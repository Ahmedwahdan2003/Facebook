package facebook.src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class is the entry point for the FaceBook application.
 */
public class Main extends Application {

    /**
     * The main window of the application.
     */
    public Stage window;

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command-line arguments.
     * @throws FacebookExceptions If an exception related to Facebook occurs.
     */
    public static void main(String[] args) throws FacebookExceptions {
        // Load existing data from files
        DATA.read();

        // Launch the JavaFX application
        launch();

        // Save data to files when the application exits
        DATA.writeDataToFile();
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        window = new Stage();
        window.setTitle("FaceBook");
        window.setScene(scene);
        window.show();

        // Handle the close request

        window.setOnCloseRequest(event -> {
            event.consume();
            exit();
        });
    }

    /**
     * Displays a confirmation dialog when the user tries to exit the application.
     * Closes the application if the user confirms.
     */
    public void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you Sure?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            window.close();
        }
    }
}
