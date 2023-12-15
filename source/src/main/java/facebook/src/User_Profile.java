package facebook.src;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class User_Profile {

    @FXML private ImageView imageView;
    @FXML private Label id;
    @FXML private Label username;
    @FXML private Label userEmail;
    @FXML private Label userGender;
    @FXML private Label userBirth;
    @FXML private Label friends_num;
    @FXML private Button change;
    @FXML private Button add_btn;
    @FXML private Button restrict_btn;
    public static User viewedUser;
    public void setInfo(User user){
        viewedUser = user;
        if(user.id != DATA.currentUser.id) change.setVisible(false);
        else{
            add_btn.setVisible(false);
            restrict_btn.setVisible(false);
        }
        id.setText("User ID: ".concat(Integer.toString(user.id)));
        username.setText("UserName: ".concat(user.name));
//        userEmail.setText("Email: ".concat(user.email));
//        userGender.setText("Gender: ".concat(user.gender));
//        userBirth.setText("BirthDate: ".concat(user.birthdate));
        friends_num.setText("Number of Friends: ".concat(Integer.toString(user.friends.size())));
        imageView.setImage(new Image(user.profile_photo_path));
        if (DATA.currentUser.friends.contains((Integer)user.id)){
            add_btn.setText("Friend");
        }
        if (DATA.currentUser.restricted_users.contains((Integer)user.id)){
            restrict_btn.setText("Restricted");
        }
    }
    @FXML private void changeInfo(Event event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "ChangeInfo.fxml");
    }
    @FXML private void Back(Event event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "feed.fxml");
    }
    @FXML private void add(){
        if(add_btn.getText().equals("Friend")){
            DATA.currentUser.friends.remove((Integer) viewedUser.id);
            add_btn.setText("Add Friend");
        }
        else{
            DATA.currentUser.friends.add((Integer) viewedUser.id);
            add_btn.setText("Added");
        }
    }
    @FXML private void friends(Event event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "Friends_View.fxml");

        Friends_View friendsView = scene_changer.loader.getController();
        for(int i : viewedUser.friends){
            friendsView.user_Friends.add(DATA.users.get(i - 1));
        }
    }
    @FXML private void restrict(){
        if(restrict_btn.getText().equals("Restricted")){
            DATA.currentUser.restricted_users.remove((Integer) viewedUser.id);
            restrict_btn.setText("Restrict Friend");
        }
        else{
            DATA.currentUser.restricted_users.add((Integer)viewedUser.id);
            restrict_btn.setText("Restricted");
        }
    }
}
