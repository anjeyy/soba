package org.anjeyy.soba.welcome;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import org.anjeyy.soba.screen.ScreenModel;

public class WelcomeModel {

    private final ReadOnlyDoubleWrapper logoWidth;
    private final ReadOnlyDoubleWrapper logoHeight;

    public WelcomeModel(ScreenModel screenModel) {
        this.logoWidth = initWidthWrapper(screenModel);
        this.logoHeight = initHeightWrapper(screenModel);
        bindLogoScalingSize(screenModel);
    }

    private ReadOnlyDoubleWrapper initWidthWrapper(ScreenModel screenModel) {
        DoubleBinding widthScaledProperty = scaleLogoWidthProperty(screenModel);
        return new ReadOnlyDoubleWrapper(widthScaledProperty.get());
    }

    private ReadOnlyDoubleWrapper initHeightWrapper(ScreenModel screenModel) {
        DoubleBinding heightScaledProperty = scaleLogoHeightProperty(screenModel);
        return new ReadOnlyDoubleWrapper(heightScaledProperty.get());
    }

    private void bindLogoScalingSize(ScreenModel screenModel) {
        DoubleBinding scaledWidth = scaleLogoWidthProperty(screenModel);
        logoWidth.bind(scaledWidth);
        DoubleBinding scaledHeight = scaleLogoHeightProperty(screenModel);
        logoHeight.bind(scaledHeight);
    }

    private static DoubleBinding scaleLogoWidthProperty(ScreenModel screenModel) {
        ReadOnlyDoubleProperty widthProperty = screenModel.widthProperty();
        return scaleLogoProperty(widthProperty);
    }

    private static DoubleBinding scaleLogoHeightProperty(ScreenModel screenModel) {
        ReadOnlyDoubleProperty heightProperty = screenModel.heightProperty();
        return scaleLogoProperty(heightProperty);
    }

    private static DoubleBinding scaleLogoProperty(ReadOnlyDoubleProperty readOnlyDoubleProperty) {
        return readOnlyDoubleProperty.divide(4);
    }

    public ReadOnlyDoubleProperty logoWidthProperty() {
        return logoWidth.getReadOnlyProperty();
    }

    public ReadOnlyDoubleProperty logoHeightProperty() {
        return logoHeight.getReadOnlyProperty();
    }

}
