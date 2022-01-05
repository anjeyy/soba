package org.anjeyy.soba.welcome;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXClippedPane;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class WelcomeController {

    private static final Random RANDOM = new Random();

    //todo set as bottom half


    public BorderPane setup() {
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
//        StackPane stackPane = new StackPane(button);
        JFXClippedPane stackPane = new JFXClippedPane(button);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new Pane());
//        borderPane.setCenter(button);

        stackPane.getStyleClass().add("entry-screen");
        borderPane.getStyleClass().add("entry-screen");
        return borderPane;
    }
}
