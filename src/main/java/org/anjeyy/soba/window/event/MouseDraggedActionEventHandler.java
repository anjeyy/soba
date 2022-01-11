package org.anjeyy.soba.window.event;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.anjeyy.soba.common.Coordinate;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.window.WindowToolbarController;

public class MouseDraggedActionEventHandler implements EventHandler<MouseEvent> {

    private final WindowToolbarController windowToolbarController;

    private MouseDraggedActionEventHandler(WindowToolbarController windowToolbarController) {
        this.windowToolbarController = windowToolbarController;
    }

    public static MouseDraggedActionEventHandler with(WindowToolbarController windowToolbarController) {
        return new MouseDraggedActionEventHandler(windowToolbarController);
    }

    @Override
    public void handle(MouseEvent event) {
        if (!event.isPrimaryButtonDown()) {
            return;
        }

        Stage stage = StageManager.extractStage(event);
        Coordinate mouseDrag = windowToolbarController.restoreScreenBound();
        stage.setX(event.getScreenX() + mouseDrag.x());
        stage.setY(event.getScreenY() + mouseDrag.y());
    }

}
