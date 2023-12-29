package facebook.src;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Controller class for handling user tagging functionality.
 * Extends the Search_For_User class to utilize user search functionality.
 */
public class Tag_User extends Search_For_User {

    @FXML
    private TextField text;

    @FXML
    private GridPane grid;

    /**
     * List to store the IDs of users that have been tagged.
     */
    public ArrayList<Integer> tags;

    /**
     * Handles the search action for finding users to tag.
     *
     * @throws FacebookExceptions If there is an exception related to Facebook functionality.
     */
    @FXML
    private void search() throws FacebookExceptions {
        view_Found(find_user(text.getText(), DATA.users));
    }

    /**
     * Overrides the view_Found method to customize the display for user tagging.
     *
     * @param users_sorted A HashMap containing sorted lists of users based on search criteria.
     * @throws FacebookExceptions If there is an exception related to Facebook functionality.
     */
    @Override
    public void view_Found(HashMap<Integer, ArrayList<User>> users_sorted) throws FacebookExceptions {
        grid.getChildren().clear();
        int i = -1;
        for (ArrayList<User> x : users_sorted.values()) {
            if (i >= 25) break;
            for (User user : x) {
                File f = new File(user.profile_photo_path.substring(6));
                if (f.exists()) {
                    ImageView profile_photo = new ImageView(new Image(user.profile_photo_path, 50, 50, false, false));
                    grid.add(profile_photo, 0, ++i);
                } else {
                    throw new FacebookExceptions(user.profile_photo_path);
                }

                Label userName = new Label(user.name);
                userName.setWrapText(true);
                grid.add(userName, 1, i);

                Label userEmail = new Label("Email: ".concat(user.Email)); // Fixed the typo in concatenation
                userEmail.setWrapText(true);
                grid.add(userEmail, 2, i);

                Button tag_button = new Button();
                if (tags.contains(user.id)) tag_button.setText("Tagged");
                else {
                    tag_button.setText("Tag");
                    tag_button.setOnAction(event -> tag(tag_button, user));
                }

                grid.add(tag_button, 3, i);

                grid.getRowConstraints().add(i, rowConstraints);
            }
        }
    }

    /**
     * Tags a user when the "Tag" button is clicked.
     *
     * @param button The Button representing the "Tag" action.
     * @param user   The User to be tagged.
     */
    private void tag(Button button, User user) {
        tags.add(user.id);
        button.setText("Tagged");
    }
}
