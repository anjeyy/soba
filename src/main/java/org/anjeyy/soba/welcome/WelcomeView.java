package org.anjeyy.soba.welcome;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import org.anjeyy.soba.common.CustomStyleSheet;
import org.anjeyy.soba.common.ImageLoader;
import org.anjeyy.soba.common.MainView;
import org.anjeyy.soba.common.ScreenManager;

public class WelcomeView implements CustomStyleSheet, MainView {

    private final ImageView logoImageView;
    private final Label clickInfoLabel;
    private final BorderPane mainContainer;

    public WelcomeView() {
        this.logoImageView = createLogoImageView();
        this.clickInfoLabel = createClickInfoLabel();
        this.mainContainer = createBorderPane();
    }

    private ImageView createLogoImageView() {
        ImageView imageView = ImageLoader.asImageView("/image/soba-title-image.png");
        imageView.setId("entryScreenBackgroundImage");
        sizeScaleLogoImageView(imageView);
        return imageView;
    }

    private void sizeScaleLogoImageView(ImageView logoImageView) {
        logoImageView.setPreserveRatio(true);
        logoImageView.setFitWidth(ScreenManager.INSTANCE.getWidth() / 5);
        logoImageView.setFitHeight(ScreenManager.INSTANCE.getHeight() / 6);
        logoImageView.fitWidthProperty();
    }

    private Label createClickInfoLabel() {
        Label label = new Label("- click to continue -");
        label.setId("entryScreenClickLabel");
        CustomStyleSheet.super.add(label, "entry-screen-text");
        setupBlinking(label);
        return label;
    }

    private void setupBlinking(Label label) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(label);
        fadeTransition.setDuration(Duration.seconds(2.5));
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();  //todo observable from entry-screen -> model necessary
    }

    private BorderPane createBorderPane() {
        BorderPane borderPane = new BorderPane();
        borderPane.setId("entryScreenMainContainer");
        CustomStyleSheet.super.add(borderPane, "entry-screen");
        BorderPane.setAlignment(this.logoImageView, Pos.CENTER);
        borderPane.setTop(this.logoImageView);
        borderPane.setCenter(this.clickInfoLabel);
        adjustLogoImageViewTopPosition();
        initializeClickableEvent(borderPane);
        return borderPane;
    }

    private void adjustLogoImageViewTopPosition() {
        double screenHeight = ScreenManager.INSTANCE.getHeight() / 5;
        BorderPane.setMargin(this.logoImageView, new Insets(screenHeight, 0, 0, 0));
    }

    private void initializeClickableEvent(BorderPane borderPane) {
        //todo model?

        borderPane.setOnMouseClicked(e -> {
            //todo set new scene
        });

    }

    @Override
    public Parent asParent() {
        return mainContainer;
    }
}
