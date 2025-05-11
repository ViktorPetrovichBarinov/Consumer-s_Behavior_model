package org.example;

import org.example.configuration.Point;

import java.util.ArrayList;
import java.util.Random;

public class Path {
    private Point points[];
    public Path(int size) {
        points = new Point[size];
    }

    public boolean isFull() {
        for (Point point : points) {
            if (point == null) return false;
        }
        return true;
    }

    public Point[] getPoints() {
        return points;
    }

    public void addPointToRandomPlace(Point point, Random random) {
        if (isFull()) {
            throw new IllegalStateException("Path is full");
        }
        ArrayList<Integer> nullIndexes = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) nullIndexes.add(i);
        }

        int randomIndex = nullIndexes.get(random.nextInt(nullIndexes.size()));
        points[randomIndex] = point;
    }
}
