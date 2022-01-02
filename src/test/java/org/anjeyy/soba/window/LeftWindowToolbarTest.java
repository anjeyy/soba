package org.anjeyy.soba.window;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class LeftWindowToolbarTest {


    @Start
    public void start(Stage stage) {
        WindowToolbarModel windowToolbarModel = new WindowToolbarModel();
        WindowToolbarController windowToolbarController = new WindowToolbarController(windowToolbarModel);
        WindowToolbarView uut = new WindowToolbarView(windowToolbarController, windowToolbarModel);
        stage.setScene(uut.setup());
        stage.show();
    }

    @CsvSource(
        ignoreLeadingAndTrailingWhitespace = false,
        value = {
            "#appImage,''",
            "#appSlogan, soba - a sober look at your finance "
        })
    @ParameterizedTest
    void toolbar_contains_left_buttons(String buttonId, String buttonText, FxRobot fxRobot) {
        // given-when
        JFXButton closeButton = fxRobot.lookup(buttonId).queryAs(JFXButton.class);

        // then
        Assertions.assertThat(closeButton).extracting(Labeled::getText).isEqualTo(buttonText);
        Assertions.assertThat(closeButton).extracting(Node::isDisabled).isEqualTo(true);
    }

}