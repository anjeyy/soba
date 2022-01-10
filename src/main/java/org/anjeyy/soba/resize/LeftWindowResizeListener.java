package org.anjeyy.soba.resize;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LeftWindowResizeListener extends AbstractWindowResizeListener {

    public LeftWindowResizeListener(Stage stage) {
        super(stage, ResizeDirection.LEFT);
    }

    @Override
    final void handleMouseRelease(MouseEvent event) {
        if (!resize) {
            return;
        }
        double originalWidth = stage.getWidth();
        xOffset = event.getX();
        stage.setX(x + xOffset);
        stage.setWidth(originalWidth - xOffset);
        resize = false;
    }

}
