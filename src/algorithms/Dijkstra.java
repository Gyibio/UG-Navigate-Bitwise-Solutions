package algorithms;

import model.Graph;
import model.Node;
import model.Edge;

import java.util.*;

public class Dijkstra {

    public static List<Node> findShortestPath(Graph graph, int startId, int endId, boolean useTime) {
        // Distance map: nodeId -> shortest distance/time
        Map<Integer, Double> distances = new HashMap<>();
        // Parent map: nodeId -> previous node in path
        Map<Integer, Integer> parents = new HashMap<>();
        // Priority queue (min-heap) by distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));

        // Initialize all distances to infinity
        for (Node node : graph.getNodes()) {
            distances.put(node.getId(), Double.POSITIVE_INFINITY);
        }

        // Distance to start = 0
        distances.put(startId, 0.0);
        pq.add(new int[]{startId, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentId = current[0];
            double currentDist = distances.get(currentId);

            // Stop if we reached destination
            if (currentId == endId) break;

            // Explore neighbors
            for (Edge edge : graph.getNeighbors(currentId)) {
                // Choose weight: distance OR effectiveTime
                double weight = useTime ? (edge.getBaseTime() * edge.getTrafficFactor()) : edge.getDistance();
                double newDist = currentDist + weight;

                if (newDist < distances.get(edge.getToId())) {
                    distances.put(edge.getToId(), newDist);
                    parents.put(edge.getToId(), currentId);
                    pq.add(new int[]{edge.getToId(), (int)newDist});
                }
            }
        }

        // Reconstruct path
        List<Node> path = new ArrayList<>();
        Integer current = endId;
        while (current != null && parents.containsKey(current)) {
            path.add(graph.getNodeById(current));
            current = parents.get(current);
        }
        path.add(graph.getNodeById(startId)); // add start
        Collections.reverse(path);

        return path;
    }
}
