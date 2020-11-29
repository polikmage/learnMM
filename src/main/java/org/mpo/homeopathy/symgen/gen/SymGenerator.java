package org.mpo.homeopathy.symgen.gen;

import org.mpo.homeopathy.symgen.model.Remedy;
import org.mpo.homeopathy.symgen.io.RemedyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.*;

public class SymGenerator {
    private static Logger logger = LoggerFactory.getLogger(SymGenerator.class);
    private String symptomClass = "keynotes";
    private List<Remedy> remedies;
    private RemedyReader remedyReader = new RemedyReader();
    private List<String> listOfSymptoms = new LinkedList<>();

    public SymGenerator(String pathToRemedies) throws FileNotFoundException {
        remedies = remedyReader.readAllRemedies(pathToRemedies);
    }

    public List<String> generateListOfSymptoms() {
        //Stack<String> randomizedSymptoms = new Stack<>();
        //int highestNumberOfSymptomsInRemedy = 0;
       // List<Stack<String>> listOfStacks = new ArrayList<>();
        //List<Integer> numberOfRemedySymptoms = new ArrayList<>();
        List<String> symptoms = new ArrayList<>();;

        for (Remedy remedy : remedies) {
            symptoms.addAll(remedy.getSymptomMap().get(symptomClass));
/*            Collections.shuffle(symptoms);
            randomizedSymptoms = new Stack<>();
            randomizedSymptoms.addAll(symptoms);
            *//*for (String symptom : randomizedSymptoms) {
                logger.info(symptom);
            }*//*
            listOfStacks.add(randomizedSymptoms);
            numberOfRemedySymptoms.add(symptoms.size());*/
        }
        //highestNumberOfSymptomsInRemedy = Collections.max(numberOfRemedySymptoms);

        Collections.shuffle(symptoms);
        //randomizedSymptoms.addAll(symptoms);

        /*for (int i = 0; i < highestNumberOfSymptomsInRemedy; i++) {
            for (Stack<String> s : listOfStacks) {
                if (s.isEmpty()) {
                    continue;
                }
                listOfSymptoms.add(s.pop());
            }
        }*/

        return symptoms;

    }

    public List<Remedy> getRemedies() {
        return remedies;
    }
}
