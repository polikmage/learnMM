package org.mpo.homeopathy.symgen.model;

import java.util.List;

public class Question {
    String symptom;
    List<String> allRemedies;
    String correctRemedy;

    public Question(String symptom, List<String> allRemedies, String correctRemedy) {
        this.symptom = symptom;
        this.allRemedies = allRemedies;
        this.correctRemedy = correctRemedy;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public List<String> getAllRemedies() {
        return allRemedies;
    }

    public void setAllRemedies(List<String> allRemedies) {
        this.allRemedies = allRemedies;
    }

    public String getCorrectRemedy() {
        return correctRemedy;
    }

    public void setCorrectRemedy(String correctRemedy) {
        this.correctRemedy = correctRemedy;
    }
}
