package org.anjeyy.soba.window;

import com.jfoenix.controls.JFXButton;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.anjeyy.soba.common.CustomStyleSheet;

class LeftToolbarView implements CustomStyleSheet {

    private final JFXButton appImageButton;
    private final JFXButton appSloganButton;

    private LeftToolbarView() {
        this.appImageButton = createApplicationImageButton();
        this.appSloganButton = createApplicationTitleBar();
    }

    static LeftToolbarView create() {
        return new LeftToolbarView();
    }

    private JFXButton createApplicationImageButton() {
        JFXButton jfxButton = new JFXButton();
        jfxButton.setId("appImage");
        jfxButton.setDisable(true);
        jfxButton.setMinSize(30, 35);
        jfxButton.setMaxSize(30, 35);
        jfxButton.setPadding(new Insets(0, 0, 0, 10));
        createIconImage(jfxButton);
        CustomStyleSheet.super.add(jfxButton, "window-jfx-title-bar-icon");
        return jfxButton;
    }

    private JFXButton createApplicationTitleBar() {
        JFXButton slogan = new JFXButton(" soba - a sober look at your finance ");
        slogan.setId("appSlogan");
        slogan.setDisable(true);
        CustomStyleSheet.super.add(slogan, "window-jfx-title-bar");
        return slogan;
    }

    private void createIconImage(JFXButton jfxButton) {
        InputStream inputStream = Objects.requireNonNull(
            getClass().getResourceAsStream("/image/icon-high-res.png"), "Could not load image.");
        Image rawAppImage = new Image(inputStream);
        ImageView appImageView = new ImageView(rawAppImage);
        appImageView.fitWidthProperty().bind(jfxButton.widthProperty());
        appImageView.fitHeightProperty().bind(jfxButton.heightProperty());
        appImageView.setPreserveRatio(true);
        jfxButton.setGraphic(appImageView);
    }

    List<Node> getNodes() {
        ArrayList<Node> result = new ArrayList<>();
        result.add(appImageButton);
        result.add(appSloganButton);
        return result;
    }
}
