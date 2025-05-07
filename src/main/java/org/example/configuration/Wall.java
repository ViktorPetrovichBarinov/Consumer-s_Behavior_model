package org.example.configuration;

public class Wall {
    private Point start;
    private Point end;

    public Wall(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public Wall() {

    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}
