package org.anjeyy.soba.screen;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.anjeyy.soba.common.StageManager;


public enum ScreenModel {

    INSTANCE;

    private final double initialWidth;
    private final double initialHeight;
    private final ReadOnlyDoubleProperty width;
    private final ReadOnlyDoubleProperty height;

    ScreenModel() {
        this.initialWidth = createInitialScreenWidth();
        this.initialHeight = createInitialScreenHeight();
        Stage currentStage = StageManager.getCurrentStage();
        this.width = currentStage.widthProperty();
        this.height = currentStage.heightProperty();
    }

    public static double createInitialScreenWidth() {
        Rectangle2D screenBound = getScreenBound();
        double screenWidth = screenBound.getWidth();
        return screenWidth * 3 / 4;
    }

    public static double createInitialScreenHeight() {
        Rectangle2D screenBound = getScreenBound();
        double screenWidth = screenBound.getHeight();
        return screenWidth * 3 / 4;
    }

    private static Rectangle2D getScreenBound() {
        Screen currentScreen = Screen.getPrimary();
        return currentScreen.getBounds();
    }

    public double getInitialWidth() {
        return initialWidth;
    }

    public double getInitialHeight() {
        return initialHeight;
    }

    public ReadOnlyDoubleProperty widthProperty() {
        return width;
    }

    public ReadOnlyDoubleProperty heightProperty() {
        return height;
    }
}
