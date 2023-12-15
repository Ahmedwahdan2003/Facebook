package facebook.src;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene_Changer {

    public Parent root;
    public FXMLLoader loader;
    public void loadAndSetScene(Event event, String fxmlFileName) throws IOException{
        // Load the FXML file
        loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        root = loader.load();
        Main main = new Main();
        main.window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        main.window.setScene(scene);
        main.window.show();
    }

}
