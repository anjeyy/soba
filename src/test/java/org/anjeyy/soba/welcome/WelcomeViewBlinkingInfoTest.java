package org.anjeyy.soba.welcome;

import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
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
class WelcomeViewBlinkingInfoTest {

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
    void when_entry_screen_is_clicked_text_info_stops_blinking(FxRobot fxRobot) {
        // given
        SceneView beforeClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(beforeClickScene).isInstanceOf(WelcomeScene.class);

        // when
        fxRobot.clickOn("#entryScreenBackgroundImage");

        // then
        Label blinkingInfoText = fxRobot.lookup("#entryScreenClickLabel").queryAs(Label.class);
        Assertions.assertThat(blinkingInfoText).extracting(Labeled::getText).isEqualTo("- click to continue -");
        fxRobot.interact(SceneManager.ENTRY_SCREEN::switchToScene); //restore state
    }

}