package org.example;

import org.example.configuration.Wall;

import java.util.ArrayList;

public class WallAnalyzer {
    public static int getMaxCoordinate(ArrayList<Wall> wallList) {
        int max = 0;
        for (Wall wall : wallList) {
            max = Math.max(max, wall.getStart().getX());
            max = Math.max(max, wall.getStart().getY());
            max = Math.max(max, wall.getEnd().getX());
            max = Math.max(max, wall.getEnd().getY());
        }
        return max;
    }
}
