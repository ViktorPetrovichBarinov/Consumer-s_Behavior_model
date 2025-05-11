package org.example;

import org.example.graph.Vertex;

import java.util.ArrayList;

public class StoreGraphEdge {
    private Vertex to;
    private double weight;
    private ArrayList<Vertex> path;

    public StoreGraphEdge(Vertex to, double weight, ArrayList<Vertex> path) {
        this.to = to;
        this.weight = weight;
        this.path = path;
    }

    public Vertex getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }
    public ArrayList<Vertex> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "to=" + this.to +
                ", weight=" + weight +
                '}';
    }
}