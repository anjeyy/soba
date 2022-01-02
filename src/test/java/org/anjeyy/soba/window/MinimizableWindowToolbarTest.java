package org.anjeyy.soba.window;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@Disabled("Minimizing window breaks following tests.")
@ExtendWith(ApplicationExtension.class)
class MinimizableWindowToolbarTest {


    @Start
    public void start(Stage stage) {
        WindowToolbarModel windowToolbarModel = new WindowToolbarModel();
        WindowToolbarController windowToolbarController = new WindowToolbarController(windowToolbarModel);
        WindowToolbarView uut = new WindowToolbarView(windowToolbarController, windowToolbarModel);
        stage.setScene(uut.setup());
        stage.show();
    }


    @Test
    void when_minimize_button_is_clicked_minimize_application(FxRobot fxRobot) {
        // given-when
        fxRobot.clickOn("#minimizeButton");
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);

        // then
        ReadOnlyBooleanProperty minimized = currentStage.iconifiedProperty();
        Assertions.assertThat(minimized.get()).isTrue();
    }
}