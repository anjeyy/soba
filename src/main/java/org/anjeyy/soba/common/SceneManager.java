package org.anjeyy.soba.common;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;

public enum SceneManager {

    INSTANCE;


    public static Rectangle2D sceneSize(Node node) {
        Scene scene = node.getScene();
        return new Rectangle2D(scene.getX(), scene.getY(), scene.getWidth(), scene.getHeight());
    }

}
