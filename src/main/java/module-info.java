module com.mimdal.bookify {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mimdal.bookify to javafx.fxml;
    exports com.mimdal.bookify;
}