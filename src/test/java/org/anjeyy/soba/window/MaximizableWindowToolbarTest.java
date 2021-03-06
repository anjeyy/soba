package org.anjeyy.soba.window;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;
import org.anjeyy.soba.common.StageManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class MaximizableWindowToolbarTest {


    @Start
    public void start(Stage stage) {
        StageManager.setCurrentStage(stage);
        WindowToolbarModel windowToolbarModel = new WindowToolbarModel();
        WindowToolbarController windowToolbarController = new WindowToolbarController(windowToolbarModel);
        WindowToolbarView uut = new WindowToolbarView(windowToolbarController, windowToolbarModel);
        stage.setScene(uut.setup());
        stage.show();
    }

    @CsvSource({
        "#minimizeButton, -",
        "#maximizeButton, □",
        "#closeButton, x"
    })
    @ParameterizedTest
    void toolbar_contains_minimize_button(String buttonId, String buttonText, FxRobot fxRobot) {
        // given-when
        JFXButton closeButton = fxRobot.lookup(buttonId).queryAs(JFXButton.class);

        // then
        Assertions.assertThat(closeButton).extracting(Labeled::getText).isEqualTo(buttonText);
    }

    @Test
    void when_maximize_button_is_clicked_maximize_application(FxRobot fxRobot) {
        // given-when
        fxRobot.clickOn("#maximizeButton");
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);

        // then
        ReadOnlyBooleanProperty maximized = currentStage.maximizedProperty();
        Assertions.assertThat(maximized.get()).isTrue();
    }

    @Test
    void when_maximize_button_is_clicked_twice_restore_previous_size(FxRobot fxRobot) {
        // given-when
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        fxRobot.clickOn("#maximizeButton");
        ReadOnlyBooleanProperty maximized = currentStage.maximizedProperty();
        Assertions.assertThat(maximized.get()).isTrue();
        fxRobot.clickOn("#maximizeButton");

        // then
        ReadOnlyBooleanProperty unMaximized = currentStage.maximizedProperty();
        Assertions.assertThat(unMaximized.get()).isFalse();
    }

}
