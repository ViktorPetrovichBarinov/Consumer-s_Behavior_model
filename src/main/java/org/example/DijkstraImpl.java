package org.example;

import org.example.graph.Edge;
import org.example.graph.Vertex;

import java.util.*;

public class DijkstraImpl {
    private final Map<Vertex, List<Edge>> adjacencyList = new HashMap<>();

    public void addVertex(Vertex vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Edge edge) {
        adjacencyList.get(edge.getStartVertex()).add(edge);
    }

    public Map<Vertex, Double> dijkstra(Vertex start) {
        Map<Vertex, Double> distances = new HashMap<>();
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(Comparator.comparing(distances::get));

        for (Vertex vertex : adjacencyList.keySet()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            Vertex current = priorityQueue.poll();

            for (Edge edge : adjacencyList.get(current)) {
                Vertex neighbor = edge.getEndVertex();
                double newDist = distances.get(current) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    priorityQueue.add(neighbor);
                }
            }
        }

        return distances;
    }
}
