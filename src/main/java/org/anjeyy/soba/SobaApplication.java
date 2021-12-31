package org.anjeyy.soba;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.anjeyy.soba.dashboard.DashboardController;
import org.anjeyy.soba.welcome.WelcomeController;

public class SobaApplication extends Application {

    //ToDo: createOwn "window" - style
    // https://stackoverflow.com/a/9864496/11770752
    // for moving - https://stackoverflow.com/questions/11780115/moving-an-undecorated-stage-in-javafx-2

    @Override
    public void start(Stage stage) throws IOException {
        DashboardController dashboardController = new DashboardController();
        Scene scene = dashboardController.setup();

        WelcomeController welcomeController = new WelcomeController();
        Scene scene2 = welcomeController.setup(stage, scene);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("- soba -");
        stage.setScene(scene2);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}