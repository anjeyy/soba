package org.anjeyy.soba.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.anjeyy.soba.common.CustomStyleSheet;
import org.anjeyy.soba.common.MainView;

public class DashboardView implements CustomStyleSheet, MainView {

    private final DashboardController dashboardController;
    private final DashboardModel dashboardModel;
    private final JFXMasonryPane mainContainer;


    public DashboardView(DashboardController dashboardController, DashboardModel dashboardModel) {
        this.dashboardController = dashboardController;
        this.dashboardModel = dashboardModel;
        this.mainContainer = createMasonryPane();
    }

    private JFXMasonryPane createMasonryPane() {
        JFXMasonryPane jfxMasonryPane = new JFXMasonryPane();
        jfxMasonryPane.setId("dashBoardMainContainer");
        CustomStyleSheet.super.add(jfxMasonryPane, "dashboard.css");
        jfxMasonryPane.setPadding(new Insets(50));  //ToDo: responsive
        jfxMasonryPane.setCenterShape(true);
        for (int i = 2018; i < 2022; i++) {
            JFXButton yearLabel = new JFXButton(i + "");    //ToDo: saved (&loaded) or newly created years
            yearLabel.setId("year" + i);
            yearLabel.setButtonType(ButtonType.RAISED); //ToDo: css property!
            yearLabel.setPrefSize(250, 250);   //ToDo: responsive
            yearLabel.setStyle("-fx-background-color:rgb(54,154,103)");    //todo css property

            //ToDo: upper & middle green with year button
            // lower is red to indicate expense/cost intention
            VBox vBox = new VBox(yearLabel);
            vBox.setAlignment(Pos.CENTER);
            StackPane stackPane = new StackPane(yearLabel);
            jfxMasonryPane.getChildren().add(stackPane);
        }
        return jfxMasonryPane;
    }

    @Override
    public Parent asParent() {
        return mainContainer;
    }
}
