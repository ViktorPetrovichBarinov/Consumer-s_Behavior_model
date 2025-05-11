package org.example;

import org.example.configuration.Point;
import org.example.configuration.Wall;
import org.example.graph.Vertex;

import java.util.ArrayList;

public class ShopHitMap {
    private final ArrayList<ArrayList<Integer>> plan = new ArrayList<>();
    private int maxCoordinate;

    public ShopHitMap(ArrayList<Wall> wallList) {
        maxCoordinate = WallAnalyzer.getMaxCoordinate(wallList);
        initEmptyPlan(maxCoordinate);
    }

    private void initEmptyPlan(int length) {
        for (int i = 0; i <= length; i++) {
            plan.add(new ArrayList<>());
        }

        for (int i = 0; i <= length; i++) {
            ArrayList<Integer> shopPlane = plan.get(i);
            for (int j = 0; j <= length; j++) {
                shopPlane.add(0);
            }
        }
    }

    public void incrementCells(ArrayList<Vertex> vertices) {
        for (var vertex : vertices) {
            Point current = vertex.getCoordinates();
            int currentNumber = plan.get(current.getX()).get(current.getY());
            plan.get(current.getX()).set(current.getY(), currentNumber + 1);
        }
    }

}
