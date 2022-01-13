package org.anjeyy.soba.dashboard;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.geometry.Insets;
import org.anjeyy.soba.screen.ScreenModel;

public class DashboardModel {

    private final Insets masonryRegionPadding;
    private final ReadOnlyDoubleWrapper yearButtonWidth;
    private final ReadOnlyDoubleWrapper yearButtonHeight;

    public DashboardModel(ScreenModel screenModel) {
        this.masonryRegionPadding = initMasonryPanePadding(screenModel);
        this.yearButtonWidth = initYearButtonWrapper(screenModel);
        this.yearButtonHeight = initYearButtonHeightWrapper(screenModel);
        bindYearButtonScalingSize(screenModel);
    }

    private static Insets initMasonryPanePadding(ScreenModel screenModel) {
        double initialHeight = screenModel.getInitialHeight() * 0.1;
        return new Insets(initialHeight);
    }

    private static ReadOnlyDoubleWrapper initYearButtonWrapper(ScreenModel screenModel) {
        DoubleBinding widthScaledProperty = scaleLogoWidthProperty(screenModel);
        return new ReadOnlyDoubleWrapper(widthScaledProperty.get());
    }

    private static ReadOnlyDoubleWrapper initYearButtonHeightWrapper(ScreenModel screenModel) {
        DoubleBinding heightScaledProperty = scaleLogoHeightProperty(screenModel);
        return new ReadOnlyDoubleWrapper(heightScaledProperty.get());
    }

    private void bindYearButtonScalingSize(ScreenModel screenModel) {
        DoubleBinding scaledWidth = scaleLogoWidthProperty(screenModel);
        yearButtonWidth.bind(scaledWidth);
        DoubleBinding scaledHeight = scaleLogoHeightProperty(screenModel);
        yearButtonHeight.bind(scaledHeight);
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
        return readOnlyDoubleProperty.divide(8);
    }

    public Insets masonryRegionPadding() {
        return masonryRegionPadding;
    }

    public ReadOnlyDoubleWrapper yearButtonWidthProperty() {
        return yearButtonWidth;
    }

    public ReadOnlyDoubleWrapper yearButtonHeightProperty() {
        return yearButtonHeight;
    }
}
