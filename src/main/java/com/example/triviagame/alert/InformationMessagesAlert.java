package com.example.triviagame.alert;


import com.example.triviagame.Messages;
import com.example.triviagame.enums.InformationCode;
import javafx.scene.control.Alert;

import java.util.Map;
import java.util.function.Consumer;


public class InformationMessagesAlert {

    private final static Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
    private final Map<InformationCode, Consumer<Messages>> mapOfInformation = Map.of(
            InformationCode.YOU_WON_THE_GAME, this::createAlert,
            InformationCode.RIGHT_ANSWER, this::createAlert,
            InformationCode.WRONG_ANSWER, this::createAlert,
            InformationCode.FINAL_SCORE,this::createAlert,
            InformationCode.START_NEW_GAME,this::createAlert
    );

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
