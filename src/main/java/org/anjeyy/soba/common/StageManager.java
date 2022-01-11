package org.anjeyy.soba.common;

import java.util.EventObject;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StageManager {

    private static Stage currentStage;

    private StageManager() {
        throw new UnsupportedOperationException("No instance allowed.");
    }

    public static Stage getCurrentStage() {
        if (currentStage == null) {
            throw new IllegalStateException("Stage has to be set.");
        }
        return currentStage;
    }

    public static void setCurrentStage(Stage newCurrentStage) {
        currentStage = newCurrentStage;
    }

    public static Stage extractStage(EventObject eventObject) {
        Node node = (Node) eventObject.getSource();
        return extractStage(node);
    }

    public static Stage extractStage(Node node) {
        Scene scene = node.getScene();
        Window window = scene.getWindow();
        return (Stage) window;
    }

}
