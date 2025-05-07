package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.configuration.StoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

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
            logger.info("config was successfully loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StoreConfig getStoreConfig() {
        return storeConfig;
    }
}
