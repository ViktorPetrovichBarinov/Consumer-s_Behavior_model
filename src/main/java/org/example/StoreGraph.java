package org.example;

import org.example.configuration.Point;
import org.example.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;

public class StoreGraph {
    private final ArrayList<Vertex> vertexes = new ArrayList<>();
    private final HashMap<Vertex, ArrayList<StoreGraphEdge>> graph = new HashMap<>();
    public StoreGraph(ArrayList<Counter> counters, Point entrance, Point exit) {
        for (Counter counter : counters) {
            Vertex vertex = new Vertex(counter.coordinate);
            vertexes.add(vertex);
            graph.put(vertex, new ArrayList<>());
        }
        vertexes.add(new Vertex(entrance));
        graph.put(new Vertex(entrance), new ArrayList<>());
        vertexes.add(new Vertex(exit));
        graph.put(new Vertex(exit), new ArrayList<>());
    }

    public ArrayList<Vertex> getVertexes() {
        return vertexes;
    }

    public void addEdge(Vertex from, Vertex to, double weight, ArrayList<Vertex> path) {
        var edges = graph.get(from);
        for (var edge : edges) {
            if (edge.getTo().equals(to)) {
                return;
            }
        }
        edges.add(new StoreGraphEdge(to, weight, path));
    }
}
