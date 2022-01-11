package org.anjeyy.soba.window;

import org.anjeyy.soba.common.Coordinate;

public class WindowToolbarController {

    private final WindowToolbarModel windowToolbarModel;

    public WindowToolbarController(WindowToolbarModel windowToolbarModel) {
        this.windowToolbarModel = windowToolbarModel;
    }

    public void maximize() {
        windowToolbarModel.maximize();
    }

    public void restoreMaximize() {
        windowToolbarModel.restoreMaximize();
    }

    public void moveWindow(double x, double y) {
        Coordinate mouseDrag = Coordinate.from(x, y);
        windowToolbarModel.moveWindow(mouseDrag);
    }

    public Coordinate restoreScreenBound() {
        return windowToolbarModel.restoreToBackupSize();
    }
}
