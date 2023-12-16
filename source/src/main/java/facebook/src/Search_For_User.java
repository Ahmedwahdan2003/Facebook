package facebook.src;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Search_For_User {
    @FXML private TextField textField;
    @FXML private GridPane grid;
    protected final RowConstraints rowConstraints = new RowConstraints();

    public Search_For_User() {
        rowConstraints.setMinHeight(70);
    }
    public void view_Found(HashMap<Integer, ArrayList<User>> users_sorted) throws FacebookExceptions {
        grid.getChildren().clear();

        int i = -1;
        for(ArrayList<User> x : users_sorted.values()) {
            if (i >= 25) break;
            for (User user : x) {
                File f = new File(user.profile_photo_path.substring(6));
                if(f.exists()){
                    ImageView profile_photo = new ImageView(new Image(user.profile_photo_path, 50, 50, false, false));
                    grid.add(profile_photo, 0, ++i);
                } else {
                    throw new FacebookExceptions(user.profile_photo_path);
                }

                Label userName = new Label(user.name);
                userName.setWrapText(true);
                grid.add(userName, 1, i);

                Label userEmail = new Label("Email: ".concat(user.Email));
                userEmail.setWrapText(true);
                grid.add(userEmail, 2, i);

                Label friend = new Label("Not Friend");
                if(DATA.currentUser.id == user.id) friend.setText("Current User");
                if(DATA.currentUser.friends.contains((Integer) user.id)) friend.setText("Friend");
                friend.setWrapText(true);
                grid.add(friend, 3, i);

                Button profile_button = new Button("Profile View");
                profile_button.setOnAction(event -> {
                    try {
                        profile_view(event, user);
                    } catch (IOException | FacebookExceptions e) {
                        throw new RuntimeException(e);
                    }
                });
                grid.add(profile_button, 4, i);

                grid.getRowConstraints().add(i, rowConstraints);
            }
        }
    }
    @FXML private void search() throws FacebookExceptions {
        view_Found(find_user(textField.getText(), DATA.users));
    }
    protected HashMap<Integer, ArrayList<User>> find_user(String name, ArrayList<User> users){
        name = name.toLowerCase();
        HashMap<Integer, ArrayList<User>> names = new HashMap<>();
        Integer i;
        for(User x : users){
            i = x.name.toLowerCase().indexOf(name);
            if(i != -1){
                ArrayList<User> p = new ArrayList<>();
                if(names.get(i) != null) p = names.get(i);
                p.add(x);
                names.put(i, p);
            }
        }
        return names;
    }
    protected void profile_view(Event event, User user) throws IOException, FacebookExceptions {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "User_Profile.fxml");

        User_Profile userProfile = scene_changer.loader.getController();
        userProfile.setInfo(user);
    }
    @FXML protected void Back(Event event) throws IOException, FacebookExceptions {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "feed.fxml");
    }
}