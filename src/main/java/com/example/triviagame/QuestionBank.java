package com.example.triviagame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static com.example.triviagame.constants.GameConstants.*;

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
                    new BufferedReader(new BufferedReader(new FileReader("trivia2.txt")));
            String line;
            while ((line = wordsForHangManGame.readLine()) != null) {
                QuestionTemplate questionTemplate = new QuestionTemplate();
                questionTemplate.setQuestion(line);
                try {
                    String ansOne = wordsForHangManGame.readLine();
                    questionTemplate.setAnswerOne(ansOne);
                } catch (NullPointerException e) {
                    System.out.println(MISSING_ANSWER_ONE);
                }
                try {
                    String ansTwo = wordsForHangManGame.readLine();
                    questionTemplate.setAnswerTwo(ansTwo);
                } catch (NullPointerException e) {
                    System.out.println(MISSING_ANSWER_TWO);
                }
                try {
                    String ansThree = wordsForHangManGame.readLine();
                    questionTemplate.setAnswerThree(ansThree);
                } catch (NullPointerException e) {
                    System.out.println(MISSING_ANSWER_THREE);
                }
                try {
                    String ansFour = wordsForHangManGame.readLine();
                    questionTemplate.setAnswerFour(ansFour);
                } catch (NullPointerException e) {
                    System.out.println(MISSING_ANSWER_FOUR);
                }
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


    public Optional<QuestionTemplate> getRandomQuestion() {
        Optional<QuestionTemplate> questionTemplate;
        Random random = new Random();
        try {
            int randomNumber = random.nextInt(this.gameQuestions.size());
            questionTemplate = Optional.ofNullable(this.gameQuestions.get(randomNumber));
            if (questionTemplate.isPresent()) {
                this.gameQuestions.remove(randomNumber);
                this.questionBeenAsked.add(questionTemplate.get());
            }
        } catch (IllegalArgumentException e) {
            return Optional.empty();
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
