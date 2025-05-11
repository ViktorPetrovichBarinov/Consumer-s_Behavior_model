package org.example;

import org.example.configuration.Point;
import org.example.configuration.StoreConfig;
import org.example.configuration.Wall;
import org.example.graph.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Model {
    private Point spawnPoint;
    private Point exitPoint;

    public ShopPlane getShopPlane() {
        return shopPlane;
    }

    private ShopPlane shopPlane;

    private static final Logger logger = LoggerFactory.getLogger(Model.class);
    private final DijkstraImpl dijkstra = new DijkstraImpl();
    private final StoreGraph storeGraph;
    private final ArrayList<Counter> counters;
    private Double avgNumberOfBuying;
    private ShopHitMap shopHitMap;
    private final Random random = new Random(52);

    public Model(StoreConfig config) {
        spawnPoint = config.getEntrance();
        exitPoint = config.getExit();
        shopPlane = new ShopPlane(config.getWalls());
        shopHitMap = new ShopHitMap(config.getWalls());
        dijkstraFill(shopPlane.getGraph());
        storeGraph = new StoreGraph(config.getCounters(), spawnPoint, exitPoint);
        fillStoreGraph();
        counters = config.getCounters();
        initAvgNumberOfBuying();

        logger.info("\n" + shopPlane.toString());
    }

    public ShopHitMap simulate(Integer numberOfBuyers) {
        ArrayList<Path> paths = generatePaths(numberOfBuyers);
        for (Path path : paths) {
            Point[] points = path.getPoints();
            Point prev = this.spawnPoint;
            for (Point current : points) {
                ArrayList<Vertex> subPath = storeGraph.getPath(new Vertex(prev), new Vertex(current));
                shopHitMap.incrementCells(subPath);
                prev = current;
            }
            ArrayList<Vertex> subPath = storeGraph.getPath(new Vertex(prev), new Vertex(this.exitPoint));
            shopHitMap.incrementCells(subPath);

        }

        return this.shopHitMap;
    }

    private ArrayList<Path> generatePaths(Integer numberOfBuyers) {
        int[] paths = new int[numberOfBuyers];
        int numberOfProducts = (int) (numberOfBuyers * this.avgNumberOfBuying);
        for (int i = 0; i < numberOfProducts; i++) {
            int index = random.nextInt(numberOfBuyers);
            paths[index]++;
        }

        ArrayList<Path> pathsEmptyList = new ArrayList<>();
        for (int i = 0; i < numberOfBuyers; i++) {
            if (paths[i] != 0) {
                pathsEmptyList.add(new Path(paths[i]));
            }
        }

        ArrayList<Path> pathFullList = new ArrayList<>();

        for (Counter counter : counters) {
            int numberOfCurrentTypeProducts = (int)(counter.weight * numberOfBuyers);
            for (int j = 0; j < numberOfCurrentTypeProducts; j++) {
                Path path = pathsEmptyList.get(random.nextInt(pathsEmptyList.size()));
                path.addPointToRandomPlace(counter.coordinate, random);
                if (path.isFull()) {
                    pathFullList.add(path);
                    pathsEmptyList.remove(path);
                }
            }
        }

        return pathFullList;
    }

    private void initAvgNumberOfBuying() {
        double avgNumberOfBuying = 0;
        for (var counter : counters) {
            double weight = counter.weight;
            avgNumberOfBuying += weight;
        }
        this.avgNumberOfBuying = avgNumberOfBuying;
    }

    private void fillStoreGraph() {
        var vertexes = storeGraph.getVertexes();
        for (int i = 0; i < vertexes.size(); i++) {
            var from = vertexes.get(i);
            storeGraph.addEdge(from, from, 0.0, new ArrayList<>());
            for (int j = i + 1; j < vertexes.size(); j++) {
                var to = vertexes.get(j);
                var path = dijkstra.getPath(from, to);

                double distance = dijkstra.getDistance(path);
                storeGraph.addEdge(from, to, distance, path);
                storeGraph.addEdge(to, from, distance, path);
            }
        }
    }

    private void dijkstraFill(ArrayList<ArrayList<Vertex>> graph) {
        for (ArrayList<Vertex> list : graph) {
            for (var vertex : list) {
                if (vertex == null) {
                    continue;
                }
                dijkstra.addVertex(vertex);
                for (var edge : vertex.getEdges()) {
                    dijkstra.addEdge(edge);
                }
            }
        }
    }

    public void simulateBuyings() {

    }

}
