package com.example.triviagame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.example.triviagame.constants.GameConstants.*;

public class QuestionBank {
    private ArrayList<QuestionTemplate> gameQuestions;
    private ArrayList<QuestionTemplate> questionBeenAsked;

    public QuestionBank() {
        this.gameQuestions = new ArrayList<>();
        this.questionBeenAsked = new ArrayList<QuestionTemplate>();
    }

    public void setGameQuestions() {
        this.gameQuestions = getQuestionForTheGame();
    }

    public boolean haveQuestionsToAsk() {
        return !gameQuestions.isEmpty();
    }

    private ArrayList<QuestionTemplate> getQuestionForTheGame() {
        ArrayList<QuestionTemplate> gameQuestions = new ArrayList<QuestionTemplate>();
        try {
            BufferedReader wordsForHangManGame =
                    new BufferedReader(new BufferedReader(new FileReader("trivia.txt")));
            String question, ansOne, ansTwo, ansThree, ansFour;
            while (((question = wordsForHangManGame.readLine()) != null) &&
                    ((ansOne = wordsForHangManGame.readLine()) != null) &&
                    ((ansTwo = wordsForHangManGame.readLine()) != null) &&
                    ((ansThree = wordsForHangManGame.readLine()) != null) &&
                    ((ansFour = wordsForHangManGame.readLine()) != null)) {
                QuestionTemplate questionTemplate = new QuestionTemplate();
                questionTemplate.setQuestion(question);
                questionTemplate.setAnswerOne(ansOne);
                questionTemplate.setAnswerTwo(ansTwo);
                questionTemplate.setAnswerThree(ansThree);
                questionTemplate.setAnswerFour(ansFour);
                gameQuestions.add(questionTemplate);
            }
            wordsForHangManGame.close();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(ERROR_READING_FROM_THE_FILE);
        }
        return gameQuestions;
    }


    public QuestionTemplate getRandomQuestion() {
        Random random = new Random();
        QuestionTemplate questionTemplate = new QuestionTemplate();
        try {
            int randomNumber = random.nextInt(this.gameQuestions.size());
            questionTemplate = this.gameQuestions.get(randomNumber);
            this.gameQuestions.remove(randomNumber);
            this.questionBeenAsked.add(questionTemplate);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(ERROR_GETTING_QUESTION);
        }
        return questionTemplate;
    }


    public void initAllQuestions() {
        try {
            gameQuestions.addAll(questionBeenAsked);
        } catch (NullPointerException e) {
            System.out.println(THERE_WAS_A_PROBLEM_TO_COPY_THE_ARRAY_WITH_THE_OLD_QUESTIONS);
        }
        questionBeenAsked.clear();
    }
}
