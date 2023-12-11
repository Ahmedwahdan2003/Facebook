package facebook.src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class feed_main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("feed.fxml"));
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }




    public static void main(String[] args) {
        DATA.read();
        launch();

    }
}