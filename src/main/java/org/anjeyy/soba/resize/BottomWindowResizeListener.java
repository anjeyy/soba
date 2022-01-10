package org.anjeyy.soba.resize;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BottomWindowResizeListener extends AbstractWindowResizeListener {

    public BottomWindowResizeListener(Stage stage) {
        super(stage, ResizeDirection.BOTTOM);
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
