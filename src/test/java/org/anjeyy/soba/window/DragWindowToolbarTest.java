package org.anjeyy.soba.window;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class DragWindowToolbarTest {


    @Start
    public void start(Stage stage) {
        WindowToolbarModel windowToolbarModel = new WindowToolbarModel();
        WindowToolbarController windowToolbarController = new WindowToolbarController(windowToolbarModel);
        WindowToolbarView uut = new WindowToolbarView(windowToolbarController, windowToolbarModel);
        stage.setScene(uut.setup(new Pane()));
        stage.show();
    }


    @Test
    void when_minimize_button_is_clicked_minimize_application(FxRobot fxRobot) {
        // given
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double beforeWidth = currentStage.getWidth();
        double beforeHeight = currentStage.getHeight();
        double beforeX = currentStage.getX();
        double beforeY = currentStage.getY();

        // when
        fxRobot.clickOn("#appToolbar").drag(MouseButton.PRIMARY).moveBy(50, 75);

        // then
        double afterX = currentStage.getX();
        double afterY = currentStage.getY();
        double afterWidth = currentStage.getWidth();
        double afterHeight = currentStage.getHeight();
        Assertions.assertThat(afterX).isEqualTo(beforeX + 50);
        Assertions.assertThat(afterY).isEqualTo(beforeY + 75);
        Assertions.assertThat(afterWidth).isEqualTo(beforeWidth);
        Assertions.assertThat(afterHeight).isEqualTo(beforeHeight);
    }
}
