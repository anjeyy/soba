package org.anjeyy.soba.resize;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

enum ResizeDirection {

    LEFT(Cursor.E_RESIZE) {
        @Override
        boolean isInsideBorder(AbstractWindowResizeListener abstractWindowResizeListener, MouseEvent event) {
            return abstractWindowResizeListener.isInsideLeftBorder(event);
        }
    },
    BOTTOM_LEFT(Cursor.SW_RESIZE) {
        @Override
        boolean isInsideBorder(AbstractWindowResizeListener abstractWindowResizeListener, MouseEvent event) {
            return abstractWindowResizeListener.isInsideLeftBottomBorder(event);
        }
    },
    BOTTOM(Cursor.S_RESIZE) {
        @Override
        boolean isInsideBorder(AbstractWindowResizeListener abstractWindowResizeListener, MouseEvent event) {
            return abstractWindowResizeListener.isInsideBottomBorder(event);
        }
    },
    BOTTOM_RIGHT(Cursor.SE_RESIZE) {
        @Override
        boolean isInsideBorder(AbstractWindowResizeListener abstractWindowResizeListener, MouseEvent event) {
            return abstractWindowResizeListener.isInsideRightBottomBorder(event);
        }
    },
    RIGHT(Cursor.W_RESIZE) {
        @Override
        boolean isInsideBorder(AbstractWindowResizeListener abstractWindowResizeListener, MouseEvent event) {
            return abstractWindowResizeListener.isInsideRightBorder(event);
        }
    };

    private final Cursor resizeCursor;

    ResizeDirection(Cursor resizeCursor) {
        this.resizeCursor = resizeCursor;
    }

    abstract boolean isInsideBorder(AbstractWindowResizeListener abstractWindowResizeListener, MouseEvent event);

    Cursor getResizeCursor() {
        return resizeCursor;
    }
}
