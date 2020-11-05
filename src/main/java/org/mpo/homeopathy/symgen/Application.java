package org.mpo.homeopathy.symgen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {

        String userDir = System.getProperty("user.dir");
        logger.info("Current directory: " + System.getProperty("user.dir"));

        if(args.length!=0) {
            logger.info(args[0]);
        }

        String remedyPath;
        if (args.length == 1) {
            remedyPath = args[0];
        } else {
            remedyPath = userDir + "\\remedies";
        }

        SymGenerator symGenerator = new SymGenerator(remedyPath);
        List<String> listOfSymptoms = symGenerator.generateListOfSymptoms();
        /*for (String s:listOfSymptoms) {
            logger.info(s);
        };*/
        ListFileWriter listFileWriter = new ListFileWriter(listOfSymptoms);
        listFileWriter.writeSymptomsToFiles(5);
        listFileWriter.writeRemediesListToFile();
    }
}
