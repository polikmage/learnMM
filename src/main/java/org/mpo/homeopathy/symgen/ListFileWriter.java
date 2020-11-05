package org.mpo.homeopathy.symgen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ListFileWriter {
    private static Logger logger = LoggerFactory.getLogger(ListFileWriter.class);
    Stack<String> symStack = new Stack<>();
    List<String> allSyms;
    int allSymsNumber;
    String pathToResultsDir;
    int counter = 0;
    private static final String genSymDir = "generated-symptoms";
    private static final String remResultDir = "remedies";
    private static final String resultsDir="results";
    private static final String symFileName = "symptom-quiz-";
    private static final String remFileName = "remedy-list";

    public ListFileWriter(List<String> allSyms) {
        createDirs();
        this.symStack.addAll(allSyms);
        this.allSymsNumber = symStack.size();
        this.allSyms = allSyms;
        deleteAllSymptomFiles();
    }

    private void writeSymptomsToFile(int maxNumberOfSymsInOneFile) {


        //abstraktni file objekt
        int increase = 1;
        File file = new File(pathToResultsDir +"\\"+ genSymDir +"\\"+ symFileName + increase + ".txt");


        //pokud existuje file s tou cestou na disku
        while (file.exists()) {
            increase++;
            file = new File(pathToResultsDir +"\\"+ genSymDir +"\\"+ symFileName + increase + ".txt");
        }
        if (!file.exists()) {
            try {

                file.createNewFile();

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);

                for (int i = 0; i < maxNumberOfSymsInOneFile; i++) {
                    if (!symStack.empty()) {
                        int lineNumber = ++counter;
                        bw.write(lineNumber + ". " + symStack.pop().replaceAll("\\@.*?\\@", ""));
                        bw.newLine();
                    }
                }
                bw.close();

                logger.info("Done writing to file: " + file.getName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void writeSymptomsToFiles(int maxNumberOfSymsInOneFile) {

        int modulo = allSymsNumber % maxNumberOfSymsInOneFile;
        int numberOfFiles = allSymsNumber / maxNumberOfSymsInOneFile;
        if (modulo != 0) {
            numberOfFiles++;
        }
        for (int i = 0; i < numberOfFiles; i++) {
            writeSymptomsToFile(maxNumberOfSymsInOneFile);
        }
    }

    private void deleteAllSymptomFiles() {
        File dir = new File(pathToResultsDir +"\\"+ genSymDir);
        for (File file : dir.listFiles())
            if (!file.isDirectory())
                file.delete();
    }

    private void deleteResultRemedyListFile() {
        /*File dir = new File(pathToResultsDir+remResultDir);
        for (File file : dir.listFiles())
            if (!file.isDirectory())
                file.delete();*/
        File file = new File(pathToResultsDir +"\\"+ remResultDir +"\\"+ remFileName + ".txt");
        file.delete();
    }

    public void writeRemediesListToFile() {
        deleteResultRemedyListFile();
        int lineNum = 0;
        //abstraktni file objekt
        File file = new File(pathToResultsDir +"\\"+ remResultDir +"\\"+ remFileName + ".txt");

        //pokud neexistuje file s tou cestou na disku
        if (!file.exists()) {
            try {
                file.createNewFile();

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                Collections.reverse(allSyms);
                for (int i = 0; i < allSyms.size(); i++) {
                    String s = allSyms.get(i);
                    s = s.substring(s.indexOf("@") + 1);
                    s = s.substring(0, s.indexOf("@"));

                    int lineNumber = ++lineNum;
                    bw.write(lineNumber + ". " + s);
                    bw.newLine();
                }
                bw.close();

                logger.info("Done writing to file: " + file.getName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void createDirs(){
        String currentDir = System.getProperty("user.dir");
        pathToResultsDir = currentDir+"\\"+resultsDir;
        File dirResults = new File(pathToResultsDir);
        File dirGenSyms = new File(pathToResultsDir+"\\"+genSymDir);
        File dirRemList = new File(pathToResultsDir+"\\"+ remResultDir);
        if (!dirResults.exists()){
            dirResults.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        if (!dirGenSyms.exists()){
            dirGenSyms.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        if (!dirRemList.exists()){
            dirRemList.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

    }

/*    public static void main(String[] args) throws IOException {
        logger.info(new java.io.File( "." ).getCanonicalPath());
        logger.info(System.getProperty("user.dir"));
        logger.info(Paths.get(".").toAbsolutePath().normalize().toString());
    }*/
}
