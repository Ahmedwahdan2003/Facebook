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

/**
 * Controller class for searching and displaying user information in the JavaFX application.
 */
public class Search_For_User {

    @FXML private TextField textField;
    @FXML private GridPane grid;
    protected final RowConstraints rowConstraints = new RowConstraints();

    /**
     * Default constructor for the Search_For_User class.
     */
    public Search_For_User() {
        rowConstraints.setMinHeight(70);
    }

    /**
     * Displays the found users in the UI grid based on the search results.
     *
     * @param users_sorted A HashMap containing the sorted users based on the search query.
     * @throws FacebookExceptions If an exception related to Facebook occurs.
     */
    public void view_Found(HashMap<Integer, ArrayList<User>> users_sorted) throws FacebookExceptions {
        grid.getChildren().clear();

        int i = -1;
        for (ArrayList<User> x : users_sorted.values()) {
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

    /**
     * Handles the search action triggered by the user.
     *
     * @throws FacebookExceptions If an exception related to Facebook occurs.
     */
    @FXML private void search() throws FacebookExceptions {
        view_Found(find_user(textField.getText(), DATA.users));
    }

    /**
     * Finds users based on the provided search query and returns a sorted HashMap.
     *
     * @param name  The search query (user name).
     * @param users The list of users to search within.
     * @return A HashMap containing sorted users based on the search query.
     */
    protected HashMap<Integer, ArrayList<User>> find_user(String name, ArrayList<User> users) {
        name = name.toLowerCase();
        HashMap<Integer, ArrayList<User>> names = new HashMap<>();
        Integer i;
        for (User x : users) {
            i = x.name.toLowerCase().indexOf(name);
            if (i != -1) {
                ArrayList<User> p = new ArrayList<>();
                if (names.get(i) != null) p = names.get(i);
                p.add(x);
                names.put(i, p);
            }
        }
        return names;
    }

    /**
     * Handles the event when a user profile is viewed.
     *
     * @param event The event triggering the profile view.
     * @param user  The user whose profile is being viewed.
     * @throws IOException          If an I/O error occurs.
     * @throws FacebookExceptions   If an exception related to Facebook occurs.
     */
    protected void profile_view(Event event, User user) throws IOException, FacebookExceptions {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "User_Profile.fxml");

        User_Profile userProfile = scene_changer.loader.getController();
        userProfile.setInfo(user);
    }

    /**
     * Handles the event when the user navigates back to the feed.
     *
     * @param event The event triggering the navigation back to the feed.
     * @throws IOException          If an I/O error occurs.
     * @throws FacebookExceptions   If an exception related to Facebook occurs.
     */
    @FXML protected void Back(Event event) throws IOException, FacebookExceptions {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "feed.fxml");
    }
}
