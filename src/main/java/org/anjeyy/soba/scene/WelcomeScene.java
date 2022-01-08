package org.anjeyy.soba.scene;

import javafx.scene.Parent;
import org.anjeyy.soba.common.MainView;
import org.anjeyy.soba.screen.ScreenModel;
import org.anjeyy.soba.welcome.WelcomeController;
import org.anjeyy.soba.welcome.WelcomeModel;
import org.anjeyy.soba.welcome.WelcomeView;

public class WelcomeScene implements SceneView {

    private final WelcomeView welcomeView;

    private WelcomeScene() {
        this.welcomeView = createWelcomeView();
    }

    static WelcomeScene create() {
        return new WelcomeScene();
    }

    private WelcomeView createWelcomeView() {
        ScreenModel screenModel = ScreenModel.INSTANCE;
        WelcomeModel welcomeModel = new WelcomeModel(screenModel);
        WelcomeController welcomeController = new WelcomeController(welcomeModel);
        return new WelcomeView(welcomeController, welcomeModel);
    }

    @Override
    public MainView createView() {
        return welcomeView;
    }

    @Override
    public Parent asParent() {
        return welcomeView.asParent();
    }
}
