package org.anjeyy.soba.welcome;

import java.util.Objects;
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
import org.anjeyy.soba.scene.SceneManager;
import org.anjeyy.soba.screen.ScreenModel;

public class WelcomeView implements CustomStyleSheet, MainView {

    private final WelcomeController welcomeController;
    private final WelcomeModel welcomeModel;
    private final ImageView logoImageView;
    private final Label clickInfoLabel;
    private final FadeTransition clickInfoFadeTransition;
    private final BorderPane mainContainer;

    public WelcomeView(WelcomeController welcomeController, WelcomeModel welcomeModel) {
        this.welcomeController = welcomeController;
        this.welcomeModel = welcomeModel;
        this.logoImageView = createLogoImageView();
        this.clickInfoLabel = createClickInfoLabel();
        this.clickInfoFadeTransition = createClickInfoFadeTransition();
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
        logoImageView.fitWidthProperty().bind(this.welcomeModel.logoWidthProperty());
        logoImageView.fitHeightProperty().bind(this.welcomeModel.logoHeightProperty());
    }

    private Label createClickInfoLabel() {
        Label label = new Label("- click to continue -");
        label.setId("entryScreenClickLabel");
        CustomStyleSheet.super.add(label, "entry-screen-text");
        return label;
    }

    private FadeTransition createClickInfoFadeTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(Objects.requireNonNull(this.clickInfoLabel, "No label provided for transition"));
        fadeTransition.setDuration(Duration.seconds(2.5));
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setAutoReverse(true);
        // smooth hack to break animation up, instead of fadeTransition.setCycleCount(Animation.INDEFINITE)
        fadeTransition.setCycleCount(2);
        fadeTransition.setOnFinished(e -> fadeTransition.play());
        fadeTransition.play();
        return fadeTransition;
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
        double screenHeight = ScreenModel.INSTANCE.getInitialHeight() / 5;
        BorderPane.setMargin(
            this.logoImageView,
            new Insets(screenHeight, 0, 0, 0)
        );
    }

    private void initializeClickableEvent(BorderPane borderPane) {
        borderPane.setOnMouseClicked(e -> {
            clickInfoFadeTransition.setOnFinished(null);
            SceneManager.DASHBOARD.switchToScene();
        });

    }

    @Override
    public Parent asParent() {
        return mainContainer;
    }
}
