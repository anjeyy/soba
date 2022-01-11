package org.anjeyy.soba.window;

import org.anjeyy.soba.common.Coordinate;

public class WindowToolbarModel {

    private Coordinate mouseDrag;
    private boolean maximized;

    public WindowToolbarModel() {
        this.mouseDrag = Coordinate.from(0, 0);
        this.maximized = false;
    }

    public boolean isMaximized() {
        return maximized;
    }

    void maximize() {
        maximized = true;
    }

    void restoreMaximize() {
        maximized = false;
    }

    void moveWindow(Coordinate mouseDrag) {
        this.mouseDrag = mouseDrag;
    }

    Coordinate restoreToBackupSize() {
        return this.mouseDrag;
    }

}
