package org.example.configuration;

import java.util.ArrayList;
import java.util.List;

public class StoreConfig {
    private ArrayList<Wall> walls;
    private Point entrance;
    private Point exit;

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public Point getEntrance() {
        return entrance;
    }

    public Point getExit() {
        return exit;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public void setEntrance(Point entrance) {
        this.entrance = entrance;
    }

    public void setExit(Point exit) {
        this.exit = exit;
    }
}
