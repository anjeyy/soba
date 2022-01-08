package org.anjeyy.soba.scene;

import javafx.scene.Parent;
import org.anjeyy.soba.common.MainView;
import org.anjeyy.soba.dashboard.DashboardView;

public class DashboardScene implements SceneView {

    private final DashboardView dashboardView;

    private DashboardScene() {
        this.dashboardView = createDashboardView();
    }

    private DashboardView createDashboardView() {
        return new DashboardView();
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
