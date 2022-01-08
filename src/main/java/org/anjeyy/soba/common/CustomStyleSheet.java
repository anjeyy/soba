package org.anjeyy.soba.common;

import javafx.scene.Node;

public interface CustomStyleSheet {

    default void add(Node node, String cssClassName) {
        node.getStyleClass().add(cssClassName);
    }
}
