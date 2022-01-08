package org.anjeyy.soba.common;

import javafx.scene.Parent;

public interface MainView {

    /**
     * Viewable component which is added as part of the main screen.
     * <b>Note:</b> Main screen is part of {@link org.anjeyy.soba.window.WindowToolbarView}.
     *
     * @return viewable Parent
     */
    Parent asParent();
}
