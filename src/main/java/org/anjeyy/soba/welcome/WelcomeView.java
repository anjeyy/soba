package org.anjeyy.soba.welcome;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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
        label.getStyleClass().add("");
        return label;
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
