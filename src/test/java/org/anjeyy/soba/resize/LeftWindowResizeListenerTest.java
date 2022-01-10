package org.anjeyy.soba.resize;

import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.window.WindowToolbarController;
import org.anjeyy.soba.window.WindowToolbarModel;
import org.anjeyy.soba.window.WindowToolbarView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ApplicationExtension.class)
class LeftWindowResizeListenerTest {

    @Start
    public void start(Stage stage) {
        StageManager.setCurrentStage(stage);
        WindowToolbarModel windowToolbarModel = new WindowToolbarModel();
        WindowToolbarController windowToolbarController = new WindowToolbarController(windowToolbarModel);
        WindowToolbarView mainContainer = new WindowToolbarView(windowToolbarController, windowToolbarModel);
        stage.setScene(mainContainer.setup());
        createResponsiveWindow(stage);
        stage.show();
    }

    private static void createResponsiveWindow(Stage stage) {
        LeftWindowResizeListener leftWindowResizeListener = new LeftWindowResizeListener(stage);
        Scene linkedScene = stage.getScene();
        AbstractWindowResizeListener.relevantMouseEvents()
                                    .forEach(m -> linkedScene.addEventHandler(m, leftWindowResizeListener));
    }

    @Order(1)
    @Test
    void resizing_left_window_border_to_left_is_successful(FxRobot fxRobot) {
        // given
        double xResizeMovement = 50;
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double originalX = currentStage.getX();
        double originalY = currentStage.getY();
        double originalWidth = currentStage.getWidth();
        double originalHeight = currentStage.getHeight();
        double xClick = originalX;
        double yClick = (originalY + originalHeight) / 2;

        // when
        fxRobot.moveTo(xClick, yClick)
               .press(MouseButton.PRIMARY)
               .drag(xClick - xResizeMovement, yClick)
               .drop();

        // then
        double xResized = currentStage.getX();
        double yResized = currentStage.getY();
        double widthResized = currentStage.getWidth();
        double heightResized = currentStage.getHeight();

        Assertions.assertThat(xResized).isEqualTo(xClick - xResizeMovement);
        Assertions.assertThat(yResized).isEqualTo(originalY);
        Assertions.assertThat(widthResized).isEqualTo(originalWidth + xResizeMovement);
        Assertions.assertThat(heightResized).isEqualTo(originalHeight);
    }

    @Order(2)
    @Test
    void resizing_left_window_border_to_right_is_successful(FxRobot fxRobot) {
        // given
        double xResizeMovement = 50;
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double originalX = currentStage.getX();
        double originalY = currentStage.getY();
        double originalWidth = currentStage.getWidth();
        double originalHeight = currentStage.getHeight();
        double xClick = originalX;
        double yClick = (originalY + originalHeight) / 2 + 2;

        // when
        fxRobot.moveTo(xClick, yClick)
               .press(MouseButton.PRIMARY)
               .drag(xClick + xResizeMovement, yClick)
               .drop();

        // then
        double xResized = currentStage.getX();
        double yResized = currentStage.getY();
        double widthResized = currentStage.getWidth();
        double heightResized = currentStage.getHeight();

        Assertions.assertThat(xResized).isEqualTo(xClick + xResizeMovement);
        Assertions.assertThat(yResized).isEqualTo(originalY);
        Assertions.assertThat(widthResized).isEqualTo(originalWidth - xResizeMovement);
        Assertions.assertThat(heightResized).isEqualTo(originalHeight);
    }

}