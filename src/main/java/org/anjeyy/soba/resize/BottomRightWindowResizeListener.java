package org.anjeyy.soba.resize;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BottomRightWindowResizeListener extends AbstractWindowResizeListener {

    public BottomRightWindowResizeListener(Stage stage) {
        super(stage, ResizeDirection.BOTTOM_RIGHT);
    }

    @Override
    final void handleMouseRelease(MouseEvent event) {
        if (!resize) {
            return;
        }
        yOffset = event.getY();
        stage.setHeight(yOffset);
        resize = false;
    }

}
