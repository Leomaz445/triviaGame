package com.example.triviagame.enums;

public enum ConfirmationCode {
    DO_YOU_WANT_TO_PLAY_AGAIN(1),
    ARE_YOU_SURE_YOU_WANT_TO_START_A_NEW_GAME(2),
    DO_YOU_SURE_YOU_WANT_TO_EXIT(3);

    private final int value;

    ConfirmationCode(int value) {
        this.value = value;
    }
}
