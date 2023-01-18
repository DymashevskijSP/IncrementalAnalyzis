package tools.GraphReaders;

import tools.Graph.GraphEdge;
import tools.Graph.GraphNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class RandomGraphGenerator implements GraphReader {
    int graph_size;

    public RandomGraphGenerator(int x) {
        graph_size = x;
    }

    @Override
    public void read_graph(ArrayList<GraphEdge> edges, ArrayList<GraphNode> nodes) {
        int startNodeCount = (int) max(graph_size * 0.0002, 5);
        int finishNodeBorder = (int) min(graph_size * 0.999, graph_size - 3);
        for (int i = 0; i < graph_size; i++) {
            GraphNode node = new GraphNode(i + 1, "Function", i < startNodeCount ? "__wakeup" : i > finishNodeBorder ? "vuln" : "randomfunc",
                    "", false, false, false, false,
                    false, false, false, false, i > finishNodeBorder);

            nodes.add(node);
        }
        Random r = new Random();
        for (int i = 0; i < graph_size; i++) {
            for (int j = i; j < graph_size; j++) {
                if (r.nextInt(graph_size) < graph_size * 0.001) {
                    edges.add(new GraphEdge(i, j));
                }
            }
        }
        Collections.shuffle(edges);
        Collections.shuffle(nodes);
    }
}
