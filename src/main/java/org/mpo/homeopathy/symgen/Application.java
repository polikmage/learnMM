package org.mpo.homeopathy.symgen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    //TODO Read from command line:
    // path to remedies and path to results
    // number of results in one symptom test file
    // add feature to run this as a runnable jar
    // make .bat file for convenient start up
    //if needed folders does not exist, create them


    public static void main(String[] args) throws FileNotFoundException {
        SymGenerator symGenerator = new SymGenerator("C:\\Users\\marek.pola\\IdeaProjects\\LearnMM\\src\\main\\resources\\remedies");
        List<String> listOfSymptoms = symGenerator.generateListOfSymptoms();
        for (String s:listOfSymptoms) {
            logger.info(s);
        };
        SymListWriter symListWriter = new SymListWriter(listOfSymptoms,"C:\\Users\\marek.pola\\IdeaProjects\\LearnMM\\src\\main\\resources\\results");
        symListWriter.writeSymptomsToFiles(5);
        symListWriter.writeRemediesListToFile();
    }
}
