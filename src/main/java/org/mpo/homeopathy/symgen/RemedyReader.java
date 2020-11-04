package org.mpo.homeopathy.symgen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RemedyReader {
    private static Logger logger = LoggerFactory.getLogger(RemedyReader.class);
    List<Remedy> remedies = new ArrayList<>();


    public List<Remedy> readAllRemedies(String pathToRemediesDir) throws FileNotFoundException {
        File dir = new File(pathToRemediesDir);
        String remedyName;
        String suffix;
        for (File file : dir.listFiles()) {
            Map<String,List<String>> mapOfSymptoms = new HashMap<>();
            List<String> remedySymptoms = new ArrayList<>();
            remedyName = file.getName().replace("_keynotes.txt","");
            suffix = "@"+remedyName+"@";
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.next()+suffix;
                //logger.info(line);
                remedySymptoms.add(line);
            }
            // do something with file
            s.close();
            String symptomClass =  file.getName().replace(remedyName+"_","").replace(".txt","");
            mapOfSymptoms.put(symptomClass,remedySymptoms);
            remedies.add(new Remedy(mapOfSymptoms,remedyName));

        }
        return remedies;
    }

    public static void main(String[] args) throws FileNotFoundException {
        RemedyReader remedyReader = new RemedyReader();
        remedyReader.readAllRemedies("C:\\Users\\marek.pola\\IdeaProjects\\LearnMM\\src\\main\\resources\\remedies");
        logger.info(remedyReader.remedies.toString());
    }

    public List<Remedy> getRemedies() {
        return remedies;
    }

    public void setRemedies(List<Remedy> remedies) {
        this.remedies = remedies;
    }
}
