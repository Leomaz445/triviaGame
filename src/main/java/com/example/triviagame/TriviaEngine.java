package com.example.triviagame;

import java.util.ArrayList;
import java.util.Collections;

public class TriviaEngine {
    private int userScore;
    private String rightAnswer;

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore += userScore;
        if (this.userScore < 0)
            this.userScore = 0;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public ArrayList<String> shuffle(QuestionTemplate randomQuestion) {
        this.rightAnswer = randomQuestion.getAnswerOne();
        ArrayList<String> shuffledAnswers = new ArrayList<String>();
        shuffledAnswers.add(randomQuestion.getAnswerOne());
        shuffledAnswers.add(randomQuestion.getAnswerTwo());
        shuffledAnswers.add(randomQuestion.getAnswerThree());
        shuffledAnswers.add(randomQuestion.getAnswerFour());
        Collections.shuffle(shuffledAnswers);
        return shuffledAnswers;
    }
}
