package org.anjeyy.soba.scene;

import javafx.scene.Parent;
import org.anjeyy.soba.window.WindowToolbarController;
import org.anjeyy.soba.window.WindowToolbarModel;
import org.anjeyy.soba.window.WindowToolbarView;

public enum SceneManager {

    ENTRY_SCREEN(WelcomeScene.create()),
    DASHBOARD(DashboardScene.create());

    private static final WindowToolbarView mainContainer = createMainWindowContainer();

    private final SceneView sceneView;

    SceneManager(SceneView sceneView) {
        this.sceneView = sceneView;
    }

    private static WindowToolbarView createMainWindowContainer() {
        WindowToolbarModel windowToolbarModel = new WindowToolbarModel();
        WindowToolbarController windowToolbarController = new WindowToolbarController(windowToolbarModel);
        WindowToolbarView windowToolbarView = new WindowToolbarView(windowToolbarController, windowToolbarModel);
        windowToolbarView.setMainParentForView(ENTRY_SCREEN.sceneView.asParent());
        return windowToolbarView;
    }

    public SceneView getSceneView() {
        return sceneView;
    }

    public static WindowToolbarView getMainSceneContainer() {
        return mainContainer;
    }

    public static SceneView getCurrentSceneView() {
        return CurrentScene.INSTANCE.sceneView;
    }

    public void switchToScene() {
        CurrentScene.INSTANCE.updateSceneView(this.sceneView);
        Parent parentScene = this.sceneView.asParent();
        mainContainer.setMainParentForView(parentScene);
    }

    private enum CurrentScene {
        INSTANCE;

        private SceneView sceneView = ENTRY_SCREEN.sceneView;

        private void updateSceneView(SceneView sceneView) {
            this.sceneView = sceneView;
        }
    }

}
