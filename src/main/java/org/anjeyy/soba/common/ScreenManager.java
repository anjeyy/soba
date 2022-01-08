package org.anjeyy.soba.common;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

public enum ScreenManager {

    INSTANCE;

    private double width = -1;
    private double height = -1;

    ScreenManager() {
        refresh();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean refresh() {
        Screen currentScreen = Screen.getPrimary();
        Rectangle2D screenBound = currentScreen.getBounds();
        boolean isRefreshed = isRefreshed(screenBound);
        if (isRefreshed) {
            setScreenSize(screenBound);
        }
        return isRefreshed;
    }

    private boolean isRefreshed(Rectangle2D screenBound) {
        double screenWidth = screenBound.getWidth();
        double screenHeight = screenBound.getHeight();
        return screenWidth != this.width || screenHeight != this.height;
    }

    private void setScreenSize(Rectangle2D screenBound) {
        double screenWidth = screenBound.getWidth();
        double screenHeight = screenBound.getHeight();
        this.width = screenWidth;
        this.height = screenHeight;
    }

    public Scene createScene(Parent root) {
        return new Scene(root, width * 3 / 4, height * 3 / 4);
    }

    public static Rectangle2D getPhysicalScreenBounds() {
        Screen currentScreen = Screen.getPrimary();
        return currentScreen.getBounds();
    }

}
