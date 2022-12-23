package com.example.triviagame.constants;


import com.example.triviagame.Messages;

public class GameConstants {
    public static final int SCENE_WIDTH = 600;
    public static final int SCENE_HEIGHT = 405;
    public static final String FX_BACKGROUND_COLOR_WHITE = "-fx-background-color: #ffffff";
    public static final String FX_BACKGROUND_COLOR_FOREST_GREEN = "-fx-background-color: #228B22";
    public static final String FX_BACKGROUND_COLOR_FIRE_BRICK = "-fx-background-color: #B22222";
    public static final String FX_BACKGROUND_NO_COLOR = "";
    public static final int ADD_10_POINTS_TO_THE_SCORE = 10;
    public static final int DECREASE_5_POINTS_FROM_THE_SCORE = -5;
    public static final String ANS_ONE_RADIO_BUTTON = "ansOneRadioButton";
    public static final String ANS_TWO_RADIO_BUTTON = "ansTwoRadioButton";
    public static final String ANS_THREE_RADIO_BUTTON = "ansThreeRadioButton";
    public static final String ANS_FOUR_RADIO_BUTTON = "ansFourRadioButton";
    public static final String THERE_WAS_A_PROBLEM_TO_COPY_THE_ARRAY_WITH_THE_OLD_QUESTIONS = "There was a problem to copy the array with the old questions";
    public static final String TRIVIA_GAME = "Trivia Game";
    public static final String CORRECT = "Correct";
    public static final String RIGHT_ANSWER1 = "Right Answer";
    public static final String WRONG = "Wrong";
    public static final String WRONG_ANSWER_CONTENT = "Wrong Answer";
    public static final String YOU_ARE_ABOUT_TO_LEAVE_THE_GAME = "You are about to leave the game";
    public static final String ARE_YOU_SURE = "Are you sure?";
    public static final String YOUR_ARE_ABOUT_TO_START_A_NEW_GAME = "Your Are about to start a new game";
    public static final String CONGRATULATIONS_YOU_FINISHED_THE_GAME = "Congratulations! You finished the game";
    public static final String DO_YOU_WANT_TO_PLAY_AGAIN = "Do you want to play again?";
    public static final String THANK_YOU_FOR_PLAYING = "Thank You For Playing";
    public static final String YOUR_FINAL_SCORE_IS = "Your Final Score is: ";
    public static final String NO_MORE_QUESTION = "No more Questions!";
    public static final String YOU_ARE_ABOUT_TO_START_FROM_THE_BEGINNING = "You are about to start the game again";
    public static final String YOUR_CURRENT_SCORE_IS = "Your current score is: ";
    public static final String MISSING_ANSWER_ONE = "missing answer one";
    public static final String MISSING_ANSWER_TWO = "missing answer two";
    public static final String MISSING_ANSWER_THREE = "missing answer three";
    public static final String MISSING_ANSWER_FOUR = "missing answer four";
    public static final String FILE_NOT_FOUND = "FILE NOT FOUND";
    public static final String ERROR_READING_FROM_THE_FILE = "error reading from the file";
    public static final String THERE_WAS_PROBLEM_TO_CAST = "cant cast";
    public static final Messages RIGHT_ANSWER = new Messages.MessagesBuilder()
            .setTitle(TRIVIA_GAME)
            .setHeader(CORRECT)
            .setContent(RIGHT_ANSWER1).build();

    public static final Messages WRONG_ANSWER = new Messages.MessagesBuilder()
            .setTitle(TRIVIA_GAME)
            .setHeader(WRONG)
            .setContent(WRONG_ANSWER_CONTENT).build();

    public static final Messages EXIT_GAME = new Messages.MessagesBuilder()
            .setTitle(TRIVIA_GAME)
            .setHeader(YOU_ARE_ABOUT_TO_LEAVE_THE_GAME)
            .setContent(ARE_YOU_SURE).build();

    public static final Messages START_NEW_GAME = new Messages.MessagesBuilder()
            .setTitle(TRIVIA_GAME)
            .setHeader(YOUR_ARE_ABOUT_TO_START_A_NEW_GAME)
            .setContent(ARE_YOU_SURE).build();

    public static final Messages AGAIN = new Messages.MessagesBuilder()
            .setTitle(TRIVIA_GAME)
            .setHeader(CONGRATULATIONS_YOU_FINISHED_THE_GAME)
            .setContent(DO_YOU_WANT_TO_PLAY_AGAIN).build();


}

