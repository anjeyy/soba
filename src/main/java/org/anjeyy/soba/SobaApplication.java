package org.anjeyy.soba;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.resize.WindowResizeListener;
import org.anjeyy.soba.scene.SceneManager;
import org.anjeyy.soba.window.WindowToolbarView;

public class SobaApplication extends Application {

    //ToDo: responsive UI -> https://edencoding.com/responsive-layouts/

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        StageManager.setCurrentStage(stage);
        WindowToolbarView mainSceneContainer = SceneManager.getMainSceneContainer();
        Scene scene = mainSceneContainer.setup();

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(true);
        stage.setScene(scene);
        createResponsiveWindow(stage);
        stage.show();
    }

    private static void createResponsiveWindow(Stage stage) {
        WindowResizeListener windowResizeListener = new WindowResizeListener(stage);
        Scene linkedScene = stage.getScene();
        linkedScene.addEventHandler(MouseEvent.MOUSE_MOVED, windowResizeListener);
        linkedScene.addEventHandler(MouseEvent.MOUSE_PRESSED, windowResizeListener);
        linkedScene.addEventHandler(MouseEvent.MOUSE_DRAGGED, windowResizeListener);
        linkedScene.addEventHandler(MouseEvent.MOUSE_EXITED, windowResizeListener);
        linkedScene.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, windowResizeListener);
    }

}
