package org.anjeyy.soba.scene;

import javafx.scene.Parent;
import org.anjeyy.soba.common.MainView;
import org.anjeyy.soba.dashboard.DashboardController;
import org.anjeyy.soba.dashboard.DashboardModel;
import org.anjeyy.soba.dashboard.DashboardView;
import org.anjeyy.soba.screen.ScreenModel;

public class DashboardScene implements SceneView {

    private final DashboardView dashboardView;

    private DashboardScene() {
        this.dashboardView = createDashboardView();
    }

    private DashboardView createDashboardView() {
        ScreenModel screenModel = ScreenModel.INSTANCE;
        DashboardController dashboardController = new DashboardController();
        DashboardModel dashboardModel = new DashboardModel(screenModel);
        return new DashboardView(dashboardController, dashboardModel);
    }

    static DashboardScene create() {
        return new DashboardScene();
    }

    @Override
    public MainView createView() {
        return dashboardView;
    }

    @Override
    public Parent asParent() {
        return dashboardView.asParent();
    }
}
