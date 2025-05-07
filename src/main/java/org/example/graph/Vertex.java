package org.example.graph;

import org.example.configuration.Point;

import java.util.ArrayList;
import java.util.Objects;

public class Vertex {
    private Point coordinates;
    private ArrayList<Edge> edges = new ArrayList<>();
    public Vertex(Point coordinates) {
        this.coordinates = coordinates;
    }

    public void addEdge(Edge edge) {
        if (!edges.contains(edge)) {
            this.edges.add(edge);
        }
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return Objects.equals(coordinates, vertex.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }
}
