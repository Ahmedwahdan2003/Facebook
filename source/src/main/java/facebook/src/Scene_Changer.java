package facebook.src;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Scene_Changer class facilitates changing scenes in a JavaFX application.
 */
public class Scene_Changer {

    /**
     * The root node of the loaded scene.
     */
    public Parent root;

    /**
     * The FXMLLoader used for loading the FXML file.
     */
    public FXMLLoader loader;

    /**
     * Loads and sets a new scene based on the specified FXML file.
     *
     * @param event         The event triggering the scene change.
     * @param fxmlFileName The name of the FXML file to load.
     * @throws IOException If an I/O error occurs during FXML file loading.
     */
    public void loadAndSetScene(Event event, String fxmlFileName) throws IOException {
        // Load the FXML file
        loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        root = loader.load();

        // Get the main window from the current event
        Main main = new Main();
        main.window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        Scene scene = new Scene(root);
        main.window.setScene(scene);
        main.window.show();
    }
}
