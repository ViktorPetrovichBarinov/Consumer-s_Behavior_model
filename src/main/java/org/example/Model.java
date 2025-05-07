package org.example;

import org.example.configuration.Point;
import org.example.configuration.StoreConfig;
import org.example.configuration.Wall;
import org.example.graph.Vertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model {
    private Point spawnPoint;
    private Point exitPoint;
    private ShopPlane shopPlane;

    private static final Logger logger = LoggerFactory.getLogger(Model.class);
    private final DijkstraImpl dijkstra = new DijkstraImpl();

    public Model(StoreConfig config) {
        spawnPoint = config.getEntrance();
        exitPoint = config.getExit();
        shopPlane = new ShopPlane(config.getWalls());

        dijkstraFill(shopPlane.getGraph());
        //Map<Vertex, Double> map = dijkstra.dijkstra(new Vertex(new Point(1, 1)));

        //System.out.println(map);
        logger.info("\n" + shopPlane.toString());
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


}
