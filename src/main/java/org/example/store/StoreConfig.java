package org.example.store;

import java.util.List;

public class StoreConfig {
    private List<Wall> walls;
    private Point entrance;
    private Point exit;

    public List<Wall> getWalls() {
        return walls;
    }

    public Point getEntrance() {
        return entrance;
    }

    public Point getExit() {
        return exit;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setEntrance(Point entrance) {
        this.entrance = entrance;
    }

    public void setExit(Point exit) {
        this.exit = exit;
    }
}
