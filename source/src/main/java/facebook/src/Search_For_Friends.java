package facebook.src;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Search_For_Friends {
    @FXML private TextField textField;
    @FXML private GridPane gridPane;
    private final RowConstraints rowConstraints = new RowConstraints();

    public Search_For_Friends() {
        rowConstraints.setMinHeight(50);
    }

    @FXML private void search(){
        gridPane.getChildren().clear();

        HashMap<Integer, ArrayList<User>> users = find_user(textField.getText());

        int i = -1;
        for(ArrayList<User> x : users.values())for (User user : x){
            Label user_text = new Label("UserName: ");
            gridPane.add(user_text, 0, ++i);

            Label userName = new Label(user.name);
            gridPane.add(userName, 1, i);

            Label id_text = new Label("ID: ");
            gridPane.add(id_text, 2, i);

            Label userId = new Label(Integer.toString(user.id));
            gridPane.add(userId, 3, i);

            Button profile_button = new Button("Profile View");
            profile_button.setOnAction(event -> {
                try {
                    profile_view(event, user);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            gridPane.add(profile_button,4 , i);

            gridPane.getRowConstraints().add(i, rowConstraints);
        }
    }
    private HashMap<Integer, ArrayList<User>> find_user(String name){
        name = name.toLowerCase();
        HashMap<Integer, ArrayList<User>> names = new HashMap<>();
        Integer i;
        for(User x : DATA.users){
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
    private void profile_view(Event event, User user) throws IOException {
        DATA.currentUser = user;
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "User_Profile.fxml");

        User_Profile userProfile = scene_changer.loader.getController();
        userProfile.setInfo(user);
    }
    @FXML private void Back(Event event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "feed.fxml");
    }
}