module com.example.hogwarts {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hogwarts to javafx.fxml;
    exports com.example.hogwarts;
}