package com.example.triviagame;


import com.example.triviagame.alert.InformationMessagesAlert;
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

    private QuestionBank questionBank;

    public void initialize() {
        questionBank = new QuestionBank();
        questionBank.setGameQuestions();
        triviaEngine = new TriviaEngine();
        init();
    }

    @FXML
    void onClickExitGame(ActionEvent event) {
        if (triviaEngine.userWantToExitTheGame())
            exit();
    }

    @FXML
    void onClickStartNewGame(ActionEvent event) {
        if (triviaEngine.userWantToStartNewGame())
            init();
    }

    private void init() {
        triviaEngine.setUserScore(0);
        scoreText.setText(String.valueOf(triviaEngine.getUserScore()));
        questionBank.initAllQuestions();
        followUserSelectedQuestionListener();
        setQuestion();
    }

    @FXML
    void submitAnswer(ActionEvent event) {
        try {
            RadioButton chk = (RadioButton) answers.getSelectedToggle();
            triviaEngine.checkRadioButton(chk);
            scoreText.setText(String.valueOf(triviaEngine.getUserScore()));
            setQuestion();
        } catch (ClassCastException e) {
            System.out.println(THERE_WAS_PROBLEM_TO_CAST);
        } catch (NullPointerException e) {
            userDidntSelectRadioButton();
        }
    }

    public void setQuestion() {
        if (questionBank.haveQuestionsToAsk()) {
            QuestionTemplate randomQuestion = questionBank.getRandomQuestion();
            ArrayList<String> shuffledAnswers = triviaEngine.shuffle(randomQuestion);
            triviaEngine.setTheTextsInRadioButton(questionText,
                    ansOneRadioButton,
                    ansTwoRadioButton,
                    ansThreeRadioButton,
                    ansFourRadioButton, randomQuestion, shuffledAnswers);
        } else {
            noMoreQuestionsFinishedGame();
        }
    }

    private void noMoreQuestionsFinishedGame() {
        if (triviaEngine.noMoreQuestionsWantToPlayAgain()) {
            init();
        } else
            exit();
    }

    void followUserSelectedQuestionListener() {
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

    private void userDidntSelectRadioButton() {
        InformationMessagesAlert informationMessagesAlert = new InformationMessagesAlert();
        informationMessagesAlert.getAlert(InformationCode.CHOOSE_ANSWER, SELECT_ANSWER);
    }
}
