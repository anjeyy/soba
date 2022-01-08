package org.anjeyy.soba.welcome;

import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
class WelcomeViewComponentTest {

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
    void when_entry_screen_is_shown_all_components_are_loaded(FxRobot fxRobot) {
        // given
        SceneView beforeClickScene = SceneManager.getCurrentSceneView();
        Assertions.assertThat(beforeClickScene).isInstanceOf(WelcomeScene.class);

        // when
        Label blinkingInfoText = fxRobot.lookup("#entryScreenClickLabel").queryAs(Label.class);
        ImageView backgroundImage = fxRobot.lookup("#entryScreenBackgroundImage").queryAs(ImageView.class);
        BorderPane mainContainer = fxRobot.lookup("#entryScreenMainContainer").queryAs(BorderPane.class);

        // then
        Assertions.assertThat(blinkingInfoText).extracting(Labeled::getText).isEqualTo("- click to continue -");
        Assertions.assertThat(backgroundImage).isNotNull();
        Assertions.assertThat(mainContainer)
                  .extracting(Pane::getChildren)
                  .asList()
                  .containsExactlyInAnyOrder(blinkingInfoText, backgroundImage);

    }

}