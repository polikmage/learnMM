package org.mpo.homeopathy.symgen.quiz;

import org.mpo.homeopathy.symgen.gen.QuizGenerator;
import org.mpo.homeopathy.symgen.model.Question;
import org.mpo.homeopathy.symgen.model.Remedy;
import org.mpo.homeopathy.symgen.quiz.view.ConsoleView;

import java.util.List;
import java.util.Scanner;

public class QuizGame {
    //TODO zkontrolovat generovani doplnkovych odpovedi, zprehlednit quiz, zobrazit spravny lek a ne pismeno, zkontrolovat generator symptomu, je tam prilis casto nat mur
    // u remedy posibilities generovat mapu vcetne a,b,c,d
    QuizGenerator quizGenerator;
    List<Remedy> remedies;
    List<String> symptoms;
    private List<Question> questions;
    private int correctAnswersNumber = 0;


    public QuizGame(List<Remedy> remedies, List<String> symptoms) {
        this.remedies = remedies;
        this.symptoms = symptoms;
        this.quizGenerator = new QuizGenerator(remedies,symptoms);
    }

    public void runGame(){
        //TODO pocet otazek volit na zacatku quizu a nebo ho ukoncit q
        questions = quizGenerator.generateQuestions(100);
        ConsoleView.showPreText();
        int counter = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            counter = i+1;
            ConsoleView.Answer correctAnswer= ConsoleView.showQuestion(currentQuestion,counter);
            ConsoleView.showDescription();
            String key = readKey();
            if(key.equals("q")){
                break;
            }
            if(getResult(key,correctAnswer)){
                ConsoleView.showResultAnswer(currentQuestion.getCorrectRemedy());
            }else {
                ConsoleView.showWrongAnswer(currentQuestion.getCorrectRemedy());
            }
        }
        ConsoleView.showPostText(correctAnswersNumber,counter);
    }

    private Boolean getResult(String key, ConsoleView.Answer answer) {
        if (key.equalsIgnoreCase(answer.toString())){
            correctAnswersNumber++;
            return true;
        }else{
            return false;
        }
    }


    public String readKey(){
        Scanner scan= new Scanner(System.in);

        //For string
        String text= scan.nextLine();
        //System.out.println(text);

        //for int
        /*
        int num= scan.nextInt();
        System.out.println(num);*/

        return text;
    }



}
