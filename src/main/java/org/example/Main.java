package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.cli.*;
import org.example.configuration.StoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        Options options = new Options();

        options.addOption("h", "help", false, "Show help.");
        options.addOption("c", "config", true, "Specification config path.");
        options.addOption("w", "walls", false, "Show only walls.");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("CommandLineExample", options);
                return;
            }
            String filePath;
            if (!cmd.hasOption("c")) {
                logger.error("Missing configuration path.");
                return;
            } else {
                filePath = cmd.getOptionValue("c");
                logger.info("Using configuration path: {}", filePath);
            }
            SupermarketCreator creator = new SupermarketCreator(filePath);

            StoreConfig config = creator.getStoreConfig();
            Model model = new Model(config);
            ShopHitMap hitMap = model.simulate(100000);
            HeatMapExample example = new HeatMapExample("Heatmap Example", heatMapToInt(hitMap));
            example.pack();
            example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            example.setVisible(true);
        } catch (ParseException e) {
            logger.info("Arguments parsing error: {}", e.getMessage());
        }

    }

    private static int[][] heatMapToInt(ShopHitMap hitMap) {
        int max = hitMap.getMaxCoordinate();
        int[][] result = new int[max+1][max+1];
        var plan = hitMap.getPlan();
        for (int x = 0; x <= max; x++) {
            for (int y = 0; y <= max; y++) {
                result[x][y] = plan.get(x).get(y);
            }
        }
        return result;
    }
}