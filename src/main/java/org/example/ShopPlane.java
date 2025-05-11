package org.example;

import org.example.configuration.Point;
import org.example.configuration.Wall;
import org.example.graph.Edge;
import org.example.graph.Vertex;

import java.util.ArrayList;
import java.util.Map;

public class ShopPlane {
    private int maxCoordinate;
    private final ArrayList<ArrayList<PointType>> plan = new ArrayList<>();
    private final ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();

    public ShopPlane(ArrayList<Wall> wallList) {
        maxCoordinate = WallAnalyzer.getMaxCoordinate(wallList);
        initEmptyPlan(maxCoordinate);
        fillWalls(wallList);

        initGraph(maxCoordinate);
        fillGraph();

    }

    public ArrayList<ArrayList<Vertex>> getGraph() {
        return graph;
    }


    public void setPointType(Point point, PointType pointType) {
        setPointType(point.getX(), point.getY(), pointType);
    }

    public void setPointType(int x, int y, PointType pointType) {
        plan.get(x).set(y, pointType);
    }

    public PointType getPointType(Point point) {
        return getPointType(point.getX(), point.getY());
    }

    public PointType getPointType(int x, int y) {
        return plan.get(x).get(y);
    }

    private void initEmptyPlan(int length) {
        for (int i = 0; i <= length; i++) {
            plan.add(new ArrayList<>());
        }

        for (int i = 0; i <= length; i++) {
            ArrayList<PointType> shopPlane = plan.get(i);
            for (int j = 0; j <= length; j++) {
                shopPlane.add(PointType.CELL);
            }
        }
    }

    private void fillWalls(ArrayList<Wall> wallList) {
        for (Wall wall : wallList) {
            Point startPoint = wall.getStart();
            Point endPoint = wall.getEnd();

            if (startPoint.getX() == endPoint.getX()) {
                fillVerticalLine(startPoint, endPoint);
            }
            if (startPoint.getY() == endPoint.getY()) {
                fillHorizontalLine(startPoint, endPoint);
            }
        }
    }

    private void fillVerticalLine(Point startPoint, Point endPoint) {
        int x = startPoint.getX();
        int yStart = Math.min(startPoint.getY(), endPoint.getY());
        int yEnd = Math.max(startPoint.getY(), endPoint.getY());
        for (int y = yStart; y <= yEnd; y++) {
            setPointType(x, y, PointType.WALL);
        }
    }

    private void fillHorizontalLine(Point startPoint, Point endPoint) {
        int y = startPoint.getY();
        int xStart = Math.min(startPoint.getX(), endPoint.getX());
        int xEnd = Math.max(startPoint.getX(), endPoint.getX());
        for (int x = xStart; x <= xEnd; x++) {
            setPointType(x, y, PointType.WALL);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (ArrayList<PointType> poll : this.plan) {
            for (PointType pointType : poll) {
                switch (pointType) {
                    case WALL:
                        builder.append("X");
                        continue;
                    case CELL:
                        builder.append("o");
                        continue;
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public Vertex getVertex(int x, int y) {
        return graph.get(x).get(y);
    }

    public Vertex getVertex(Point point) {
        return getVertex(point.getX(), point.getY());
    }

    public void setVertex(int x, int y, Vertex vertex) {
        graph.get(x).set(y, vertex);
    }

    public void setVertex(Point point, Vertex vertex) {
        setVertex(point.getX(), point.getY(), vertex);
    }

    private void initGraph(int length) {
        for (int x = 0; x <= length; x++) {
            graph.add(new ArrayList<>());
        }

        for (int x = 0; x <= length; x++) {
            ArrayList<Vertex> vertices = graph.get(x);
            for (int y = 0; y <= length; y++) {
                if (getPointType(x, y) == PointType.CELL) {
                    vertices.add(new Vertex(new Point(x, y)));
                } else {
                    vertices.add(null);
                }
            }
        }
    }

    private void fillGraph() {
        for (int x = 0; x <= maxCoordinate; x++) {
            for (int y = 0; y <= maxCoordinate; y++) {
                PointType currentPointType = getPointType(x, y);
                if (currentPointType == PointType.WALL) {
                    continue;
                }
                Point currentPoint = new Point(x, y);
                ArrayList<Point> neighboursCoordinates = getNeighboursCoordinates(currentPoint);
                ArrayList<Point> neighboursVertexes = filterNeighboursCoordinates(neighboursCoordinates);
                fillNeighbours(currentPoint, neighboursVertexes);


            }
        }
    }



    private ArrayList<Point> getNeighboursCoordinates(Point point) {
        ArrayList<Point> neighbours = new ArrayList<>();
        int x = point.getX();
        int y = point.getY();
        neighbours.add(new Point(x-1, y));
        neighbours.add(new Point(x+1, y));
        neighbours.add(new Point(x, y-1));
        neighbours.add(new Point(x, y+1));
        neighbours.add(new Point(x-1, y-1));
        neighbours.add(new Point(x+1, y-1));
        neighbours.add(new Point(x-1, y+1));
        neighbours.add(new Point(x+1, y+1));
        return neighbours;
    }

    private ArrayList<Point> filterNeighboursCoordinates(ArrayList<Point> points) {
        ArrayList<Point> filteredPoints = new ArrayList<>();
        for (Point point : points) {
            int x = point.getX();
            int y = point.getY();
            if (x < 0 || y < 0) {
                continue;
            }
            if (x > maxCoordinate || y > maxCoordinate) {
                continue;
            }
            PointType currentType = getPointType(point);
            if (currentType == PointType.WALL) {
                continue;
            }
            filteredPoints.add(point);
        }

        return filteredPoints;
    }

    private void fillNeighbours (Point source, ArrayList<Point> destinationPoints) {
        Vertex sourceVertex = getVertex(source);
        for (Point targetPoint : destinationPoints) {
            Vertex targetVertex = getVertex(targetPoint);
            double diffX = source.getX() - targetPoint.getX();
            double diffY = source.getY() - targetPoint.getY();
            double weight = Math.sqrt(diffX * diffX + diffY * diffY);
            Edge edge = new Edge(sourceVertex, targetVertex, weight);
            sourceVertex.addEdge(edge);
            edge = new Edge(targetVertex, sourceVertex, weight);
            targetVertex.addEdge(edge);
        }
    }
}
