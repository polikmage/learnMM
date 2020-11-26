package org.mpo.homeopathy.symgen.quiz.view;

import org.mpo.homeopathy.symgen.model.Question;

import java.util.List;

public class ConsoleView {

    static final String liner = "*****************************************************";

    public static void showPreText(){
        printLnText("Vitejte v Materia Medica quizu, ceka vas prirazovani 20 symptomu k 4 lekum kdz jeden a vice je spravnych");
    }
    public static void showPostText(){
        printLnText("Vyborne vase skore je:");
    }



    public static void showQuestion(Question question, int i){

        printLnText(liner);
        printLnText("Otazka cislo: "+i);
        printLnText(question.getSymptom());
        printLnText("Mozne odpovedi");
        printAnswers(question.getAllRemedies());


    }

    private static void printLnText(String text){
        System.out.println(text);
    }

    private static void printAnswers(List<String> remedies){
        System.out.print("A: "+ remedies.get(0)+", ");
        System.out.print("B: "+ remedies.get(1)+", ");
        System.out.print("C: "+ remedies.get(2)+", ");
        System.out.print("D: "+ remedies.get(3)+", \n");
    }


}
