package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.cli.*;
import org.example.configuration.StoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        } catch (ParseException e) {
            logger.info("Arguments parsing error: {}", e.getMessage());
        }

    }
}