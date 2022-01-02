package org.anjeyy.soba.window;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.anjeyy.soba.common.Coordinate;
import org.anjeyy.soba.common.ScreenManager;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.common.ViewableScene;

public class WindowToolbarView implements ViewableScene {

    private static final String JFX_BUTTON_CSS_CLASS = "window-jfx-button";

    private final WindowToolbarController windowToolbarController;
    private final WindowToolbarModel windowToolbarModel;
    private final JFXButton appImageButton;
    private final JFXButton appSloganButton;
    private final JFXButton minimizeButton;
    private final JFXButton maximizeButton;
    private final JFXButton closeButton;
    private final JFXToolbar jfxToolbar;

    public WindowToolbarView(WindowToolbarController windowToolbarController, WindowToolbarModel windowToolbarModel) {
        this.windowToolbarController = windowToolbarController;
        this.windowToolbarModel = windowToolbarModel;
        this.appImageButton = createApplicationImageButton();
        this.appSloganButton = createApplicationTitleBar();
        this.minimizeButton = createMinimizeButton();
        this.maximizeButton = createMaximizeButton();
        this.closeButton = createCloseButton();
        this.jfxToolbar = createRootToolbar();
    }

    private JFXButton createApplicationImageButton() {
        JFXButton jfxButton = new JFXButton();
        jfxButton.setId("appImage");
        jfxButton.setDisable(true);
        jfxButton.setMinSize(30, 35);
        jfxButton.setMaxSize(30, 35);
        jfxButton.setPadding(new Insets(0, 0, 0, 10));
        createIconImage(jfxButton);
        addCustomStylesheet(jfxButton, "window-jfx-title-bar-icon");
        return jfxButton;
    }

    private void createIconImage(JFXButton jfxButton) {
        InputStream inputStream = Objects.requireNonNull(
            getClass().getResourceAsStream("/image/icon-high-res.png"), "Could not load image.");
        Image rawAppImage = new Image(inputStream);
        ImageView appImageView = new ImageView(rawAppImage);
        appImageView.fitWidthProperty().bind(jfxButton.widthProperty());
        appImageView.fitHeightProperty().bind(jfxButton.heightProperty());
        appImageView.setPreserveRatio(true);
        jfxButton.setGraphic(appImageView);
    }

    private JFXButton createApplicationTitleBar() {
        JFXButton slogan = new JFXButton(" soba - a sober look at your finance ");
        slogan.setId("appSlogan");
        slogan.setDisable(true);
        addCustomStylesheet(slogan, "window-jfx-title-bar");
        return slogan;
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
        root.setId("appToolbar");
        root.setLeftItems(appImageButton, appSloganButton);
        root.setRightItems(minimizeButton, maximizeButton, closeButton);
        addCustomStylesheet(root, "window-jfx-tool-bar");
        initializeMouseDrag(root);
        root.setTop(new AnchorPane());
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
