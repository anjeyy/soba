package org.anjeyy.soba.welcome;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXClippedPane;
import java.io.InputStream;
import java.util.Objects;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WelcomeController {

    private static final Random RANDOM = new Random();

    //todo set as bottom half


    public StackPane setup(Stage currentStage, Node followingNode) {
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
//        button.setOnAction(e -> currentStage.setScene(followingNode));

        ImageView backgroundImageView = new ImageView(backgroundImage);
//        backgroundImageView.setOnMouseClicked(e -> currentStage.setScene(followingScene));
//        StackPane stackPane = new StackPane(backgroundImageView, button);
        JFXClippedPane stackPane = new JFXClippedPane();
        stackPane.setMaxSize(1000, 1000);
        stackPane.getStyleClass().add("entry-screen");
        return stackPane;
    }
}
