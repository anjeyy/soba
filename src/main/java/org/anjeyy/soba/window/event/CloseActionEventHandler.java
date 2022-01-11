package org.anjeyy.soba.window.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.window.WindowToolbarController;
import org.anjeyy.soba.window.WindowToolbarModel;

public class CloseActionEventHandler implements EventHandler<ActionEvent> {

    private final WindowToolbarController windowToolbarController;
    private final WindowToolbarModel windowToolbarModel;

    private CloseActionEventHandler(
        WindowToolbarController windowToolbarController,
        WindowToolbarModel windowToolbarModel
    ) {
        this.windowToolbarController = windowToolbarController;
        this.windowToolbarModel = windowToolbarModel;
    }

    public static CloseActionEventHandler with(
        WindowToolbarController windowToolbarController,
        WindowToolbarModel windowToolbarModel
    ) {
        return new CloseActionEventHandler(windowToolbarController, windowToolbarModel);
    }

    @Override
    public void handle(ActionEvent event) {
        Stage stage = StageManager.extractStage(event);
        boolean alteredMaximize = alterMaximizeButton();
        stage.setMaximized(alteredMaximize);
    }

    private boolean alterMaximizeButton() {
        boolean isMaximized = windowToolbarModel.isMaximized();
        if (isMaximized) {
            windowToolbarController.restoreMaximize();
        } else {
            windowToolbarController.maximize();
        }
        return !isMaximized;
    }

}
