package com.example.triviagame.alert;


import com.example.triviagame.Messages;
import com.example.triviagame.enums.InformationCode;
import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.function.Consumer;


public class InformationMessagesAlert {

    private final static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    private final HashMap<InformationCode, Consumer<Messages>> mapOfInformation = new HashMap<InformationCode, Consumer<Messages>>() {{
        put(InformationCode.YOU_WON_THE_GAME, msg -> createAlert(msg));
        put(InformationCode.RIGHT_ANSWER, msg -> createAlert(msg));
        put(InformationCode.WRONG_ANSWER, msg -> createAlert(msg));
        put(InformationCode.FINAL_SCORE, msg -> createAlert(msg));
        put(InformationCode.START_NEW_GAME, msg -> createAlert(msg));
    }};

    public void getAlert(InformationCode alertCode, Messages messages) {
        mapOfInformation.get(alertCode).accept(messages);
    }


    private void createAlert(Messages messages) {
        alertInformation.setTitle(messages.getTitle());
        alertInformation.setHeaderText(messages.getHeader());
        alertInformation.setContentText(messages.getContent());
        alertInformation.showAndWait();
    }

    public void showCustomMessage(InformationCode informationCode, String title, String header, String content) {
        getAlert(informationCode, new Messages.MessagesBuilder()
                .setTitle(title)
                .setHeader(header)
                .setContent(content)
                .build());
    }
}
