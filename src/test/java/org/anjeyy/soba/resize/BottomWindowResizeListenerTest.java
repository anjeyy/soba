package org.anjeyy.soba.resize;

import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.anjeyy.soba.common.StageManager;
import org.anjeyy.soba.window.WindowToolbarController;
import org.anjeyy.soba.window.WindowToolbarModel;
import org.anjeyy.soba.window.WindowToolbarView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
class BottomWindowResizeListenerTest {

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
        BottomWindowResizeListener bottomWindowResizeListener = new BottomWindowResizeListener(stage);
        Scene linkedScene = stage.getScene();
        AbstractWindowResizeListener.relevantMouseEvents()
                                    .forEach(m -> linkedScene.addEventHandler(m, bottomWindowResizeListener));
    }

    @Order(1)
    @Test
    void resizing_bottom_window_border_to_bottom_is_successful(FxRobot fxRobot) {
        // given
        double resizeMovement = 50;
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double originalX = currentStage.getX();
        double originalY = currentStage.getY();
        double originalWidth = currentStage.getWidth();
        double originalHeight = currentStage.getHeight();
        double xClick = (originalX + originalWidth) / 2;
        double yClick = originalY + originalHeight - 1;

        // when
        fxRobot.moveTo(0, 0)
               .moveTo(xClick, yClick)
               .press(MouseButton.PRIMARY)
               .drag(xClick, yClick + resizeMovement)
               .drop();

        // then
        double xResized = currentStage.getX();
        double yResized = currentStage.getY();
        double widthResized = currentStage.getWidth();
        double heightResized = currentStage.getHeight();

        Assertions.assertThat(xResized).isEqualTo(originalX);
        Assertions.assertThat(yResized).isEqualTo(originalY);
        Assertions.assertThat(widthResized).isEqualTo(originalWidth);
        Assertions.assertThat(heightResized).isEqualTo(originalHeight + resizeMovement - 1);
    }

    @Disabled("Disabled due to JavaFX rendering issue while multiple tests are working.")
    @Order(2)
    @Test
    void resizing_bottom_window_border_to_top_is_successful(FxRobot fxRobot) {
        // given
        double resizeMovement = 50;
        Stage currentStage = (Stage) fxRobot.listWindows().get(0);
        double originalX = currentStage.getX();
        double originalY = currentStage.getY();
        double originalWidth = currentStage.getWidth();
        double originalHeight = currentStage.getHeight();
        double xClick = (originalX + originalWidth) / 2;
        double yClick = originalY + originalHeight - 1;

        // when
        fxRobot.moveTo(0, 0)
               .moveTo(xClick, yClick)
               .press(MouseButton.PRIMARY)
               .drag(xClick, yClick - resizeMovement)
               .drop();

        // then
        double xResized = currentStage.getX();
        double yResized = currentStage.getY();
        double widthResized = currentStage.getWidth();
        double heightResized = currentStage.getHeight();

        Assertions.assertThat(xResized).isEqualTo(originalX);
        Assertions.assertThat(yResized).isEqualTo(originalY);
        Assertions.assertThat(widthResized).isEqualTo(originalWidth);
        Assertions.assertThat(heightResized).isEqualTo(originalHeight - resizeMovement - 1);
    }

}