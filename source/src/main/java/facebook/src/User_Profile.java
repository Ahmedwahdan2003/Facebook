package facebook.src;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;

/**
 * The User_Profile class represents the user profile view in the Facebook application.
 * It includes methods for displaying user information, handling button actions,
 * and navigating to other scenes.
 */
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
    @FXML private Button friends_btn;

    /**
     * The User object representing the currently viewed user's profile.
     */
    public static User viewedUser;

    /**
     * Sets the information for the viewed user's profile.
     *
     * @param user The User object representing the viewed user.
     * @throws FacebookExceptions If there is an issue with loading user information.
     */
    public void setInfo(User user) throws FacebookExceptions {
        viewedUser = user;
        if (user.id != DATA.currentUser.id) change.setVisible(false);
        else {
            add_btn.setVisible(false);
            restrict_btn.setVisible(false);
        }
        id.setText("User ID: ".concat(Integer.toString(user.id)));
        username.setText(user.name);
        userEmail.setText("Email: ".concat(user.Email));
        userGender.setText("Gender: ".concat(user.gender));
        userBirth.setText("BirthDate: ".concat(user.Date.toString()));
        if (user.friends.isEmpty()) {
            friends_num.setText("No Friends");
            friends_btn.setVisible(false);
        } else friends_num.setText("Number of Friends: ".concat(Integer.toString(user.friends.size())));
        File f = new File(user.profile_photo_path.substring(6));
        if (f.exists()) {
            imageView.setImage(new Image(user.profile_photo_path));
        } else {
            throw new FacebookExceptions(user.profile_photo_path);
        }
        if (DATA.currentUser.friends.contains(user.id)) {
            add_btn.setText("Friend");
        }
        if (DATA.currentUser.restricted_users.contains(user.id)) {
            restrict_btn.setText("Restricted");
        }
    }

    /**
     * Handles the event when the user wants to change their information.
     *
     * @param event The event triggered by the user.
     * @throws IOException If there is an issue loading the ChangeInfo scene.
     */
    @FXML private void changeInfo(Event event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "ChangeInfo.fxml");
    }

    /**
     * Handles the event when the user wants to go back to the feed.
     *
     * @param event The event triggered by the user.
     * @throws IOException If there is an issue loading the feed scene.
     */
    @FXML private void Back(Event event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "feed.fxml");
    }

    /**
     * Handles the event when the user wants to add or remove a friend.
     */
    @FXML private void add() {
        if (add_btn.getText().equals("Friend")) {
            DATA.currentUser.friends.remove((Integer) viewedUser.id);
            add_btn.setText("Add Friend");
        } else {
            DATA.currentUser.friends.add(viewedUser.id);
            add_btn.setText("Added");
        }
    }

    /**
     * Handles the event when the user wants to view the friends of the currently viewed user.
     *
     * @param event The event triggered by the user.
     * @throws IOException If there is an issue loading the Friends_View scene.
     */
    @FXML private void friends(Event event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "Friends_View.fxml");

        Friends_View friendsView = scene_changer.loader.getController();
        for (int i : viewedUser.friends) {
            friendsView.user_Friends.add(DATA.users.get(i - 1));
        }
    }

    /**
     * Handles the event when the user wants to restrict or unrestrict a friend.
     */
    @FXML private void restrict() {
        if (restrict_btn.getText().equals("Restricted")) {
            DATA.currentUser.restricted_users.remove((Integer) viewedUser.id);
            restrict_btn.setText("Restrict Friend");
        } else {
            DATA.currentUser.restricted_users.add(viewedUser.id);
            restrict_btn.setText("Restricted");
        }
    }
}
