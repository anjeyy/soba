module org.anjeyy.soba {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.jfoenix;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.anjeyy.soba to javafx.fxml;
    exports org.anjeyy.soba;
    exports org.anjeyy.soba.common;
    opens org.anjeyy.soba.common to javafx.fxml;
    exports org.anjeyy.soba.scene;
    opens org.anjeyy.soba.scene to javafx.fxml;
    exports org.anjeyy.soba.resize;
    opens org.anjeyy.soba.resize to javafx.fxml;
}
