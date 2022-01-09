package org.anjeyy.soba.window;

import com.jfoenix.controls.JFXToolbar;
import java.net.URL;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.anjeyy.soba.common.Coordinate;
import org.anjeyy.soba.common.CustomStyleSheet;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.screen.ScreenModel;

public class WindowToolbarView implements CustomStyleSheet {

    private final WindowToolbarController windowToolbarController;
    private final WindowToolbarModel windowToolbarModel;
    private final LeftToolbarView leftToolbarView;
    private final RightToolbarView rightToolbarView;
    private final JFXToolbar jfxToolbar;
    private final BorderPane mainContainer;

    public WindowToolbarView(WindowToolbarController windowToolbarController, WindowToolbarModel windowToolbarModel) {
        this.windowToolbarController = windowToolbarController;
        this.windowToolbarModel = windowToolbarModel;
        this.leftToolbarView = LeftToolbarView.create();
        this.rightToolbarView = new RightToolbarView(windowToolbarController, windowToolbarModel);
        this.jfxToolbar = createRootToolbar();
        this.mainContainer = createApplicationLayout();
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
            if (!e.isPrimaryButtonDown()) {
                return;
            }
            Stage stage = StageManager.extractStage(node);
            double xDiff = stage.getX() - e.getScreenX();
            double yDiff = stage.getY() - e.getScreenY();
            windowToolbarController.moveWindow(xDiff, yDiff);
        });
        node.setOnMouseDragged(e -> {
            if (!e.isPrimaryButtonDown()) {
                return;
            }
            Stage stage = StageManager.extractStage(node);
            Coordinate mouseDrag = windowToolbarController.restoreScreenBound();
            stage.setX(e.getScreenX() + mouseDrag.x());
            stage.setY(e.getScreenY() + mouseDrag.y());
        });
    }

    private BorderPane createApplicationLayout() {
        BorderPane windowLayout = new BorderPane();
        windowLayout.setId("windowMainContainer");
        CustomStyleSheet.super.add(windowLayout, "window-jfx-box");
        windowLayout.setTop(jfxToolbar);
        return windowLayout;
    }

    public Scene setup() {
        Scene scene = new Scene(
            mainContainer,
            ScreenModel.INSTANCE.getInitialWidth(),
            ScreenModel.INSTANCE.getInitialHeight()
        );
        loadCssStyle(scene);
        return scene;
    }

    public <E extends Node> void setMainParentForView(E node) {
        mainContainer.setCenter(node);
    }

    private void loadCssStyle(Scene scene) {
        URL mainCssUrl = WindowToolbarView.class.getClassLoader().getResource("css-style/main.css");
        scene.getStylesheets()
             .add(Objects.requireNonNull(mainCssUrl, "Could not load main.css file.").toExternalForm());
    }
}
