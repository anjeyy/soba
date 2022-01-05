package org.anjeyy.soba;

import com.jfoenix.controls.JFXMasonryPane;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.anjeyy.soba.dashboard.DashboardController;
import org.anjeyy.soba.welcome.WelcomeController;
import org.anjeyy.soba.welcome.WelcomeView;
import org.anjeyy.soba.window.WindowToolbarController;
import org.anjeyy.soba.window.WindowToolbarModel;
import org.anjeyy.soba.window.WindowToolbarView;

public class SobaApplication extends Application {

    //ToDo: responsive UI -> https://edencoding.com/responsive-layouts/

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        DashboardController dashboardController = new DashboardController();
        JFXMasonryPane pane = dashboardController.setup();

        WelcomeController welcomeController = new WelcomeController();
        BorderPane stackPane = welcomeController.setup();

        WindowToolbarView windowToolbarView = createWindowButtonsView();
        Scene scene = windowToolbarView.setup(stackPane);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    private WelcomeView createWelcomeView() {
        //todo
        return null;
    }

    private WindowToolbarView createWindowButtonsView() {
        WindowToolbarModel windowToolbarModel = new WindowToolbarModel();
        WindowToolbarController windowToolbarController = new WindowToolbarController(windowToolbarModel);
        return new WindowToolbarView(windowToolbarController, windowToolbarModel);
    }


}
