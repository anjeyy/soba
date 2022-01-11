package org.anjeyy.soba.window.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.anjeyy.soba.common.StageManager;

public class MinimizeActionEventHandler implements EventHandler<ActionEvent> {

    private MinimizeActionEventHandler() {}

    public static MinimizeActionEventHandler create() {
        return new MinimizeActionEventHandler();
    }

    @Override
    public void handle(ActionEvent event) {
        Stage stage = StageManager.extractStage(event);
        stage.setIconified(true);
    }

}
