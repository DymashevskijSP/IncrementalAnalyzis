package tools.GraphReaders;

import tools.Graph.GraphEdge;
import tools.Graph.GraphNode;

import java.util.ArrayList;
public interface GraphReader {
    void read_graph(ArrayList<GraphEdge> edges, ArrayList<GraphNode> nodes);

}
