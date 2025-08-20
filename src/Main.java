import model.Edge;
import model.Graph;
import model.Node;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        Node node1 = new Node(1, "Balme Library");
        Node node2 = new Node(2, "Engineering School");
        Node node3 = new Node(3, "Maths Department");

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);

        graph.addEdge(new Edge(1, 2, 200, 10, 1.0), true);
        graph.addEdge(new Edge(2, 3, 150, 8, 1.2), true);
        graph.addEdge(new Edge(1, 3, 300, 15, 0.9), false);


        graph.printGraph();
    }
}
