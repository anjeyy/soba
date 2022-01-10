package org.anjeyy.soba;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.resize.AbstractWindowResizeListener;
import org.anjeyy.soba.resize.BottomLeftWindowResizeListener;
import org.anjeyy.soba.resize.BottomRightWindowResizeListener;
import org.anjeyy.soba.resize.BottomWindowResizeListener;
import org.anjeyy.soba.resize.LeftWindowResizeListener;
import org.anjeyy.soba.resize.RightWindowResizeListener;
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
        BottomWindowResizeListener bottomWindowResizeListener = new BottomWindowResizeListener(stage);
        RightWindowResizeListener rightWindowResizeListener = new RightWindowResizeListener(stage);
        LeftWindowResizeListener leftWindowResizeListener = new LeftWindowResizeListener(stage);
        BottomLeftWindowResizeListener bottomLeftWindowResizeListener = new BottomLeftWindowResizeListener(stage);
        BottomRightWindowResizeListener bottomRightWindowResizeListener = new BottomRightWindowResizeListener(stage);
        Scene linkedScene = stage.getScene();

        AbstractWindowResizeListener.relevantMouseEvents()
                                    .forEach(m -> {
                                        linkedScene.addEventHandler(m, bottomWindowResizeListener);
                                        linkedScene.addEventHandler(m, rightWindowResizeListener);
                                        linkedScene.addEventHandler(m, leftWindowResizeListener);
                                        linkedScene.addEventHandler(m, bottomLeftWindowResizeListener);
                                        linkedScene.addEventHandler(m, bottomRightWindowResizeListener);
                                    });
    }

}
