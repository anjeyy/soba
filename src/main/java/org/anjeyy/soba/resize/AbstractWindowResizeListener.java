package org.anjeyy.soba.resize;

import java.util.List;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public abstract class AbstractWindowResizeListener implements EventHandler<MouseEvent> {

    static final double RESIZE_BORDER = 5;

    final Stage stage;
    final ResizeDirection resizeDirection;

    boolean resize = false;
    double x;
    double y;
    double xOffset;
    double yOffset;

    AbstractWindowResizeListener(Stage stage, ResizeDirection resizeDirection) {
        this.stage = stage;
        this.resizeDirection = resizeDirection;
    }

    @Override
    public void handle(MouseEvent event) {
        EventType<? extends MouseEvent> mouseEventType = event.getEventType();

        if (MouseEvent.MOUSE_MOVED == mouseEventType) {
            handleMouseMovement(event);
        } else if (MouseEvent.MOUSE_EXITED == mouseEventType) {
            changeCursor(Cursor.DEFAULT);
        } else if (MouseEvent.MOUSE_PRESSED == mouseEventType) {
            handleMousePressed(event);
        } else if (MouseEvent.MOUSE_DRAGGED == mouseEventType && resize) {
            Cursor cursor = resizeDirection.getResizeCursor();
            changeCursor(cursor);
        } else if (MouseEvent.MOUSE_RELEASED == mouseEventType && resize) {
            handleMouseRelease(event);
        }
    }

    private void handleMousePressed(MouseEvent event) {
        if (resizeDirection.isInsideBorder(this, event)) {
            resize = true;
            x = stage.getX();
            y = stage.getY();
        }
    }

    void handleMouseMovement(MouseEvent event) {
        if (isInsideRightBottomBorder(event)) {
            changeCursor(Cursor.SE_RESIZE);
        } else if (isInsideLeftBottomBorder(event)) {
            changeCursor(Cursor.SW_RESIZE);
        } else if (isInsideLeftBorder(event)) {
            changeCursor(Cursor.W_RESIZE);
        } else if (isInsideBottomBorder(event)) {
            changeCursor(Cursor.S_RESIZE);
        } else if (isInsideRightBorder(event)) {
            changeCursor(Cursor.E_RESIZE);
        } else {
            changeCursor(Cursor.DEFAULT);
        }
    }

    void changeCursor(Cursor currentCursor) {
        stage.getScene().setCursor(currentCursor);
    }

    final boolean isInsideRightBottomBorder(MouseEvent event) {
        return isInsideRightBorder(event) && isInsideBottomBorder(event);
    }

    final boolean isInsideLeftBottomBorder(MouseEvent event) {
        return isInsideLeftBorder(event) && isInsideBottomBorder(event);
    }

    final boolean isInsideRightBorder(MouseEvent event) {
        double xMousePos = event.getX();
        double rightResizeBorder = stage.getWidth() - RESIZE_BORDER;
        return xMousePos > rightResizeBorder;
    }

    final boolean isInsideBottomBorder(MouseEvent event) {
        double yMousePos = event.getY();
        double bottomResizeBorder = stage.getHeight() - RESIZE_BORDER;
        return yMousePos > bottomResizeBorder;
    }

    final boolean isInsideLeftBorder(MouseEvent event) {
        double xMousePos = event.getX();
        return xMousePos < RESIZE_BORDER;
    }

    abstract void handleMouseRelease(MouseEvent event);

    public static List<EventType<MouseEvent>> relevantMouseEvents() {
        return List.of(
            MouseEvent.MOUSE_MOVED,
            MouseEvent.MOUSE_EXITED,
            MouseEvent.MOUSE_PRESSED,
            MouseEvent.MOUSE_DRAGGED,
            MouseEvent.MOUSE_RELEASED
        );
    }

}
