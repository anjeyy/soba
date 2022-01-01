package org.anjeyy.soba.window;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import java.net.URL;
import java.util.Objects;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.anjeyy.soba.common.Coordinate;
import org.anjeyy.soba.common.ScreenManager;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.common.ViewableScene;

public class WindowToolbarView implements ViewableScene {

    private static final String JFX_BUTTON_CSS_CLASS = "window-jfx-button";

    private final WindowToolbarController windowToolbarController;
    private final WindowToolbarModel windowToolbarModel;
    private final JFXButton minimizeButton;
    private final JFXButton maximizeButton;
    private final JFXButton closeButton;
    private final JFXToolbar jfxToolbar;

    public WindowToolbarView(WindowToolbarController windowToolbarController, WindowToolbarModel windowToolbarModel) {
        this.windowToolbarController = windowToolbarController;
        this.windowToolbarModel = windowToolbarModel;
        this.minimizeButton = createMinimizeButton();
        this.maximizeButton = createMaximizeButton();
        this.closeButton = createCloseButton();
        this.jfxToolbar = createRootToolbar();
    }

    private static JFXButton createMinimizeButton() {
        JFXButton minimize = new JFXButton("-");
        minimize.setId("minimizeButton");
        addCustomStylesheet(minimize, JFX_BUTTON_CSS_CLASS);
        minimize.setOnAction(event -> {
            Stage stage = StageManager.extractStage(minimize);
            stage.setIconified(true);
        });
        return minimize;
    }

    private JFXButton createMaximizeButton() {
        JFXButton maximize = new JFXButton("□");
        maximize.setId("maximizeButton");
        addCustomStylesheet(maximize, JFX_BUTTON_CSS_CLASS);
        maximize.setOnAction(event -> {
            boolean alteredMaximize = alterMaximizeButton();
            Stage stage = StageManager.extractStage(maximize);
            stage.setMaximized(alteredMaximize);
        });
        return maximize;
    }

    private boolean alterMaximizeButton() {
        boolean isMaximized = windowToolbarModel.isMaximized();
        if (isMaximized) {
            windowToolbarController.restoreMaximize();
        } else {
            windowToolbarController.maximize();
        }
        return !isMaximized;
    }

    private static JFXButton createCloseButton() {
        JFXButton close = new JFXButton("x");
        close.setId("closeButton");
        addCustomStylesheet(close, JFX_BUTTON_CSS_CLASS);
        close.setOnAction(event -> Platform.exit());
        return close;
    }

    private JFXToolbar createRootToolbar() {
        JFXToolbar root = new JFXToolbar();
        addCustomStylesheet(root, "window-jfx-tool-bar");
        root.setRightItems(minimizeButton, maximizeButton, closeButton);
        initializeMouseDrag(root);
        return root;
    }

    private <E extends Node> void initializeMouseDrag(E node) {
        node.setOnMousePressed(e -> {
            Stage stage = StageManager.extractStage(node);
            double xDiff = stage.getX() - e.getScreenX();
            double yDiff = stage.getY() - e.getScreenY();
            windowToolbarController.moveWindow(xDiff, yDiff);
        });
        node.setOnMouseDragged(e -> {
            Stage stage = StageManager.extractStage(node);
            Coordinate mouseDrag = windowToolbarController.restoreScreenBound();
            stage.setX(e.getScreenX() + mouseDrag.x());
            stage.setY(e.getScreenY() + mouseDrag.y());
        });
    }

    private static void addCustomStylesheet(Node node, String cssClassName) {
        node.getStyleClass().add(cssClassName);
    }

    public <E extends Node> void setBottomView(E node) {
        jfxToolbar.setBottom(node);
    }

    @Override
    public Scene setup() {
        Scene scene = ScreenManager.INSTANCE.createScene(jfxToolbar);
        loadCssStyle(scene);
        return scene;
    }

    private void loadCssStyle(Scene scene) {
        URL mainCssUrl = WindowToolbarView.class.getClassLoader().getResource("css-style/main.css");
        scene.getStylesheets()
             .add(Objects.requireNonNull(mainCssUrl, "Could not load main.css file.").toExternalForm());
    }
}
