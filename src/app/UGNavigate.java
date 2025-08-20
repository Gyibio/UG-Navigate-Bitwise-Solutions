package app;

import model.Graph;
import model.Node;
import model.Edge;
import model.Dijkstra;

import java.util.Scanner;

public class UGNavigate {
    public static void main(String[] args) {
        Graph campusGraph = new Graph();

        // Create nodes (buildings/landmarks)
        Node library = new Node(1, "Library");
        Node cafeteria = new Node(2, "Cafeteria");
        Node lectureHall = new Node(3, "Lecture Hall");
        Node adminBlock = new Node(4, "Admin Block");

        // Add nodes
        campusGraph.addNode(library);
        campusGraph.addNode(cafeteria);
        campusGraph.addNode(lectureHall);
        campusGraph.addNode(adminBlock);

        // Add edges (roads/paths: fromId, toId, distance, baseTime, trafficFactor)
        campusGraph.addEdge(new Edge(1, 2, 100, 2, 1.0), true); // Library <-> Cafeteria
        campusGraph.addEdge(new Edge(2, 3, 150, 3, 1.2), true); // Cafeteria <-> Lecture Hall
        campusGraph.addEdge(new Edge(1, 4, 200, 4, 0.9), true); // Library <-> Admin Block

        // Interactive Menu
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- UG Navigate ---");
            System.out.println("1. View all buildings");
            System.out.println("2. View connections");
            System.out.println("3. Find shortest route (Dijkstra)");
            System.out.println("4. Find fastest route (by time)");
            System.out.println("5. Show Minimum Spanning Tree (MST)");
            System.out.println("6. Find optimal route (A* Search)");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\nBuildings on campus:");
                    System.out.printf("%-5s %-20s %-10s\n", "ID", "Name", "Accessibility");
                    for (Node node : campusGraph.getNodes()) {
                        System.out.printf("%-5d %-20s %-10d\n", node.getId(), node.getName(), node.getAccessibilityRating());
                    }
                    break;

                case 2:
                    System.out.println("\nCampus connections:");
                    campusGraph.printGraph();
                    break;

                case 3:
                    System.out.print("Enter start building ID: ");
                    int startId = scanner.nextInt();
                    System.out.print("Enter destination building ID: ");
                    int destId = scanner.nextInt();

                    Node start = campusGraph.getNodeById(startId);
                    Node dest = campusGraph.getNodeById(destId);

                    if (start == null || dest == null) {
                        System.out.println("Invalid building IDs!");
                    } else {
                        Dijkstra.PathResult result = Dijkstra.shortestPath(campusGraph, startId, destId);

                        System.out.println("\nShortest path from " + start.getName() + " to " + dest.getName() + ":");
                        for (Node node : result.getPath()) {
                            System.out.print(node.getName() + " -> ");
                        }
                        System.out.println("END");
                        System.out.println("Total distance: " + result.getDistance());
                    }
                    break;

                case 4:
                    System.out.print("Enter start building ID: ");
                    int startIdTime = scanner.nextInt();
                    System.out.print("Enter destination building ID: ");
                    int destIdTime = scanner.nextInt();

                    Node startTime = campusGraph.getNodeById(startIdTime);
                    Node destTime = campusGraph.getNodeById(destIdTime);

                    if (startTime == null || destTime == null) {
                        System.out.println("Invalid building IDs!");
                    } else {
                        Dijkstra.PathResult result = Dijkstra.fastestPath(campusGraph, startIdTime, destIdTime);

                        System.out.println("\nFastest path from " + startTime.getName() + " to " + destTime.getName() + ":");
                        for (Node node : result.getPath()) {
                            System.out.print(node.getName() + " -> ");
                        }
                        System.out.println("END");
                        System.out.println("Total time: " + result.getDistance());
                    }
                    break;

                    case 5:
                        model.MST.MSTResult mst = model.MST.primMST(campusGraph);
                        System.out.println("\nMinimum Spanning Tree (MST):");
                        for (Edge edge : mst.getEdges()) {
                            System.out.println(
                                campusGraph.getNodeById(edge.getFromId()).getName() + " <-> " +
                                campusGraph.getNodeById(edge.getToId()).getName() +
                                " | Distance: " + edge.getDistance()
                            );
                        }
                        System.out.println("Total MST distance: " + mst.getTotalWeight());
                        break;

                        case 6:
                            System.out.print("Enter start building ID: ");
                            int startIdAStar = scanner.nextInt();
                            System.out.print("Enter destination building ID: ");
                            int destIdAStar = scanner.nextInt();

                            Node startAStar = campusGraph.getNodeById(startIdAStar);
                            Node destAStar = campusGraph.getNodeById(destIdAStar);

                            if (startAStar == null || destAStar == null) {
                                System.out.println("Invalid building IDs!");
                            } else {
                                model.AStarSearch.AStarResult result = model.AStarSearch.aStar(campusGraph, startIdAStar, destIdAStar);

                                System.out.println("\nOptimal path (A*) from " + startAStar.getName() + " to " + destAStar.getName() + ":");
                                for (Node node : result.getPath()) {
                                    System.out.print(node.getName() + " -> ");
                                }
                                System.out.println("END");
                                System.out.println("Total cost (time): " + result.getCost());
                            }
                            break;

                case 0:
                    System.out.println("Exiting UG Navigate... Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
