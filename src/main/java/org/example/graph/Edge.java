package org.example.graph;

import java.util.Objects;

public class Edge {
    private double weight;
    private Vertex startVertex;
    private Vertex endVertex;
    public Edge(Vertex startVertex, Vertex endVertex, double weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return Objects.equals(startVertex, edge.startVertex) &&
                Objects.equals(endVertex, edge.endVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startVertex, endVertex);
    }

    public double getWeight() {
        return weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }
}
