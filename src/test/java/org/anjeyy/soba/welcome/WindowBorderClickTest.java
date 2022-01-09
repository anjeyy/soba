package org.anjeyy.soba.welcome;

import javafx.geometry.Bounds;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.anjeyy.soba.common.StageManager;
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
class WindowBorderClickTest {

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
    void when_clicking_left_border_does_nothing(FxRobot fxRobot) {
        // given
        Bounds windowBounds = getWindowBounds(fxRobot);
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double x = currentStage.getX();
        double y = currentStage.getY();
        double halfHeight = windowBounds.getHeight() / 2;

        // when
        fxRobot.clickOn(x, y + halfHeight);

        // then
        SceneView afterClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(afterClickScene).isInstanceOf(WelcomeScene.class);
    }

    @Test
    void when_clicking_left_bottom_border_does_nothing(FxRobot fxRobot) {
        // given
        Bounds windowBounds = getWindowBounds(fxRobot);
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double x = currentStage.getX();
        double y = currentStage.getY();
        double height = windowBounds.getMaxY() - 10;

        // when
        fxRobot.clickOn(x, y + height);

        // then
        SceneView afterClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(afterClickScene).isInstanceOf(WelcomeScene.class);
    }

    @Test
    void when_clicking_bottom_border_does_nothing(FxRobot fxRobot) {
        // given
        Bounds windowBounds = getWindowBounds(fxRobot);
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double x = currentStage.getX();
        double y = currentStage.getY();
        double halfWidth = windowBounds.getWidth() / 2;
        double height = windowBounds.getMaxY() - 10;

        // when
        fxRobot.clickOn(x + halfWidth, y + height);

        // then
        SceneView afterClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(afterClickScene).isInstanceOf(WelcomeScene.class);
    }

    @Test
    void when_clicking_right_bottom_border_does_nothing(FxRobot fxRobot) {
        // given
        Bounds windowBounds = getWindowBounds(fxRobot);
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double x = currentStage.getX();
        double y = currentStage.getY();
        double width = windowBounds.getMaxX() - 20;
        double height = windowBounds.getMaxY() - 10;

        // when
        fxRobot.clickOn(x + width, y + height);

        // then
        SceneView afterClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(afterClickScene).isInstanceOf(WelcomeScene.class);
    }

    @Test
    void when_clicking_right_border_does_nothing(FxRobot fxRobot) {
        // given
        Bounds windowBounds = getWindowBounds(fxRobot);
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double x = currentStage.getX();
        double y = currentStage.getY();
        double width = windowBounds.getMaxX() - 20;
        double halfHeight = windowBounds.getHeight() / 2;

        // when
        fxRobot.clickOn(x + width, y + halfHeight);

        // then
        SceneView afterClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(afterClickScene).isInstanceOf(WelcomeScene.class);
    }

    private static Bounds getWindowBounds(FxRobot fxRobot) {
        SceneView beforeClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(beforeClickScene).isInstanceOf(WelcomeScene.class);

        BorderPane windowMainContainer = fxRobot.lookup("#windowMainContainer").queryAs(BorderPane.class);
        return windowMainContainer.getBoundsInLocal();
    }
}
