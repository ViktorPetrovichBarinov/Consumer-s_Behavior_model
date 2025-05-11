package org.example;

import org.example.configuration.Point;
import org.example.graph.Edge;
import org.example.graph.Vertex;

import java.util.*;

public class DijkstraImpl {
    private final Map<Vertex, List<Edge>> adjacencyList = new HashMap<>();
    Map<Vertex, Vertex> predecessors = new HashMap<>();
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
            predecessors.put(vertex, null);
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
                    predecessors.put(neighbor, current);
                    priorityQueue.add(neighbor);
                }
            }
        }

        return distances;
    }

    public ArrayList<Vertex> getPath(Vertex start, Vertex end) {
        ArrayList<Vertex> path = new ArrayList<>();
        dijkstra(start);

        for (Vertex at = end; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public double getDistance(Vertex start, Vertex end) {
        var path = getPath(start, end);
        return getDistance(path);
    }

    public double getDistance(List<Vertex> path) {
        double distance = 0;
        assert path != null;
        if (path.size() == 1) {
            return distance;
        }
        Vertex prev = path.getFirst();
        for (int i = 1; i < path.size(); i++) {
            Vertex current = path.get(i);
            Point prevPoint = prev.getCoordinates();
            Point currentPoint = current.getCoordinates();
            double sectionDistance = Math.sqrt(
                    Math.pow(prevPoint.getX() - currentPoint.getX(), 2)
                    + Math.pow(prevPoint.getY() - currentPoint.getY(), 2));
            distance += sectionDistance;
            prev = current;
        }
        return distance;
    }
}
