package org.example;

import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.store.StoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;

public class SupermarketCreator {
    private static final Logger logger = LoggerFactory.getLogger(SupermarketCreator.class);
    private StoreConfig storeConfig;
    private final String configPath;
    public SupermarketCreator(String configPath) {
        this.configPath = configPath;
        jsonParse();
        logger.info(Integer.toString(storeConfig.getEntrance().getX()));
    }

    private void jsonParse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonFile = new File(configPath);
            this.storeConfig = mapper.readValue(jsonFile, StoreConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
