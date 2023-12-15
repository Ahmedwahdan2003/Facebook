package facebook.src;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ChangeInfo {
    @FXML private TextField newName;
    @FXML private TextField currentPass;
    @FXML private TextField newPass;
    @FXML private TextField newPhotoPath;
    @FXML  private void setImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );

        Main main = new Main();
        File file = fileChooser.showOpenDialog(main.window);

        if (file != null) {
            newPhotoPath.setText("file:/".concat(file.getPath().replace(" ", "%20").replace("\\", "/")));
        } else  {
            System.out.println("error"); // or something else
        }
    }
    @FXML private void save(Event event) throws IOException {
        if(newPass.getText().startsWith(" ") || newName.getText().startsWith(" ")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save Failed");
            alert.setHeaderText("The password and username can't start with space Character");
            alert.showAndWait();
        }
        else if(currentPass.getText().equals(DATA.currentUser.password)){
            if(!(newName.getText().isEmpty())) DATA.currentUser.name = newName.getText();
            if(!(newPass.getText().isEmpty())) DATA.currentUser.password = newPass.getText();
            if(!(newPhotoPath.getText().isEmpty())) DATA.currentUser.profile_photo_path = newPhotoPath.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save Success");
            alert.setHeaderText("The changes have been saved");
            alert.showAndWait();

            Scene_Changer scene_changer = new Scene_Changer();
            scene_changer.loadAndSetScene(event, "User_Profile.fxml");

            User_Profile userProfile = scene_changer.loader.getController();
            userProfile.setInfo(DATA.currentUser);

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save Failed");
            alert.setHeaderText("Wrong Password");
            alert.showAndWait();
        }
    }
    @FXML private void discard(Event event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Discard Changes");
        alert.setHeaderText("All changes won't be saved!");
        alert.setContentText("Are you Sure?");
        if(alert.showAndWait().get() == ButtonType.OK){
            Scene_Changer scene_changer = new Scene_Changer();
            scene_changer.loadAndSetScene(event, "User_Profile.fxml");

            User_Profile userProfile = scene_changer.loader.getController();
            userProfile.setInfo(DATA.currentUser);
        }
    }
}
