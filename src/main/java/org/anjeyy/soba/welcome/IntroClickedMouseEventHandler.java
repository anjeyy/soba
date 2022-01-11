package org.anjeyy.soba.welcome;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.anjeyy.soba.scene.SceneManager;

class IntroClickedMouseEventHandler implements EventHandler<MouseEvent> {

    private final BorderPane mainContainer;
    private final FadeTransition clickInfoFadeTransition;

    private IntroClickedMouseEventHandler(BorderPane mainContainer, FadeTransition clickInfoFadeTransition) {
        this.mainContainer = mainContainer;
        this.clickInfoFadeTransition = clickInfoFadeTransition;
    }

    static IntroClickedMouseEventHandler with(BorderPane mainContainer, FadeTransition clickInfoFadeTransition) {
        return new IntroClickedMouseEventHandler(mainContainer, clickInfoFadeTransition);
    }

    @Override
    public void handle(MouseEvent event) {
        if (MouseEvent.MOUSE_CLICKED != event.getEventType()) {
            return;
        }
        int borderDiff = 5;
        double xBorder = mainContainer.getWidth() - borderDiff;
        double yBorder = mainContainer.getHeight() - borderDiff;
        double xRightBorder = xBorder - event.getX();
        double xLeftBorder = event.getX() - borderDiff;
        double yBottomBorder = yBorder - event.getY();
        double yTopBorder = event.getY() - borderDiff;
        boolean insideBorder = (xRightBorder > 0 && xLeftBorder > 0) && (yBottomBorder > 0 && yTopBorder > 0);
        if (insideBorder) {
            clickInfoFadeTransition.setOnFinished(null);
            SceneManager.DASHBOARD.switchToScene();
        }
    }
}
