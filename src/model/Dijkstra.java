package model;

import java.util.*;

public class Dijkstra {

    public static class PathResult {
        private final double distance;
        private final List<Node> path;

        public PathResult(double distance, List<Node> path) {
            this.distance = distance;
            this.path = path;
        }

        public double getDistance() { return distance; }
        public List<Node> getPath() { return path; }
    }

    // Dijkstra's algorithm for shortest path by distance
    public static PathResult shortestPath(Graph graph, int startId, int destId) {
        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));

        // Initialize distances
        for (Node node : graph.getNodes()) {
            distances.put(node.getId(), Double.MAX_VALUE);
        }
        distances.put(startId, 0.0);

        pq.add(new int[]{startId, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentId = current[0];

            if (currentId == destId) break; // stop early when destination is reached

            for (Edge edge : graph.getNeighbors(currentId)) {
                double newDist = distances.get(currentId) + edge.getDistance();
                if (newDist < distances.get(edge.getToId())) {
                    distances.put(edge.getToId(), newDist);
                    previous.put(edge.getToId(), currentId);
                    pq.add(new int[]{edge.getToId(), (int)newDist});
                }
            }
        }

        // Reconstruct path
        List<Node> path = new ArrayList<>();
        Integer current = destId;
        while (current != null) {
            path.add(graph.getNodeById(current));
            current = previous.get(current);
        }
        Collections.reverse(path);

        return new PathResult(distances.get(destId), path);
    }
    
    // Dijkstra's algorithm for fastest path by time
    public static PathResult fastestPath(Graph graph, int startId, int destId, int hour) {
        Map<Integer, Double> times = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));

        // Initialize times
        for (Node node : graph.getNodes()) {
            times.put(node.getId(), Double.MAX_VALUE);
        }
        times.put(startId, 0.0);

        pq.add(new int[]{startId, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentId = current[0];

            if (currentId == destId) break;

            for (Edge edge : graph.getNeighbors(currentId)) {
                double newTime = times.get(currentId) + edge.getEffectiveTime(hour);
                if (newTime < times.get(edge.getToId())) {
                    times.put(edge.getToId(), newTime);
                    previous.put(edge.getToId(), currentId);
                    pq.add(new int[]{edge.getToId(), (int)newTime});
                }
            }
        }

        // Build path
        List<Node> path = new ArrayList<>();
        int currentId = destId;
        while (previous.containsKey(currentId)) {
            path.add(0, graph.getNodeById(currentId));
            currentId = previous.get(currentId);
        }
        if (currentId == startId) {
            path.add(0, graph.getNodeById(startId));
        }
        return new PathResult(times.get(destId), path);
    }
}

