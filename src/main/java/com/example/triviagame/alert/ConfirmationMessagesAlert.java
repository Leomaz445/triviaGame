package com.example.triviagame.alert;


import com.example.triviagame.Messages;
import com.example.triviagame.enums.ConfirmationCode;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.example.triviagame.constants.GameConstants.*;


public class ConfirmationMessagesAlert {

    private static final Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
    private final Map<ConfirmationCode, Supplier<Boolean>> mapOfConfirmation = new HashMap<ConfirmationCode, Supplier<Boolean>>() {{
        put(ConfirmationCode.DO_YOU_WANT_TO_PLAY_AGAIN, () -> createAlert(AGAIN));
        put(ConfirmationCode.ARE_YOU_SURE_YOU_WANT_TO_START_A_NEW_GAME, () -> createAlert(START_NEW_GAME));
        put(ConfirmationCode.DO_YOU_SURE_YOU_WANT_TO_EXIT, () -> createAlert(EXIT_GAME));
    }};


    public boolean getAlert(ConfirmationCode confirmationCode) {
        return mapOfConfirmation.get(confirmationCode).get();
    }

    private boolean createAlert(Messages messages) {
        alertConfirmation.setTitle(messages.getTitle());
        alertConfirmation.setHeaderText(messages.getHeader());
        alertConfirmation.setContentText(messages.getContent());
        Optional<ButtonType> option = alertConfirmation.showAndWait();
        return option.isPresent() && option.get() == ButtonType.YES;
    }


}
