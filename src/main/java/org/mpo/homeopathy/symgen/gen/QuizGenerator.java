package org.mpo.homeopathy.symgen.gen;

import org.mpo.homeopathy.symgen.model.Question;
import org.mpo.homeopathy.symgen.model.Remedy;
import org.mpo.homeopathy.symgen.utils.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizGenerator {

    private List<Remedy> remedies;
    private List<String> symptoms;
    private List<Question> questions;


    public QuizGenerator(List<Remedy> remedies, List<String> symptoms) {
        this.remedies = remedies;
        this.symptoms = symptoms;
    }

    public List<Question> generateQuestions(int numberOfQuestions) {
        questions = new ArrayList<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            String correctRemedy = Util.getRemedyName(symptoms.get(i));
            Question question = new Question(symptoms.get(i).replaceAll("\\@.*?\\@", ""), generateRemedyPossibilities(correctRemedy), correctRemedy);
            questions.add(question);
        }
        return questions;
    }

    private List<String> generateRemedyPossibilities(String correctRemedyName) {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int rnd = random.nextInt(remedies.size());
            list.add(remedies.get(rnd).getName());
        }
        list.add(correctRemedyName);

        Collections.shuffle(list);

        return list;
    }
}
