package com.example.triviagame.constants;


import com.example.triviagame.Messages;

public class GameConstants {
    public static final Messages RIGHT_ANSWER = new Messages.MessagesBuilder()
            .setTitle("Trivia Game")
            .setHeader("Correct")
            .setContent("Right Answer").build();

    public static final Messages WRONG_ANSWER = new Messages.MessagesBuilder()
            .setTitle("Trivia Game")
            .setHeader("Wrong")
            .setContent("Wrong Answer").build();



}

