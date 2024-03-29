package org.mpo.homeopathy.symgen;

import org.mpo.homeopathy.symgen.gen.SymptomGenerator;
import org.mpo.homeopathy.symgen.quiz.QuizGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {

        String userDir = System.getProperty("user.dir");
        logger.info("Current directory: " + System.getProperty("user.dir"));

        if (args.length != 0) {
            for (String s : args) {
                logger.info(s);
            }
        }

        String remedyPath;
        if (args.length == 1) {
            remedyPath = args[0];
        } else {
            remedyPath = userDir + "/remedies7";
        }
        /*
        int num;
        if (args.length == 1) {
            num = Integer.parseInt(args[0]);
        } else {
            num = 20;
        }
        */

        //String remedyPath = userDir + "\\remedies";

        SymptomGenerator symptomGenerator = new SymptomGenerator(remedyPath);
        List<String> listOfSymptoms = symptomGenerator.generateListOfSymptoms();
        /*
        ListFileWriter listFileWriter = new ListFileWriter(listOfSymptoms);
        listFileWriter.writeSymptomsToFiles(num);
        listFileWriter.writeRemediesListToFile();
        */

        QuizGame quizGame = new QuizGame(symptomGenerator.getRemedies(), listOfSymptoms);
        quizGame.runGame();
    }
}
