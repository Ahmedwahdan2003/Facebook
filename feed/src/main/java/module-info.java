module com.example.feed {
    requires javafx.controls;
    requires javafx.fxml;


    opens facebook.src to javafx.fxml;
    exports facebook.src;
}