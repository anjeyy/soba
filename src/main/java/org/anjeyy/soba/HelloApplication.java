package org.anjeyy.soba;

import com.jfoenix.controls.JFXMasonryPane;
import java.io.IOException;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Random r = new Random();
        JFXMasonryPane root = new JFXMasonryPane();
        for (int i = 0; i < 20; i++) {
            Label label = new Label(i + "");
            label.setPrefSize(r.nextInt(100), r.nextInt(100));
            label.setStyle(
                "-fx-background-color:rgb(" + r.nextInt(255) + "," + r.nextInt(255) + "," + r.nextInt(255) + ")");
            root.getChildren().add(label);
        }
        Scene scene = new Scene(root, 320, 240);

//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}