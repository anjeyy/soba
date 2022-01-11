package org.anjeyy.soba.window.event;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.window.WindowToolbarController;

public class MousePressedActionEventHandler implements EventHandler<MouseEvent> {

    private final WindowToolbarController windowToolbarController;

    private MousePressedActionEventHandler(WindowToolbarController windowToolbarController) {
        this.windowToolbarController = windowToolbarController;
    }

    public static MousePressedActionEventHandler with(WindowToolbarController windowToolbarController) {
        return new MousePressedActionEventHandler(windowToolbarController);
    }

    @Override
    public void handle(MouseEvent event) {
        if (!event.isPrimaryButtonDown()) {
            return;
        }
        
        Stage stage = StageManager.extractStage(event);
        double xDiff = stage.getX() - event.getScreenX();
        double yDiff = stage.getY() - event.getScreenY();
        windowToolbarController.moveWindow(xDiff, yDiff);
    }

}
