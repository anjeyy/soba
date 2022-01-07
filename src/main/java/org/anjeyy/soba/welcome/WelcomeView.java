package org.anjeyy.soba.welcome;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import org.anjeyy.soba.common.CustomStyleSheet;
import org.anjeyy.soba.common.MainView;

public class WelcomeView implements CustomStyleSheet, MainView {

    private final Label clickInfoLabel;
    private final BorderPane mainContainer;

    public WelcomeView() {
        this.clickInfoLabel = createClickInfoLabel();
        this.mainContainer = createBorderPane();
    }

    // ToDo: welcome image (soba logo)

    private Label createClickInfoLabel() {
        Label label = new Label("- click to continue -");
        CustomStyleSheet.super.add(label, "entry-screen-text");
        blink(label);
        return label;
    }

    private void blink(Label label) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2.5), label);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
    }

    private BorderPane createBorderPane() {
        BorderPane borderPane = new BorderPane(this.clickInfoLabel);
        CustomStyleSheet.super.add(borderPane, "entry-screen");
        return borderPane;
    }

    @Override
    public Parent asParent() {
        return mainContainer;
    }
}
