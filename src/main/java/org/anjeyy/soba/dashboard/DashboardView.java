package org.anjeyy.soba.dashboard;

import javafx.scene.Parent;
import org.anjeyy.soba.common.MainView;

public class DashboardView implements MainView {

    private final DashboardController dashboardController;
    private final DashboardModel dashboardModel;

    public DashboardView(DashboardController dashboardController, DashboardModel dashboardModel) {
        this.dashboardController = dashboardController;
        this.dashboardModel = dashboardModel;
    }

    @Override
    public Parent asParent() {
        return null;
    }
}
