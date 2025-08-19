package model;

import java.util.*;

public class Graph {
    private Map<Integer, Node> nodes; //stores nodes by ID
    private Map<Integer, List<Edge>> adjacencyList; //each node -> list of outgoing edges

    public Graph() {
        nodes = new HashMap<>(); 
        adjacencyList = new HashMap<>();
        //initialize both maps as empthy hash maps
    }

    // Add a node (building/landmark)
    public void addNode(Node node) {
        nodes.put(node.getId(), node);
        adjacencyList.putIfAbsent(node.getId(), new ArrayList<>());
    }

    //Add an edge (road/path)
    public void addEdge(Edge edge, boolean bidirectional) {
        adjacencyList.get(edge.getFromId()).add(edge);

        if(bidirectional) {
            Edge reversEdge = new Edge(
                edge.getToId(),
                edge.getFromId(),
                edge.getDistance(),
                edge.getBaseTime(),
                edge.getTrafficFactor()
            );
            adjacencyList.get(edge.getToId()).add(reversEdge);
        }
    }

    //Get all nodes
    public Collection<Node> getNodes() {
        return nodes.values();
    }
    
    //Get a node by ID
    public Node getNodeById(int id) {
        return nodes.get(id);
    }

    //Get neighbours of a node (roads leading out)
    public List<Edge> getNeighbors(int nodeId) {
        return adjacencyList.getOrDefault(nodeId, new ArrayList<>());
    }

    //Debugging: print graph
    public void printGraph(){
        for(int nodeId : adjacencyList.keySet()) {
            Node node = nodes.get(nodeId);
            System.out.println(node.getName() + " connects to:");
            for (Edge edge : adjacencyList.get(nodeId)) {
                edge.getEffectiveTime();
                System.out.println(" -> " + nodes.get(edge.getToId()).getName() +
                                   " (distance: " + edge.getDistance() +
                                   ", time: " + edge.getEffectiveTime() + ")");
            }
        }
    }
}
