package app.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import static app.utils.Functions.*;


public class Digraph {
    protected final HashMap<Node, ArrayList<Node>> GRAPH = new HashMap<>();
    protected final ArrayList<Edge> EDGES = new ArrayList<>();

    public void addNode(Node node){
        if (!GRAPH.containsKey(node)){
            GRAPH.put(node, new ArrayList<>());
        }
    }

    public void addEdge(Edge edge){
        if(EDGES.contains(edge)) return;

        this.EDGES.add(edge);
        for(Node source : GRAPH.keySet()){
            if (source == edge.getSource()){
                GRAPH.get(source).add(edge.getDestination());
            }
        }
    }

    public ArrayList<Edge> getDestinationEdges(Node source){
        ArrayList<Edge> destinations = new ArrayList<>();
        for (Edge edge: this.EDGES){
            if (edge.getSource() == source){
                 destinations.add(edge);
            }
        }
        return destinations;
    }

    public Node getNodeByName(String name){
        for(Node node : GRAPH.keySet()){
            if (node.getName().equalsIgnoreCase(name)){
                return node;
            }
        }
        return null;
    }

    public Set<Node> getNodes(){
        return this.GRAPH.keySet();
    }

    public void listPlaces(Node except){
        int index = 1;
        for(Node node : GRAPH.keySet()){
            if (node != except){
                println(index + ". " + node.getName());
            }
            index++;
        }
    }

}
