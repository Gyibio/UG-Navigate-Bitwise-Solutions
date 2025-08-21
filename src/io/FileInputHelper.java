package io;

import model.Node;
import model.Edge;
import model.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInputHelper {
      // Load the graph directly from files
    public static Graph loadGraph(String nodesFile, String edgesFile) {
        Graph graph = new Graph();

    //Load nodes from nodes.txt
        try (BufferedReader br = new BufferedReader(new FileReader(nodesFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Example line: 1,JQB
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    graph.addNode(new Node(id, name));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading nodes files: " + e.getMessage());
        }    

    // Load edges 
        try (BufferedReader br = new BufferedReader(new FileReader(edgesFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Example line: 1,2,200,10,1.0
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int fromId = Integer.parseInt(parts[0].trim());
                    int toId = Integer.parseInt(parts[1].trim());
                    double distance = Double.parseDouble(parts[2].trim());
                    double baseTime = Double.parseDouble(parts[3].trim());
                    double trafficFactor = Double.parseDouble(parts[4].trim());
                    boolean bidirectional = Boolean.parseBoolean(parts[5].trim());

                    graph.addEdge(new Edge(fromId, toId, distance, baseTime, trafficFactor),bidirectional);

                    // If the road is bidirectional, add the reverse edge too
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading edges file: " + e.getMessage());
        }
        return graph;
    }
}
