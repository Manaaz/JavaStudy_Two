module com.example.lesson4_jfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.lesson4_jfx to javafx.fxml;
    exports com.example.lesson4_jfx;
}