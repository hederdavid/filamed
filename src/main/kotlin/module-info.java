module com.example.filamed2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.example.filamed2.home to javafx.fxml;
    exports com.example.filamed2;
}