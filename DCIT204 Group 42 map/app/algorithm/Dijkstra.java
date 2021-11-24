package app.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import app.graph.Digraph;
import app.graph.Edge;
import app.graph.Node;
import static app.utils.Functions.*;

public class Dijkstra {
    private static final ArrayList<Node> UNVISITED = new ArrayList<>();
    
    private static final HashMap<Node, Integer> DISTANCE_MAP = new HashMap<>();
    
    private static final HashMap<Node, Node> PREVIOUS_NODE = new HashMap<>();

    public static void findShortestPath(Digraph graph, Node source, Node destination) {

        if (source == destination) {
            print(source.getName());
            return;
        }

            for (Node node : graph.getNodes()) {
            DISTANCE_MAP.put(node, Integer.MAX_VALUE);
            PREVIOUS_NODE.put(node, null);
            UNVISITED.add(node);
        }

        // Set the cost to reach the source node to zero.
        DISTANCE_MAP.put(source, 0);

        // Find the node with the least distance to reach.
        Node minNode = findVertexWithMinDist();
        while (UNVISITED.size() > 0 && minNode != null) {
            // Find the node with the least distance to reach.
            minNode = findVertexWithMinDist();

            // Mark this node as visited.
            UNVISITED.remove(minNode);

            // Explore all the neighbours of this node.
            ArrayList<Edge> edges = graph.getDestinationEdges(minNode);
            for (Edge edge : edges) {
                // Checking for cycles: i.e., if we've not already visited this node.
                if (UNVISITED.contains(edge.getDestination())) {

                    // Calculate alternative cost
                    int alt = DISTANCE_MAP.get(minNode) + edge.getDistance();

                    if (alt < DISTANCE_MAP.get(edge.getDestination())) { // If the alternative cost is smaller than the
                                                                         // current cost.
                        // Update the min cost to reach this node.
                        DISTANCE_MAP.put(edge.getDestination(), alt);

                        // Update the previous node to reach this current node.
                        PREVIOUS_NODE.put(edge.getDestination(), minNode);
                    }
                }
            }
        }
        printShortestPath(source, destination);
        printDistances(destination);
    }

    private static void printDistances(Node destination) {
        println("Total Distance: " + String.format("%.3f", DISTANCE_MAP.get(destination) / 6F) + "kilometres \n");
    }

    private static void printShortestPath(Node source, Node destination) {
        // Reconstruct the path to the destination using the previous nodes.
        println("\nThe Shortest path from '" + source.getName() + "' to '" + destination.getName()
                + "' using Dijkstra Algorithm.");
        ArrayList<Node> path = new ArrayList<>();
        print(source.getName());
        while (PREVIOUS_NODE.get(destination) != null) {
            path.add(destination);
            destination = PREVIOUS_NODE.get(destination);
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            Node node = path.get(i);
            print(" --> " + node.getName());
        }
        println("");
    }

    private static Node findVertexWithMinDist() {
        // Linear search for the min cost node based on the distance.
        Node minNode = null;
        long minDistance = Long.MAX_VALUE;
        for (HashMap.Entry<Node, Integer> entry : DISTANCE_MAP.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (UNVISITED.contains(node) && distance < minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }
}
