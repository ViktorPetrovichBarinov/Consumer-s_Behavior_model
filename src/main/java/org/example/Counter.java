package org.example;

import org.example.configuration.Point;

public class Counter {
    String name;
    Point coordinate;
    Double weight;

    public String getName() {
        return name;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
