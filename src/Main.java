
import java.util.List;
import java.util.Scanner;

import algorithms.Dijkstra;
import io.FileInputHelper;
import model.Graph;
import model.Node;

public class Main {
    public static void main(String[] args) {
           // Adjust file paths as needed
        String nodesFile = "C:\\Users\\royad\\Desktop\\UG-Navigate-Bitwise-Solutions\\data\\n" + //
                        "odes.txt";
        String edgesFile = "C:\\Users\\royad\\Desktop\\UG-Navigate-Bitwise-Solutions\\data\\edges.txt";

        Graph graph = FileInputHelper.loadGraph(nodesFile, edgesFile);

        // System.out.println("Graph Loaded");
        // System.out.println("Nodes count: " + graph.getNodes().size());
        // System.out.println("Edges count: " + graph.getEdges().size());

        // graph.printGraph();
        // Choose start and end
Scanner sc = new Scanner(System.in);
System.out.print("Enter start node ID: ");
int start = sc.nextInt();
System.out.print("Enter destination node ID: ");
int end = sc.nextInt();
System.out.print("Optimize by (1) Distance or (2) Time: ");
int opt = sc.nextInt();

boolean useTime = (opt == 2);

// Run Dijkstra
List<Node> path = Dijkstra.findShortestPath(graph, start, end, useTime);

// Print results
System.out.println("Shortest Path:");
for (Node n : path) {
    System.out.print(n.getName() + " -> ");
}
System.out.println("END");
    }
}
