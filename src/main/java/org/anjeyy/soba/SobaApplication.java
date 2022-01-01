package org.anjeyy.soba;

import com.jfoenix.controls.JFXMasonryPane;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.anjeyy.soba.dashboard.DashboardController;
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

        WindowToolbarView windowToolbarView = createWindowButtonsView();
        windowToolbarView.setBottomView(pane);
        Scene scene3 = windowToolbarView.setup();

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(true);
        stage.setTitle("- soba -");
        stage.setScene(scene3);
        stage.show();
    }

    private WindowToolbarView createWindowButtonsView() {
        WindowToolbarModel windowToolbarModel = new WindowToolbarModel();
        WindowToolbarController windowToolbarController = new WindowToolbarController(windowToolbarModel);
        return new WindowToolbarView(windowToolbarController, windowToolbarModel);
    }
}