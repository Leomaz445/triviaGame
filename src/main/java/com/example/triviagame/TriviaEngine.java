package com.example.triviagame;

import com.example.triviagame.alert.ConfirmationMessagesAlert;
import com.example.triviagame.alert.InformationMessagesAlert;
import com.example.triviagame.enums.ConfirmationCode;
import com.example.triviagame.enums.InformationCode;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Consumer;

import static com.example.triviagame.constants.GameConstants.*;

public class TriviaEngine {
    private int userScore;
    private String rightAnswer;
    private final ConfirmationMessagesAlert confirmationMessagesAlert;
    private final InformationMessagesAlert informationMessagesAlert;
    private final HashMap<String, Consumer<RadioButton>> buttonMap = new HashMap<String, Consumer<RadioButton>>() {{
        put(ANS_ONE_RADIO_BUTTON, (radioButton -> checkAnswer(radioButton)));
        put(ANS_TWO_RADIO_BUTTON, (radioButton -> checkAnswer(radioButton)));
        put(ANS_THREE_RADIO_BUTTON, (radioButton -> checkAnswer(radioButton)));
        put(ANS_FOUR_RADIO_BUTTON, (radioButton -> checkAnswer(radioButton)));
    }};

    public TriviaEngine() {
        this.userScore = 0;
        confirmationMessagesAlert = new ConfirmationMessagesAlert();
        informationMessagesAlert = new InformationMessagesAlert();
    }

    public void checkRadioButton(RadioButton selectedButton) {
        buttonMap.get(selectedButton.getId()).accept(selectedButton);
    }

    private void checkAnswer(RadioButton selectedButton) {
        if (selectedButton.getText().equals(rightAnswer)) {
            setRightOrWrongConsequences(selectedButton,
                    FX_BACKGROUND_COLOR_FOREST_GREEN,
                    InformationCode.RIGHT_ANSWER,
                    RIGHT_ANSWER,
                    ADD_10_POINTS_TO_THE_SCORE);
        } else {
            setRightOrWrongConsequences(selectedButton,
                    FX_BACKGROUND_COLOR_FIRE_BRICK,
                    InformationCode.WRONG_ANSWER,
                    WRONG_ANSWER,
                    DECREASE_5_POINTS_FROM_THE_SCORE);
        }
        updateResultScreen(selectedButton);
    }


    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public void calcUserScore(int userScore) {
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

    public void setRightOrWrongConsequences(RadioButton selectedButton, String backGroundColor,
                                            InformationCode informationCode,
                                            Messages messages,
                                            int changePoints) {
        selectedButton.setStyle(backGroundColor);
        informationMessagesAlert.getAlert(informationCode, messages);
        calcUserScore(changePoints);

    }

    public void setTheTextsInRadioButton(Text questionText, RadioButton ansOneRadioButton, RadioButton ansTwoRadioButton,
                                         RadioButton ansThreeRadioButton, RadioButton ansFourRadioButton,
                                         QuestionTemplate randomQuestion,
                                         ArrayList<String> shuffledAnswers) {
        questionText.setText(randomQuestion.getQuestion());
        ansOneRadioButton.setText(shuffledAnswers.get(0));
        ansTwoRadioButton.setText(shuffledAnswers.get(1));
        ansThreeRadioButton.setText(shuffledAnswers.get(2));
        ansFourRadioButton.setText(shuffledAnswers.get(3));
    }

    public void updateResultScreen(RadioButton selectedButton) {
        selectedButton.setSelected(false);
        selectedButton.setStyle(FX_BACKGROUND_NO_COLOR);
    }

    public boolean userWantToExitTheGame() {
        if (confirmationMessagesAlert.getAlert(ConfirmationCode.DO_YOU_SURE_YOU_WANT_TO_EXIT)) {
            informationMessagesAlert.showCustomMessage(InformationCode.FINAL_SCORE,
                    TRIVIA_GAME,
                    THANK_YOU_FOR_PLAYING,
                    YOUR_FINAL_SCORE_IS + userScore);
            return true;
        }
        return false;
    }

    public boolean userWantToStartNewGame() {
        if (confirmationMessagesAlert.getAlert(ConfirmationCode.ARE_YOU_SURE_YOU_WANT_TO_START_A_NEW_GAME)) {
            informationMessagesAlert.showCustomMessage(InformationCode.START_NEW_GAME,
                    TRIVIA_GAME,
                    YOU_ARE_ABOUT_TO_START_FROM_THE_BEGINNING,
                    YOUR_CURRENT_SCORE_IS + userScore);
            return true;
        }
        return false;
    }

    public boolean noMoreQuestionsWantToPlayAgain() {
        informationMessagesAlert.showCustomMessage(InformationCode.YOU_WON_THE_GAME,
                TRIVIA_GAME,
                NO_MORE_QUESTION,
                YOUR_CURRENT_SCORE_IS + userScore);
        return confirmationMessagesAlert.getAlert(ConfirmationCode.DO_YOU_WANT_TO_PLAY_AGAIN);
    }

}
