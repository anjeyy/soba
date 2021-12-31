package org.anjeyy.soba.dashboard;

import com.jfoenix.controls.JFXMasonryPane;
import java.util.Random;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.anjeyy.soba.ScreenManager;

public class DashboardController {

    private static final Random RANDOM = new Random();

    public Scene setup() {
        JFXMasonryPane root = new JFXMasonryPane();
        for (int i = 0; i < 100; i++) {
            Label label = new Label(i + "");
            label.setPrefSize(RANDOM.nextInt(100), RANDOM.nextInt(100));
            label.setStyle(
                "-fx-background-color:rgb(" +
                    RANDOM.nextInt(255) + ","
                    + RANDOM.nextInt(255) + ","
                    + RANDOM.nextInt(255) +
                    ")");
            root.getChildren().add(label);
        }
        return ScreenManager.INSTANCE.createScene(root);
    }
}
