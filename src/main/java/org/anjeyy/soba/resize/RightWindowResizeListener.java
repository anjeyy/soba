package org.anjeyy.soba.resize;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RightWindowResizeListener extends AbstractWindowResizeListener {

    public RightWindowResizeListener(Stage stage) {
        super(stage, ResizeDirection.RIGHT);
    }

    @Override
    void handleMouseRelease(MouseEvent event) {
        if (!resize) {
            return;
        }
        xOffset = event.getX();
        stage.setWidth(xOffset);
        resize = false;
    }

}
