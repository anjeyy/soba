package org.anjeyy.soba.window;

import com.jfoenix.controls.JFXToolbar;
import java.net.URL;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.anjeyy.soba.common.Coordinate;
import org.anjeyy.soba.common.CustomStyleSheet;
import org.anjeyy.soba.common.ScreenManager;
import org.anjeyy.soba.common.StageManager;

public class WindowToolbarView implements CustomStyleSheet {

    private final WindowToolbarController windowToolbarController;
    private final WindowToolbarModel windowToolbarModel;
    private final LeftToolbarView leftToolbarView;
    private final RightToolbarView rightToolbarView;
    private final JFXToolbar jfxToolbar;
    private final VBox layout;

    public WindowToolbarView(WindowToolbarController windowToolbarController, WindowToolbarModel windowToolbarModel) {
        this.windowToolbarController = windowToolbarController;
        this.windowToolbarModel = windowToolbarModel;
        this.leftToolbarView = LeftToolbarView.create();
        this.rightToolbarView = new RightToolbarView(windowToolbarController, windowToolbarModel);
        this.jfxToolbar = createRootToolbar();
        this.layout = createApplicationLayout();
    }

    private JFXToolbar createRootToolbar() {
        JFXToolbar root = new JFXToolbar();
        root.setId("appToolbar");
        CustomStyleSheet.super.add(root, "window-jfx-tool-bar");
        initializeMouseDrag(root);

        Node[] leftToolbarNodes = leftToolbarView.getNodes().toArray(new Node[]{});
        root.setLeftItems(leftToolbarNodes);
        Node[] rightToolbarNodes = rightToolbarView.getNodes().toArray(new Node[]{});
        root.setRightItems(rightToolbarNodes);
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

    private VBox createApplicationLayout() {
        VBox windowLayout = new VBox();
        windowLayout.getStyleClass().add("window-jfx-box");
        windowLayout.getChildren().addAll(jfxToolbar);
        return windowLayout;
    }

    public Scene setup(Node node) {
        setMainView(node);
        Scene scene = ScreenManager.INSTANCE.createScene(layout);
        loadCssStyle(scene);
        return scene;
    }

    public <E extends Node> void setMainView(E node) {
        layout.getChildren().setAll(jfxToolbar, node);
    }

    private void loadCssStyle(Scene scene) {
        URL mainCssUrl = WindowToolbarView.class.getClassLoader().getResource("css-style/main.css");
        scene.getStylesheets()
             .add(Objects.requireNonNull(mainCssUrl, "Could not load main.css file.").toExternalForm());
    }
}
