package org.anjeyy.soba.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.svg.SVGGlyph;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.anjeyy.soba.common.CustomStyleSheet;
import org.anjeyy.soba.common.MainView;

public class DashboardView implements CustomStyleSheet, MainView {

    private final DashboardController dashboardController;
    private final DashboardModel dashboardModel;
    private final List<StackPane> yearButtons;
    private final JFXMasonryPane mainContainer;


    public DashboardView(DashboardController dashboardController, DashboardModel dashboardModel) {
        this.dashboardController = dashboardController;
        this.dashboardModel = dashboardModel;
        this.yearButtons = createYearButtons();
        this.mainContainer = createMasonryPane();
    }

    private List<StackPane> createYearButtons() {
        List<StackPane> result = new ArrayList<>();
        //loaded from model
        for (int i = 2021; i > 2010; i--) {
            String s = String.valueOf(i);
            JFXButton yearLabel = new JFXButton(s);
            yearLabel.setId("year" + s);
            yearLabel.prefWidthProperty().bind(dashboardModel.yearButtonWidthProperty());
            yearLabel.prefHeightProperty().bind(dashboardModel.yearButtonHeightProperty());

            //ToDo: upper & middle green with year button
            // lower is red to indicate expense/cost intention
            VBox vBox = new VBox(yearLabel);
            vBox.setAlignment(Pos.CENTER);

            //ToDo: upper & middle layout with fancy button
            JFXButton signalButton = new JFXButton("~");
            signalButton.setButtonType(ButtonType.RAISED);
            signalButton.setStyle("-fx-background-radius: 40");
            signalButton.setScaleX(0);
            signalButton.setScaleY(0);
            SVGGlyph glyph = new SVGGlyph(-1,
                                          "test",
                                          "M1008 6.286q18.857 13.714 15.429 36.571l-146.286 877.714q-2.857 16.571-18.286 25.714-8 4.571-17.714 4.571-6.286 "
                                              + "0-13.714-2.857l-258.857-105.714-138.286 168.571q-10.286 13.143-28 13.143-7.429 "
                                              + "0-12.571-2.286-10.857-4-17.429-13.429t-6.571-20.857v-199.429l493.714-605.143-610.857 "
                                              + "528.571-225.714-92.571q-21.143-8-22.857-31.429-1.143-22.857 18.286-33.714l950.857-548.571q8.571-5.143 18.286-5.143"
                                              + " 11.429 0 20.571 6.286z",
                                          Color.WHITE);
            glyph.setSize(20, 20);
            signalButton.setGraphic(glyph);
            signalButton.translateYProperty()
                        .bind(
                            Bindings.createDoubleBinding(
                                () -> vBox.getBoundsInParent().getHeight() - signalButton.getHeight() / 2,
                                signalButton.boundsInParentProperty(),
                                signalButton.heightProperty())
                        );

            StackPane yearContainer = new StackPane();
            yearContainer.getChildren().addAll(vBox, signalButton);

            result.add(yearContainer);
        }
        return result;
    }

    private JFXMasonryPane createMasonryPane() {
        JFXMasonryPane jfxMasonryPane = new JFXMasonryPane();
        jfxMasonryPane.setId("dashBoardMainContainer");
        jfxMasonryPane.setPadding(dashboardModel.masonryRegionPadding());
        jfxMasonryPane.setCenterShape(true);
        CustomStyleSheet.super.add(jfxMasonryPane, "year-button");
        jfxMasonryPane.getChildren().addAll(yearButtons);
        return jfxMasonryPane;
    }

    @Override
    public Parent asParent() {
        return mainContainer;
    }
}
