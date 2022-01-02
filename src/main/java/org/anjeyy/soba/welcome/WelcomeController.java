package org.anjeyy.soba.welcome;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.anjeyy.soba.common.ScreenManager;

public class WelcomeController {

    private static final Random RANDOM = new Random();


    public Scene setup(Stage currentStage, Scene followingScene) {
        InputStream inputStream = Objects.requireNonNull(
            getClass().getResourceAsStream("/image/welcome-background.jpg"), "Could not load image.");
        Image backgroundImage = new Image(inputStream);

        JFXButton button = new JFXButton("JFX foenix button");
        button.setStyle(
            "-fx-background-color:rgb(" +
                RANDOM.nextInt(255) + ","
                + RANDOM.nextInt(255) + ","
                + RANDOM.nextInt(255) +
                ")");
        button.setButtonType(ButtonType.FLAT);
        button.setAlignment(Pos.CENTER);
        button.getStyleClass().add("button");
        button.setOnAction(e -> currentStage.setScene(followingScene));

        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOnMouseClicked(e -> currentStage.setScene(followingScene));
        StackPane stackPane = new StackPane(button, new ImageView(backgroundImage));
        return ScreenManager.INSTANCE.createScene(stackPane);
        //        JFXTextField textField = new JFXTextField("-- click to continue --");
//        textField.setOnAction(e -> currentStage.setScene(followingScene));
//        StackPane stackPane = new StackPane(textField, new ImageView(backgroundImage));
//        return ScreenManager.INSTANCE.createScene(textField);
    }
}
