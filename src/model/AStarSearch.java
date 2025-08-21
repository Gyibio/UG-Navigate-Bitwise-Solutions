package model;

import java.util.*;

public class AStarSearch {
    public static class AStarResult {
        private final double cost;
        private final List<Node> path;

        public AStarResult(double cost, List<Node> path) {
            this.cost = cost;
            this.path = path;
        }

        public double getCost() { return cost; }
        public List<Node> getPath() { return path; }
    }

    // Heuristic function: returns 0 (can be replaced with coordinates for real heuristic)
    private static double heuristic(Node a, Node b) {
        return 0; // For now, no coordinates, so Dijkstra-like behavior
    }

    // A* algorithm for fastest route by time
    public static AStarResult aStar(Graph graph, int startId, int destId, int hour) {
        Map<Integer, Double> gScore = new HashMap<>();
        Map<Integer, Double> fScore = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();
        Set<Integer> closedSet = new HashSet<>();
    PriorityQueue<double[]> openSet = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));

        for (Node node : graph.getNodes()) {
            gScore.put(node.getId(), Double.MAX_VALUE);
            fScore.put(node.getId(), Double.MAX_VALUE);
        }
        gScore.put(startId, 0.0);
        fScore.put(startId, heuristic(graph.getNodeById(startId), graph.getNodeById(destId)));

    openSet.add(new double[]{startId, fScore.get(startId)});

        while (!openSet.isEmpty()) {
            double[] current = openSet.poll();
            int currentId = (int)current[0];

            if (currentId == destId) break;
            closedSet.add(currentId);

            for (Edge edge : graph.getNeighbors(currentId)) {
                int neighborId = edge.getToId();
                if (closedSet.contains(neighborId)) continue;
                double tentativeG = gScore.get(currentId) + edge.getEffectiveTime(hour);
                if (tentativeG < gScore.get(neighborId)) {
                    previous.put(neighborId, currentId);
                    gScore.put(neighborId, tentativeG);
                    fScore.put(neighborId, tentativeG + heuristic(graph.getNodeById(neighborId), graph.getNodeById(destId)));
                    openSet.add(new double[]{neighborId, fScore.get(neighborId)});
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
        return new AStarResult(gScore.get(destId), path);
    }
}
