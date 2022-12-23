package tools.GraphReaders;

import tools.Graph.GraphEdge;
import tools.Graph.GraphNode;
import tools.GraphReaders.GraphReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVGraphReader implements GraphReader {
    private String edgesPath;
    private String nodesPath;
    public CSVGraphReader(String  edges, String nodes){
        edgesPath = edges;
        nodesPath = nodes;
    }
    @Override
    public void read_graph(ArrayList<GraphEdge> edges, ArrayList<GraphNode> nodes) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(edgesPath)).useDelimiter("[,\n]");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.nextLine();
        while (scanner.hasNext()) {
            Integer start = Integer.valueOf(scanner.next());
            Integer finish = Integer.valueOf(scanner.next());
            String type = scanner.next();
            edges.add(new GraphEdge(start, finish));
        }
        System.out.println(edges.size());
        try {
            scanner = new Scanner(new File(nodesPath)).useDelimiter("[,\n]");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.nextLine();
        while (scanner.hasNext()) {
            int id = Integer.parseInt(scanner.next());
            String label = scanner.next();
            scanner.next();
            String name = scanner.next();
            String filename = scanner.next();
            boolean overrided = scanner.next().equals("1");
            boolean invoke_call = scanner.next().equals("1");
            boolean call_call = scanner.next().equals("1");
            boolean offset_get = scanner.next().equals("1");
            boolean current_call = scanner.next().equals("1");
            boolean to_string = scanner.next().equals("1");
            boolean get_set_call = scanner.next().equals("1");
            boolean arb_obj_instance = scanner.next().equals("1");
            boolean vuln = scanner.next().equals("True");

            nodes.add(new GraphNode(id, label, name, filename, overrided, invoke_call,
                    call_call, offset_get, current_call, to_string, get_set_call, arb_obj_instance, vuln));

        }
    }
}
