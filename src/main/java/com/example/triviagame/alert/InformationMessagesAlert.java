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
            InformationCode.WRONG_ANSWER, this::createAlert
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
}
