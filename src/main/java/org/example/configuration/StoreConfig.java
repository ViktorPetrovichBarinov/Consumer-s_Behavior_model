package org.example.configuration;

import org.example.Counter;

import java.util.ArrayList;

public class StoreConfig {
    private ArrayList<Wall> walls;
    private Point entrance;
    private Point exit;
    private ArrayList<Counter> counters;

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public Point getEntrance() {
        return entrance;
    }

    public Point getExit() {
        return exit;
    }

    public ArrayList<Counter> getCounters() {
        return counters;
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

    public void setCounters(ArrayList<Counter> counters) {
        this.counters = counters;
    }

}
