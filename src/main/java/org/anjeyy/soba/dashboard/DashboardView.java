package org.anjeyy.soba.dashboard;

import com.jfoenix.controls.JFXMasonryPane;
import java.util.Random;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.anjeyy.soba.common.CustomStyleSheet;
import org.anjeyy.soba.common.MainView;

public class DashboardView implements CustomStyleSheet, MainView {

    private static final Random RANDOM = new Random();

    private final JFXMasonryPane mainContainer;

    public DashboardView() {
        this.mainContainer = createMainContainer();
    }

    private JFXMasonryPane createMainContainer() {
        JFXMasonryPane root = new JFXMasonryPane();
        root.setId("dashBoardMainContainer");
        for (int i = 0; i < 100; i++) {
            Label label = new Label(i + "");
            label.setPrefSize(RANDOM.nextInt(100), RANDOM.nextInt(100));
            label.setStyle(
                "-fx-background-color:rgb(" +
                    RANDOM.nextInt(255) + ","
                    + RANDOM.nextInt(255) + ","
                    + RANDOM.nextInt(255) +
                    ")");
            root.getChildren().add(label);
        }
        return root;
    }

    @Override
    public Parent asParent() {
        return mainContainer;
    }
}
