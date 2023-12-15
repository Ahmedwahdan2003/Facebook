package facebook.src;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class User_Profile {

    @FXML private ImageView imageView;
    @FXML private Label username;
    @FXML private Label id;
    @FXML private Label friends_num;
    public void setImage(User user) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );

        Main main = new Main();
        File file = fileChooser.showOpenDialog(main.window);

        if (file != null) {
            DATA.currentUser.profile_photo_path = "file:/".concat(file.getPath().replace(" ", "%20").replace("\\", "/"));
            imageView.setImage(new Image(DATA.currentUser.profile_photo_path));
        } else  {
            System.out.println("error"); // or something else
        }
    }
    public void setInfo(User user){
        imageView.setImage(new Image(user.profile_photo_path));
        id.setText("User ID: ".concat(Integer.toString(user.id)));
        username.setText("UserName: ".concat(user.getName()));
        friends_num.setText("Number of Friends: ".concat(Integer.toString(user.friends.size())));
    }
}
