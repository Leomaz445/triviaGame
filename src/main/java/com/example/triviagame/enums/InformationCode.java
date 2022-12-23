package com.example.triviagame.enums;

public enum InformationCode {
    YOU_WON_THE_GAME(1),
    RIGHT_ANSWER(2),
    WRONG_ANSWER(3),
    FINAL_SCORE(4),
    START_NEW_GAME(5);

    private final int value;

    InformationCode(int value) {
        this.value = value;
    }
}
