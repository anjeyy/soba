module org.anjeyy.soba {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.anjeyy.soba to javafx.fxml;
    exports org.anjeyy.soba;
}