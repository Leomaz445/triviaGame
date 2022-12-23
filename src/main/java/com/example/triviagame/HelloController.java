package com.example.triviagame;


import com.example.triviagame.alert.InformationMessagesAlert;
import com.example.triviagame.enums.InformationCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Consumer;

import static com.example.triviagame.constants.GameConstants.RIGHT_ANSWER;
import static com.example.triviagame.constants.GameConstants.WRONG_ANSWER;


public class HelloController {
    public static final String FX_BACKGROUND_COLOR_WHITE = "-fx-background-color: #ffffff";
    public static final String FX_BACKGROUND_COLOR_FOREST_GREEN = "-fx-background-color: #228B22";
    public static final String FX_BACKGROUND_COLOR_FIRE_BRICK = "-fx-background-color: #B22222";
    public static final String FX_BACKGROUND_NO_COLOR = "";
    //TO DO CHANGE THE NAME OF THE RADIO BUTTON
    //THINK ABOUT HOW TO SHUFFLE
    //HOW TO PIANT THE RIGHT ANS

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
    private Button checkAnswer;

    @FXML
    private Text scoreText;

    @FXML
    ToggleGroup answers;

    TriviaEngine triviaEngine;

    private final HashMap<String, Consumer<RadioButton>> buttonMap = new HashMap<>() {{
        put("ansOneRadioButton", (radioButton -> checkIfRightOrWrong(radioButton)));
        put("ansTwoRadioButton", (radioButton -> checkIfRightOrWrong(radioButton)));
        put("ansThreeRadioButton", (radioButton -> checkIfRightOrWrong(radioButton)));
        put("ansFourRadioButton", (radioButton -> checkIfRightOrWrong(radioButton)));
    }};

    private final HashMap<String,>

    // private ConfirmationMessagesAlert confirmationMessagesAlert;
    private InformationMessagesAlert informationMessagesAlert;

    @FXML
    void submitAnswer(ActionEvent event) {
        RadioButton chk = (RadioButton) answers.getSelectedToggle();
        buttonMap.get(chk.getId()).accept(chk);

    }

    private void checkIfRightOrWrong(RadioButton selectedButton) {
        if (selectedButton.getText().equals(triviaEngine.getRightAnswer())) {
            selectedButton.setStyle(FX_BACKGROUND_COLOR_FOREST_GREEN);
            informationMessagesAlert.getAlert(InformationCode.RIGHT_ANSWER, RIGHT_ANSWER);
            triviaEngine.setUserScore(10);
        } else {
            selectedButton.setStyle(FX_BACKGROUND_COLOR_FIRE_BRICK);
            informationMessagesAlert.getAlert(InformationCode.WRONG_ANSWER, WRONG_ANSWER);
            triviaEngine.setUserScore(-5);
        }
        scoreText.setText(String.valueOf(triviaEngine.getUserScore()));
        selectedButton.setSelected(false);
        selectedButton.setStyle(FX_BACKGROUND_NO_COLOR);
        setQuestion();
    }

    @FXML
    void onClickExitGame(ActionEvent event) {

    }

    @FXML
    void onClickStartNewGame(ActionEvent event) {

    }

    QuestionBank questionBank;

    public void setQuestion() {
        ArrayList<String> shuffledAnswers = new ArrayList<String>();
        QuestionTemplate randomQuestion = questionBank.getRandomQuestion();
        triviaEngine.setRightAnswer(randomQuestion.getAnswerOne());
        System.out.println(triviaEngine.getRightAnswer());
        questionText.setText(randomQuestion.getQuestion());
        shuffledAnswers.add(randomQuestion.getAnswerOne());
        shuffledAnswers.add(randomQuestion.getAnswerTwo());
        shuffledAnswers.add(randomQuestion.getAnswerThree());
        shuffledAnswers.add(randomQuestion.getAnswerFour());
        Collections.shuffle(shuffledAnswers);
        ansOneRadioButton.setText(shuffledAnswers.get(0));
        ansTwoRadioButton.setText(shuffledAnswers.get(1));
        ansThreeRadioButton.setText(shuffledAnswers.get(2));
        ansFourRadioButton.setText(shuffledAnswers.get(3));
        listen();

    }

    private void listen() {
        ansOneRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ansOneRadioButton.setStyle(FX_BACKGROUND_COLOR_WHITE);
                ansTwoRadioButton.setStyle("");
                ansThreeRadioButton.setStyle("");
                ansFourRadioButton.setStyle("");
            }
        });
        ansTwoRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ansOneRadioButton.setStyle("");
                ansTwoRadioButton.setStyle(FX_BACKGROUND_COLOR_WHITE);
                ansThreeRadioButton.setStyle("");
                ansFourRadioButton.setStyle("");
            }
        });
        ansThreeRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ansOneRadioButton.setStyle("");
                ansTwoRadioButton.setStyle("");
                ansThreeRadioButton.setStyle(FX_BACKGROUND_COLOR_WHITE);
                ansFourRadioButton.setStyle("");
            }
        });
        ansFourRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ansOneRadioButton.setStyle("");
                ansTwoRadioButton.setStyle("");
                ansThreeRadioButton.setStyle("");
                ansFourRadioButton.setStyle(FX_BACKGROUND_COLOR_WHITE);
            }
        });
    }


    public void initialize() {
        questionBank = new QuestionBank();
        triviaEngine = new TriviaEngine();
        //  confirmationMessagesAlert = new ConfirmationMessagesAlert();
        informationMessagesAlert = new InformationMessagesAlert();
        setQuestion();
        init();
    }

    private void init() {
        triviaEngine.setUserScore(0);
    }

}
