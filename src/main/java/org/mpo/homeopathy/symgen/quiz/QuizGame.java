package org.mpo.homeopathy.symgen.quiz;

import org.mpo.homeopathy.symgen.gen.QuizGenerator;
import org.mpo.homeopathy.symgen.model.Question;
import org.mpo.homeopathy.symgen.model.Remedy;
import org.mpo.homeopathy.symgen.quiz.view.ConsoleView;

import java.util.List;
import java.util.Scanner;

public class QuizGame {
    //TODO v cyklu zobrazit 1 symptom a 4 leky jako moznosti, uzivatel bude psat pismena abcd.
    // Spravna odpoved se zobrazi okamzite po napsani pismena a enter. datova struktura bude  pole Question objektu.
    // Objekt Question ktery by obsahoval symptom, leky, oznaceni spravneho leku.
    // Nutno mit metodu pro generovani 3 nespravnych odpovedi

    QuizGenerator quizGenerator;
    List<Remedy> remedies;
    List<String> symptoms;
    private List<Question> questions;


    public QuizGame(List<Remedy> remedies, List<String> symptoms) {
        this.remedies = remedies;
        this.symptoms = symptoms;
        this.quizGenerator = new QuizGenerator(remedies,symptoms);
    }

    public void runGame(int numOfQuestions){
        questions = quizGenerator.generateQuestions(numOfQuestions);
        ConsoleView.showPreText();

        for (int i = 0; i < questions.size(); i++) {
            ConsoleView.showQuestion(questions.get(i),i);
        }

        ConsoleView.showPostText();
    }


    public static String readKey(){
        Scanner scan= new Scanner(System.in);

        //For string
        String text= scan.nextLine();
        System.out.println(text);

        //for int
        /*
        int num= scan.nextInt();
        System.out.println(num);*/

        return text;
    }



}
