package facebook.src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public Stage window;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window = new Stage();
        window.setTitle("FaceBook");
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event -> {
            event.consume();
            exit();
        });
    }
    public void exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you Sure?");
        if(alert.showAndWait().get() == ButtonType.OK){
            window.close();
        }
    }
    public static void main(String[] args) {
        DATA.read();
        System.out.println(DATA.users.getFirst().getEmail());
        launch();
        //DATA.writeDataToFile();
    }
}