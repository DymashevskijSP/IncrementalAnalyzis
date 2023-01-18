import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import ddlogapi.*;
import tools.GraphReaders.CSVGraphReader;
import tools.Graph.GraphEdge;
import ddlog.analyze.*;
import tools.Graph.GraphNode;
import tools.GraphReaders.GraphReader;
import tools.GraphReaders.RandomGraphGenerator;

public class Main {
    static class Test {

        private DDlogAPI api;
        double split_coef = 0.95;
        private final int TRANSACTION_COUNT = 1;
        private int resultDelta = 0;
        private int maxN = 0;
        private ArrayList<GraphEdge> edges = new ArrayList<>();
        private ArrayList<GraphNode> nodes = new ArrayList<>();
        private HashSet<Integer> endNodes = new HashSet<>();
        private final HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

        private int color = 1;

        private GraphEdge parseEdge(DDlogRecord record) {
            String s = record.toString().substring(5, record.toString().length() - 1);
            String[] nums = s.split(",");
            return new GraphEdge(Integer.valueOf(nums[0]), Integer.valueOf(nums[1]));
        }

        Test(int v) throws DDlogException, IOException {
            GraphReader reader = new RandomGraphGenerator(v);//new CSVGraphReader("rels.csv", "nodes.csv");
            reader.read_graph(edges, nodes);
            for (GraphEdge edge : edges) {
                maxN = Integer.max(maxN, Integer.max(edge.from, edge.to));
                graph.put(edge.from, new ArrayList<>());
                graph.put(edge.to, new ArrayList<>());
            }

        }

        void insert_vertex() throws DDlogException, IOException {
            this.api = new DDlogAPI(8, false);
            api.recordCommands("replay.dat", false);
            this.api.transactionStart();
            analyzeUpdateBuilder builder = new analyzeUpdateBuilder();
            for (GraphNode node : nodes) {
                if (node.vuln) {
                    endNodes.add(node.id);
                }
                builder.insert_Node(BigInteger.valueOf(node.id), node.label, node.name, node.filename, node.overrided, node.invoke_call,
                        node.call_call, node.offset_get, node.current_call, node.to_string, node.get_set_call, node.arb_obj_instance, node.vuln);
            }
            builder.applyUpdates(this.api);
            this.api.transactionCommitDumpChanges(this::onCommit);
        }

        void onCommit(DDlogCommand<DDlogRecord> command) {
            int relid = command.relid();
            switch (relid) {
                case analyzeRelation.Next:
                    resultDelta++;
                    GraphEdge edge = parseEdge(command.value());
                    graph.get(edge.from).add(edge.to);
                    maxN = Integer.max(Integer.max(edge.from, edge.to), maxN);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown relation id " + relid);
            }
        }

        ArrayList<ArrayList<GraphEdge>> split_sample(ArrayList<GraphEdge> edges) {
            int train_size = (int) (edges.size() * split_coef);
            int transaction_size = (edges.size() - train_size) / TRANSACTION_COUNT;
            ArrayList<GraphEdge> train = new ArrayList<>(edges);
            ArrayList<ArrayList<GraphEdge>> ans = new ArrayList<>();
            ans.add(train);
            for (int i = 0; i < TRANSACTION_COUNT; i++) {
                ans.add(new ArrayList<>());
                for (int j = 0; j < transaction_size; j++) {
                    ans.get(i + 1).add(edges.get(i * transaction_size + j));
                }
            }
          return ans;
        }

        long run_with_incrementalization() throws DDlogException, IOException {
            insert_vertex();
            ArrayList<ArrayList<GraphEdge>> data = split_sample(edges);
            long res = System.currentTimeMillis();
            analyzeUpdateBuilder builder;
            this.api.transactionStart();
            builder = new analyzeUpdateBuilder();
            for (int i = 0; i < data.get(0).size(); i++) {
                builder.insert_Edge(new BigInteger(String.valueOf(data.get(0).get(i).from)),
                        new BigInteger(String.valueOf(data.get(0).get(i).to)));
            }
            builder.applyUpdates(this.api);
            this.api.transactionCommitDumpChanges(this::onCommit);
            for (int trans_n = 1; trans_n < data.size(); trans_n++) {
                this.api.transactionStart();
                builder = new analyzeUpdateBuilder();
                for (int op_cnt = 1; op_cnt < data.get(trans_n).size(); op_cnt++) {
                    builder.delete_Edge(new BigInteger(String.valueOf(data.get(trans_n).get(op_cnt).from)),
                            new BigInteger(String.valueOf(data.get(trans_n).get(op_cnt).to)));
                }
                builder.applyUpdates(this.api);
                this.api.transactionCommitDumpChanges(this::onCommit);
                analyzeGraph();
            }
            for (int trans_n = 1; trans_n < data.size(); trans_n++) {
                this.api.transactionStart();
                builder = new analyzeUpdateBuilder();
                for (int op_cnt = 1; op_cnt < data.get(trans_n).size(); op_cnt++) {
                    builder.insert_Edge(new BigInteger(String.valueOf(data.get(trans_n).get(op_cnt).from)),
                            new BigInteger(String.valueOf(data.get(trans_n).get(op_cnt).to)));
                }
                builder.applyUpdates(this.api);
                this.api.transactionCommitDumpChanges(this::onCommit);
                analyzeGraph();
            }
            res = System.currentTimeMillis() - res;
            this.api.stop();
            return res;
        }

        long run_without_incrementalization(int num) throws DDlogException, IOException {
            insert_vertex();
            ArrayList<ArrayList<GraphEdge>> data = split_sample(edges);
            long res = System.currentTimeMillis();

            analyzeUpdateBuilder builder = new analyzeUpdateBuilder();
            ;
            this.api.transactionStart();
            int size = num % 2 == 0 ? data.get(0).size() : data.get(0).size() - data.get(1).size();
            for (int i = 0; i < size; i++) {
                builder.insert_Edge(new BigInteger(String.valueOf(data.get(0).get(i).from)),
                        new BigInteger(String.valueOf(data.get(0).get(i).to)));
            }
            builder.applyUpdates(this.api);
            this.api.transactionCommitDumpChanges(this::onCommit);
            analyzeGraph();
            res = System.currentTimeMillis() - res;
            this.api.stop();
            return res;
        }

        void analyzeGraph() {
            long res = System.currentTimeMillis();
            ArrayList<Boolean> used = new ArrayList<>();
            for (int i = 0; i < maxN + 1; i++) {
                used.add(false);
            }

            HashSet<Integer> roots = new HashSet<>();
            for (int i = 0; i < maxN + 1; i++) {
                if (!used.get(i) && graph.containsKey(i) && graph.get(i).size() > 0) {
                    dfs(i, -1, used, roots);
                }
            }
            ArrayList<Integer> usedNumber = new ArrayList<>();
            for (int i = 0; i < maxN + 1; i++) {
                usedNumber.add(0);
            }
            color = 0;
            for (int i : roots) {
                //System.out.printf("paths for %s\n", i);
                dfs_paths(i, new ArrayList<>(), usedNumber);
                color++;
            }
        }

        void dfs(int v, int parent, ArrayList<Boolean> used, HashSet<Integer> roots) {
            if (parent == -1) {
                roots.add(v);
            }
            used.set(v, true);
            for (int u : graph.get(v)) {
                roots.remove(u);
                if (!used.get(u)) {
                    dfs(u, v, used, roots);
                }

            }
        }

        void dfs_paths(int v, ArrayList<Integer> curpath, ArrayList<Integer> used) {
            curpath.add(v);
            used.set(v, color);
            if (graph.get(v).size() == 0 && endNodes.contains(v)) {
                for (int u : curpath) {
                    //save path -- curpath
                    //System.out.printf("%s ", u);
                }
            }
            for (int u : graph.get(v)) {
                if (used.get(u) < color) {
                    dfs_paths(u, curpath, used);
                }
            }
            curpath.remove(curpath.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException, DDlogException {
        ArrayList<Integer> res_inc = new ArrayList<>();
        ArrayList<Integer> res_no_inc = new ArrayList<>();
        int maxv = 10000;
        int minv = 3000;
        for(int v = minv; v < maxv; v+=100){
            System.out.print((v-minv)*100/maxv);
            System.out.print("% ");
            int ans = 0;
            int ans2 = 0;
            for(int i = 0; i < 5; i++){
                Test test = new Test(v);
                ans += (int)test.run_with_incrementalization();
                ans2 += (int)test.run_without_incrementalization(0);
                ans2 += (int)test.run_without_incrementalization(1);
                ans2 += (int)test.run_without_incrementalization(2);
            }
            res_inc.add(ans/5);
            res_no_inc.add(ans2/5);
        }
        for (int i = 0; i < res_inc.size(); i++) {
            System.out.printf("%s, ", res_inc.get(i));
        }
        System.out.println();

        for (int i = 0; i < res_no_inc.size(); i++) {
            System.out.printf("%s, ", res_no_inc.get(i));
        }
    }
}
