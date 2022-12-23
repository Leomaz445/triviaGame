package com.example.triviagame;

import java.util.Random;

public class TriviaEngine {
    private int userScore;
    private String rightAnswer;

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore+= userScore;
        if(this.userScore<0)
            this.userScore=0;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
