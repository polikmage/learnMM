package org.mpo.homeopathy.symgen.quiz;

import org.mpo.homeopathy.symgen.gen.QuizGenerator;
import org.mpo.homeopathy.symgen.model.Question;
import org.mpo.homeopathy.symgen.model.Remedy;
import org.mpo.homeopathy.symgen.quiz.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGame {
    QuizGenerator quizGenerator;
    List<Remedy> remedies;
    List<String> symptoms;


    public QuizGame(List<Remedy> remedies, List<String> symptoms) {
        this.remedies = remedies;
        this.symptoms = symptoms;
        this.quizGenerator = new QuizGenerator(remedies, symptoms);
        //wrongQuestions = new ArrayList<>();
    }

    // todo ulozit nespravne zvolene otazky do listu a nabidnout na konci jejich znovu projiti.
    public void runGame(List<Question> questions) {
        List<Question> wrongQuestions = new ArrayList<>();
        ConsoleView.showPreText();
        int counter = 0;
        int correctAnswersNumber = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            counter = i + 1;
            ConsoleView.Answer correctAnswer = ConsoleView.showQuestion(currentQuestion, counter, questions.size());
            ConsoleView.showDescription();
            String key = readKey();
            if (key.equals("q")) {
                break;
            }
            if (getResult(key, correctAnswer)) {
                correctAnswersNumber++;
                ConsoleView.showResultAnswer(currentQuestion.getCorrectRemedy());
            } else {
                wrongQuestions.add(currentQuestion);
                ConsoleView.showWrongAnswer(currentQuestion.getCorrectRemedy());
            }
        }
        ConsoleView.showPostText(correctAnswersNumber, counter - 1);
        String key = readKey();
        if (key.equals("a")) {
            runGame(wrongQuestions);
        } else {

            System.exit(0);
        }
    }

    public void runGame() {
        List<Question> questions = this.generateQuestions();
        runGame(questions);
    }

    private List<Question> generateQuestions() {
        return quizGenerator.generateQuestions(symptoms.size());
    }

    private Boolean getResult(String key, ConsoleView.Answer answer) {
        if (key.equalsIgnoreCase(answer.toString())) {
            return true;
        } else {
            return false;
        }
    }


    public String readKey() {
        Scanner scan = new Scanner(System.in);

        //For string
        String text = scan.nextLine();
        //System.out.println(text);

        //for int
        /*
        int num= scan.nextInt();
        System.out.println(num);*/

        return text;
    }


}
