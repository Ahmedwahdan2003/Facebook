package facebook.src;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
public class scene_changer {

    public static void loadAndSetScene(String fxmlFileName, String title) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(feed_main.class.getResource(fxmlFileName));
            Parent root = loader.load();

            // Create the scene
            Scene scene = new Scene(root);

            // Create a new stage
            Stage stage = new Stage();

            // Set the title and scene for the new stage
            stage.setTitle(title);
            stage.setScene(scene);

            // Show the new stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
