package com.example.triviagame;

import com.example.triviagame.alert.InformationMessagesAlert;
import com.example.triviagame.enums.InformationCode;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.triviagame.constants.GameConstants.*;

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

    public void setRightOrWrongConsequences(InformationMessagesAlert informationMessagesAlert,
                                            RadioButton selectedButton, String backGroundColor,
                                            InformationCode informationCode,
                                            Messages messages,
                                            int changePoints) {
        selectedButton.setStyle(backGroundColor);
        informationMessagesAlert.getAlert(informationCode, messages);
        setUserScore(changePoints);

    }

    public void setTheTextsInRadioButton(Text questionText,
                                          RadioButton ansOneRadioButton,
                                          RadioButton ansTwoRadioButton,
                                          RadioButton ansThreeRadioButton,
                                          RadioButton ansFourRadioButton,
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

    private void checkIfRightOrWrong(InformationMessagesAlert informationMessagesAlert,RadioButton selectedButton) {
        if (selectedButton.getText().equals(rightAnswer)) {
            setRightOrWrongConsequences(informationMessagesAlert, selectedButton,
                    FX_BACKGROUND_COLOR_FOREST_GREEN,
                    InformationCode.RIGHT_ANSWER,
                    RIGHT_ANSWER,
                    ADD_10_POINTS_TO_THE_SCORE);
        } else {
            setRightOrWrongConsequences(informationMessagesAlert, selectedButton,
                    FX_BACKGROUND_COLOR_FIRE_BRICK,
                    InformationCode.WRONG_ANSWER,
                    WRONG_ANSWER,
                    DECREASE_5_POINTS_FROM_THE_SCORE);
        }
    }

}
