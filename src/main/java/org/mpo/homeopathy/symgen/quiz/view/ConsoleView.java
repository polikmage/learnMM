package org.mpo.homeopathy.symgen.quiz.view;

import org.mpo.homeopathy.symgen.model.Question;

import java.util.List;

public class ConsoleView {

    public static void showResultAnswer(String correctAnswer) {
        printLnText("TREFA! Spravna odpoved je: " + correctAnswer);
    }

    public static void showWrongAnswer(String correctAnswer) {
        printLnText("Chyba spravna odpoved byla: " + correctAnswer);
    }

    public enum Answer {
        a(0), b(1), c(2), d(3), e(4);

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

    static final String liner = "************************************************************************************************************";

    public static void showPreText() {
        printLnText(liner);
        printLnText("Vitejte v Materia Medica quizu, ceka vas prirazovani symptomu k 5 lekum kdy jeden a vice je spravnych");

    }

    public static void showPostText(int correctAnswersNumber, int num) {
        System.out.println();
        printLnText("Výborně! Vaše skóre je: " + correctAnswersNumber + " z " + num);
        printLnText("Přejete si opakovat nesprávně zodpovězené otázky?[a/n]");
    }


    public static Answer showQuestion(Question question, int i, int size) {

        printLnText(liner);
        printLnText("Otazka cislo: " + i + " z " + size);
        printLnText(question.getSymptom());
        //printLnText("Mozne odpovedi");

        return printAnswers(question);

    }

    private static void printLnText(String text) {
        System.out.println(text);
    }

    private static Answer printAnswers(Question question) {
        List<String> remedies = question.getAllRemedies();
        System.out.print("[");
        for (int i = 0; i < remedies.size(); i++) {
            System.out.print(Answer.enumFromInt(i) + ": " + remedies.get(i));
            if (i < remedies.size()-1){
                System.out.print(", ");
            }

        }
        System.out.print("]");
        System.out.println();

        int indexOfCorrectRemedy = remedies.indexOf(question.getCorrectRemedy());
        return Answer.enumFromInt(indexOfCorrectRemedy);
    }


    public static void showDescription() {
        System.out.print("Zvol [a,b,c,d,e] a [q] pro ukonceni a enter : ");
    }
}
