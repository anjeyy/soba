package org.anjeyy.soba.window;

import com.jfoenix.controls.JFXToolbar;
import java.net.URL;
import java.util.Objects;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.anjeyy.soba.common.CustomStyleSheet;
import org.anjeyy.soba.screen.ScreenModel;
import org.anjeyy.soba.window.event.MouseDraggedActionEventHandler;
import org.anjeyy.soba.window.event.MousePressedActionEventHandler;

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
        node.setOnMousePressed(MousePressedActionEventHandler.with(windowToolbarController));
        node.setOnMouseDragged(MouseDraggedActionEventHandler.with(windowToolbarController));
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
