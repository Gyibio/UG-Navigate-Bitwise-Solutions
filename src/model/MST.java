package model;

import java.util.*;

public class MST {
    public static class MSTResult {
        private final List<Edge> edges;
        private final double totalWeight;

        public MSTResult(List<Edge> edges, double totalWeight) {
            this.edges = edges;
            this.totalWeight = totalWeight;
        }

        public List<Edge> getEdges() { return edges; }
        public double getTotalWeight() { return totalWeight; }
    }

    // Prim's Algorithm for Minimum Spanning Tree
    public static MSTResult primMST(Graph graph) {
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(Edge::getDistance));
        List<Edge> mstEdges = new ArrayList<>();
        double totalWeight = 0.0;

        Iterator<Node> it = graph.getNodes().iterator();
        if (!it.hasNext()) return new MSTResult(mstEdges, totalWeight);
        Node start = it.next();
        visited.add(start.getId());
        pq.addAll(graph.getNeighbors(start.getId()));

        while (!pq.isEmpty() && visited.size() < graph.getNodes().size()) {
            Edge edge = pq.poll();
            int toId = edge.getToId();
            if (visited.contains(toId)) continue;
            visited.add(toId);
            mstEdges.add(edge);
            totalWeight += edge.getDistance();
            for (Edge nextEdge : graph.getNeighbors(toId)) {
                if (!visited.contains(nextEdge.getToId())) {
                    pq.add(nextEdge);
                }
            }
        }
        return new MSTResult(mstEdges, totalWeight);
    }
}
