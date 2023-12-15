package facebook.src;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;

public class Tag_User extends Search_For_User{
    @FXML
    private TextField text;
    @FXML private GridPane grid;
    public ArrayList<Integer> tags;

    @FXML private void search(){
        view_Found(find_user(text.getText(), DATA.users));
    }
    @Override
    public void view_Found(HashMap<Integer, ArrayList<User>> users_sorted){
        grid.getChildren().clear();
        int i = -1;
        for(ArrayList<User> x : users_sorted.values()) {
            if (i >= 25) break;
            for (User user : x) {
                ImageView profile_photo = new ImageView(new Image(user.profile_photo_path, 50, 50, false, false));
                grid.add(profile_photo, 0, ++i);

                Label userName = new Label(user.name);
                userName.setWrapText(true);
                grid.add(userName, 1, i);

                Label userEmail = new Label("Email: ".concat("user.email"));
                userEmail.setWrapText(true);
                grid.add(userEmail, 2, i);

                Button tag_button = new Button();
                if(tags.contains((Integer) user.id)) tag_button.setText("Tagged");
                else{
                    tag_button.setText("Tag");
                    tag_button.setOnAction(event -> {
                        tag(tag_button, user);
                    });
                }

                grid.add(tag_button, 3, i);

                grid.getRowConstraints().add(i, rowConstraints);
            }
        }
    }
    private void tag(Button button, User user){
        tags.add((Integer) user.id);
        button.setText("Tagged");
    }
}
