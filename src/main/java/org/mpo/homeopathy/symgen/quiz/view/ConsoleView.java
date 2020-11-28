package org.mpo.homeopathy.symgen.quiz.view;

import org.mpo.homeopathy.symgen.model.Question;

import java.util.List;

public class ConsoleView {

    public static void showResultAnswer(Answer correctAnswer) {
        printLnText("TREFA! Spravna odpoved je: "+correctAnswer);
    }

    public static void showWrongAnswer(Answer correctAnswer) {
        printLnText("Chyba spravna odpoved byla: "+correctAnswer);
    }

    public enum Answer {
        a(0), b(1), c(2), d(3);

        private int value;

        Answer(int value) {
            this.value = value;
        }

        public static Answer enumFromInt(int value) {
            for (Answer a : Answer.values()) {
                if (a.value == value) {
                    return a;
                }
            }
            return null;
        }
    }

    static final String liner = "*****************************************************";

    public static void showPreText() {
        printLnText("Vitejte v Materia Medica quizu, ceka vas prirazovani 20 symptomu k 4 lekum kdz jeden a vice je spravnych");
    }

    public static void showPostText(int correctAnswersNumber,int num) {
        printLnText("Vyborne vase skore je: "+ correctAnswersNumber +" z "+num);
    }


    public static Answer showQuestion(Question question, int i) {

        printLnText(liner);
        printLnText("Otazka cislo: " + i);
        printLnText(question.getSymptom());
        printLnText("Mozne odpovedi");

        return printAnswers(question);

    }

    private static void printLnText(String text) {
        System.out.println(text);
    }

    private static Answer printAnswers(Question question) {
        List<String> remedies = question.getAllRemedies();
        System.out.print(Answer.a + ": " + remedies.get(0) + ", ");
        System.out.print(Answer.b + ": " + remedies.get(1) + ", ");
        System.out.print(Answer.c + ": " + remedies.get(2) + ", ");
        System.out.print(Answer.d + ": " + remedies.get(3) + ", \n");
        int indexOfCorrectRemedy = remedies.indexOf(question.getCorrectRemedy());
        return Answer.enumFromInt(indexOfCorrectRemedy);
    }


    public static void showDescription() {
        System.out.print("Zvol [a,b,c,d] a enter : ");
    }
}
