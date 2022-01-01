package org.anjeyy.soba.window;

import org.anjeyy.soba.common.Coordinate;

public class WindowToolbarController {

    private final WindowToolbarModel windowToolbarModel;

    public WindowToolbarController(WindowToolbarModel windowToolbarModel) {
        this.windowToolbarModel = windowToolbarModel;
    }

    void maximize() {
        windowToolbarModel.maximize();
    }

    void restoreMaximize() {
        windowToolbarModel.restoreMaximize();
    }

    void moveWindow(double x, double y) {
        Coordinate mouseDrag = Coordinate.from(x, y);
        windowToolbarModel.moveWindow(mouseDrag);
    }

    Coordinate restoreScreenBound() {
        return windowToolbarModel.restoreToBackupSize();
    }
}
