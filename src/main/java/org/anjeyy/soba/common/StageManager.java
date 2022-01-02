package org.anjeyy.soba.common;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public enum StageManager {

    WELCOME;


    public static Stage extractStage(Node node) {
        Scene scene = node.getScene();
        Window window = scene.getWindow();
        return (Stage) window;
    }

}
