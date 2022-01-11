package org.anjeyy.soba.scene;

import javafx.scene.Parent;
import org.anjeyy.soba.common.DummyView;
import org.anjeyy.soba.common.MainView;

public class DummyScene implements SceneView {

    private final DummyView dashboardView;

    private DummyScene() {
        this.dashboardView = createDummyView();
    }

    private DummyView createDummyView() {
        return new DummyView();
    }

    static DummyScene create() {
        return new DummyScene();
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
