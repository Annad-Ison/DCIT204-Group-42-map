package app;

import java.util.Scanner;
import app.algorithm.Dijkstra;
import app.graph.Node;
import app.graph.Edge;
import app.graph.Digraph;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Digraph graph = new Digraph();
        Node a = new Node("Main Gate");
        Node b = new Node("Balme Library");
        Node c = new Node("Commonwealth Hall");
        Node d = new Node("Great Hall");
        Node e = new Node("Night Market");
        Node f = new Node("Central Cafeteria");
        Node g = new Node("Diaspora");

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(f);
        graph.addNode(g);

        graph.addEdge(new Edge(a, b, 4));
        graph.addEdge(new Edge(a, c, 7));
        graph.addEdge(new Edge(a, g, 11));

        graph.addEdge(new Edge(b, d, 20));
        graph.addEdge(new Edge(b, c, 3));

        graph.addEdge(new Edge(c, d, 1));
        graph.addEdge(new Edge(c, e, 8));

        graph.addEdge(new Edge(d, f, 7));
        graph.addEdge(new Edge(d, e, 10));

        graph.addEdge(new Edge(e, f, 5));
        graph.addEdge(new Edge(e, g, 1));
        graph.addEdge(new Edge(g, e, 1));

        System.out.println("Pick your current location:");
        graph.listPlaces(null);

        String currentLocationHolder = scanner.nextLine();
        Node currentLocation = graph.getNodeByName(currentLocationHolder);

        System.out.println("\nPick your destination:");
        String destinationHolder = scanner.nextLine();
        Node destination = graph.getNodeByName(destinationHolder);
        Dijkstra.findShortestPath(graph, currentLocation, destination);
    }
}