package com.example.triviagame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestionBank {
    private final ArrayList<QuestionTemplate> gameQuestions;
    private ArrayList<QuestionTemplate> questionBeenAsked;

    public QuestionBank() {
        this.gameQuestions = getQuestionForTheGame();
        this.questionBeenAsked = new ArrayList<QuestionTemplate>();
    }

    private ArrayList<QuestionTemplate> getQuestionForTheGame() {
        ArrayList<QuestionTemplate> gameQuestions = new ArrayList<QuestionTemplate>();
        try {
            BufferedReader wordsForHangManGame =
                    new BufferedReader(new BufferedReader(new FileReader("trivia.txt")));
            String line;
            while ((line = wordsForHangManGame.readLine()) != null) {
                QuestionTemplate questionTemplate = new QuestionTemplate();
                questionTemplate.setQuestion(line);
                try {
                    String ansOne = wordsForHangManGame.readLine();
                    questionTemplate.setAnswerOne(ansOne);
                } catch (NullPointerException e) {
                    System.out.println("missing answer one");
                }
                try {
                    String ansTwo = wordsForHangManGame.readLine();
                    questionTemplate.setAnswerTwo(ansTwo);
                } catch (NullPointerException e) {
                    System.out.println("missing answer two");
                }
                try {
                    String ansThree = wordsForHangManGame.readLine();
                    questionTemplate.setAnswerThree(ansThree);
                } catch (NullPointerException e) {
                    System.out.println("missing answer three");
                }
                try {
                    String ansFour = wordsForHangManGame.readLine();
                    questionTemplate.setAnswerFour(ansFour);
                } catch (NullPointerException e) {
                    System.out.println("missing answer four");
                }
                gameQuestions.add(questionTemplate);
            }
            wordsForHangManGame.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameQuestions;
    }


    public QuestionTemplate getRandomQuestion() {
        Random random = new Random();
        int randomNumber = random.nextInt(this.gameQuestions.size());
        QuestionTemplate questionTemplate = this.gameQuestions.get(randomNumber);
        this.gameQuestions.remove(randomNumber);
        this.questionBeenAsked.add(questionTemplate);
        return questionTemplate;
    }
}
