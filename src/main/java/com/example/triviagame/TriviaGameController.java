package com.example.triviagame;


import com.example.triviagame.alert.ConfirmationMessagesAlert;
import com.example.triviagame.alert.InformationMessagesAlert;
import com.example.triviagame.enums.ConfirmationCode;
import com.example.triviagame.enums.InformationCode;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;

import static com.example.triviagame.constants.GameConstants.*;
import static javafx.application.Platform.exit;


public class TriviaGameController {
    @FXML
    private RadioButton ansOneRadioButton;

    @FXML
    private RadioButton ansTwoRadioButton;

    @FXML
    private RadioButton ansThreeRadioButton;

    @FXML
    private RadioButton ansFourRadioButton;

    @FXML
    private Text questionText;

    @FXML
    private Text scoreText;

    @FXML
    private ToggleGroup answers;

    private TriviaEngine triviaEngine;
    private ConfirmationMessagesAlert confirmationMessagesAlert;
    private InformationMessagesAlert informationMessagesAlert;
    private QuestionBank questionBank;

    private final HashMap<String, Consumer<RadioButton>> buttonMap = new HashMap<>() {{
        put(ANS_ONE_RADIO_BUTTON, (radioButton -> checkIfRightOrWrong(radioButton)));
        put(ANS_TWO_RADIO_BUTTON, (radioButton -> checkIfRightOrWrong(radioButton)));
        put(ANS_THREE_RADIO_BUTTON, (radioButton -> checkIfRightOrWrong(radioButton)));
        put(ANS_FOUR_RADIO_BUTTON, (radioButton -> checkIfRightOrWrong(radioButton)));
    }};


    @FXML
    void onClickExitGame(ActionEvent event) {
        if (confirmationMessagesAlert.getAlert(ConfirmationCode.DO_YOU_SURE_YOU_WANT_TO_EXIT)) {
            informationMessagesAlert.showCustomMessage(InformationCode.FINAL_SCORE,
                    TRIVIA_GAME,
                    THANK_YOU_FOR_PLAYING,
                    YOUR_FINAL_SCORE_IS + triviaEngine.getUserScore());
            exit();
        }

    }

    @FXML
    void onClickStartNewGame(ActionEvent event) {
        if (confirmationMessagesAlert.getAlert(ConfirmationCode.ARE_YOU_SURE_YOU_WANT_TO_START_A_NEW_GAME)) {
            informationMessagesAlert.showCustomMessage(InformationCode.START_NEW_GAME,
                    TRIVIA_GAME,
                    YOU_ARE_ABOUT_TO_START_FROM_THE_BEGINNING,
                    YOUR_CURRENT_SCORE_IS + triviaEngine.getUserScore());
            init();
        }
    }

    public void initialize() {
        questionBank = new QuestionBank();
        triviaEngine = new TriviaEngine();
        confirmationMessagesAlert = new ConfirmationMessagesAlert();
        informationMessagesAlert = new InformationMessagesAlert();
        init();
    }

    private void init() {
        triviaEngine.setUserScore(0);
        questionBank.initAllQuestions();
        followUserSelectedQuestionLister();
        setQuestion();
    }

    @FXML
    void submitAnswer(ActionEvent event) {
        RadioButton chk = (RadioButton) answers.getSelectedToggle();
        buttonMap.get(chk.getId()).accept(chk);

    }

    private void checkIfRightOrWrong(RadioButton selectedButton) {
        if (selectedButton.getText().equals(triviaEngine.getRightAnswer())) {
            triviaEngine.setRightOrWrongConsequences(informationMessagesAlert, selectedButton,
                    FX_BACKGROUND_COLOR_FOREST_GREEN,
                    InformationCode.RIGHT_ANSWER,
                    RIGHT_ANSWER,
                    ADD_10_POINTS_TO_THE_SCORE);
        } else {
            triviaEngine.setRightOrWrongConsequences(informationMessagesAlert, selectedButton,
                    FX_BACKGROUND_COLOR_FIRE_BRICK,
                    InformationCode.WRONG_ANSWER,
                    WRONG_ANSWER,
                    DECREASE_5_POINTS_FROM_THE_SCORE);
        }
        triviaEngine.updateResultScreen(selectedButton);
        scoreText.setText(String.valueOf(triviaEngine.getUserScore()));
        setQuestion();

    }

    public void setQuestion() {
        Optional<QuestionTemplate> randomQuestion = questionBank.getRandomQuestion();
        if (randomQuestion.isPresent()) {
            ArrayList<String> shuffledAnswers = triviaEngine.shuffle(randomQuestion.get());
            System.out.println(triviaEngine.getRightAnswer());
            triviaEngine.setTheTextsInRadioButton(questionText,
                    ansOneRadioButton,
                    ansTwoRadioButton,
                    ansThreeRadioButton,
                    ansFourRadioButton, randomQuestion.get(), shuffledAnswers);
        } else {
            noMoreQuestionsFinishedGame();
        }
    }

    private void noMoreQuestionsFinishedGame() {
        informationMessagesAlert.showCustomMessage(InformationCode.YOU_WON_THE_GAME,
                TRIVIA_GAME,
                YOU_ARE_ABOUT_TO_START_FROM_THE_BEGINNING,
                YOUR_CURRENT_SCORE_IS + triviaEngine.getUserScore());
        if (!confirmationMessagesAlert.getAlert(ConfirmationCode.DO_YOU_WANT_TO_PLAY_AGAIN)) {
            exit();
        }
        init();
    }

    void followUserSelectedQuestionLister() {
        answers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                try {
                    if (newValue != null) {
                        RadioButton currentlyPressed = (RadioButton) newValue;
                        currentlyPressed.setStyle(FX_BACKGROUND_COLOR_WHITE);
                        if (oldValue != null) {
                            RadioButton oldPressedButton = (RadioButton) oldValue;
                            if (!currentlyPressed.equals(oldPressedButton))
                                oldPressedButton.setStyle(FX_BACKGROUND_NO_COLOR);
                        }
                    }
                } catch (ClassCastException e) {
                    System.out.println(THERE_WAS_PROBLEM_TO_CAST);
                }
            }
        });
    }
}
