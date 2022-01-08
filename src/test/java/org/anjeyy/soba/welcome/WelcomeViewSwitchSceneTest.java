package org.anjeyy.soba.welcome;

import javafx.stage.Stage;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.scene.DashboardScene;
import org.anjeyy.soba.scene.SceneManager;
import org.anjeyy.soba.scene.SceneView;
import org.anjeyy.soba.scene.WelcomeScene;
import org.anjeyy.soba.window.WindowToolbarController;
import org.anjeyy.soba.window.WindowToolbarModel;
import org.anjeyy.soba.window.WindowToolbarView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class WelcomeViewSwitchSceneTest {

    @Start
    public void start(Stage stage) {
        StageManager.setCurrentStage(stage);
        WindowToolbarModel windowToolbarModel = new WindowToolbarModel();
        WindowToolbarController windowToolbarController = new WindowToolbarController(windowToolbarModel);
        WindowToolbarView mainContainer = new WindowToolbarView(windowToolbarController, windowToolbarModel);
        SceneView entryScreenScene = SceneManager.ENTRY_SCREEN.getSceneView();
        mainContainer.setMainParentForView(entryScreenScene.asParent());
        stage.setScene(mainContainer.setup());
        stage.show();
    }

    @Test
    void when_entry_screen_is_clicked_dashboard_screen_shows(FxRobot fxRobot) {
        // given
        SceneView beforeClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(beforeClickScene).isInstanceOf(WelcomeScene.class);

        // when
        fxRobot.clickOn("#entryScreenBackgroundImage");

        // then
        SceneView afterClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(afterClickScene).isInstanceOf(DashboardScene.class);
    }

}