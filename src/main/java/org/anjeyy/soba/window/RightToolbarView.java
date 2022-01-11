package org.anjeyy.soba.window;

import com.jfoenix.controls.JFXButton;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.Node;
import org.anjeyy.soba.common.CustomStyleSheet;
import org.anjeyy.soba.window.event.CloseActionEventHandler;
import org.anjeyy.soba.window.event.MinimizeActionEventHandler;

class RightToolbarView implements CustomStyleSheet {

    private static final String JFX_BUTTON_CSS_CLASS = "window-jfx-button";

    private final WindowToolbarController windowToolbarController;
    private final WindowToolbarModel windowToolbarModel;

    private final JFXButton minimizeButton;
    private final JFXButton maximizeButton;
    private final JFXButton closeButton;

    RightToolbarView(WindowToolbarController windowToolbarController, WindowToolbarModel windowToolbarModel) {
        this.windowToolbarController = windowToolbarController;
        this.windowToolbarModel = windowToolbarModel;
        this.minimizeButton = createMinimizeButton();
        this.maximizeButton = createMaximizeButton();
        this.closeButton = createCloseButton();
    }

    private JFXButton createMinimizeButton() {
        JFXButton minimize = new JFXButton("-");
        minimize.setId("minimizeButton");
        CustomStyleSheet.super.add(minimize, JFX_BUTTON_CSS_CLASS);
        minimize.setOnAction(MinimizeActionEventHandler.create());
        return minimize;
    }

    private JFXButton createMaximizeButton() {
        JFXButton maximize = new JFXButton("□");
        maximize.setId("maximizeButton");
        CustomStyleSheet.super.add(maximize, JFX_BUTTON_CSS_CLASS);
        maximize.setOnAction(CloseActionEventHandler.with(windowToolbarController, windowToolbarModel));
        return maximize;
    }

    private JFXButton createCloseButton() {
        JFXButton close = new JFXButton("x");
        close.setId("closeButton");
        CustomStyleSheet.super.add(close, JFX_BUTTON_CSS_CLASS);
        close.setOnAction(event -> Platform.exit());
        return close;
    }

    List<Node> getNodes() {
        ArrayList<Node> result = new ArrayList<>();
        result.add(minimizeButton);
        result.add(maximizeButton);
        result.add(closeButton);
        return result;
    }
}
