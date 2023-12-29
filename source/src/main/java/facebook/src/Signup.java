package facebook.src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controller class for handling user sign-up functionality.
 */
public class Signup {

    @FXML
    private Button create;

    @FXML
    private TextField name;

    @FXML
    private TextField gender;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private DatePicker date;

    @FXML
    private Label noinfo;

    /**
     * Handles the "Create Account" button action. Validates user input, creates a new user,
     * adds it to the user list, and transitions to the login scene upon success.
     *
     * @param event The ActionEvent triggered by the "Create Account" button.
     */
    @FXML
    public void usercreateacc(ActionEvent event) throws FacebookExceptions {
        try {
            // Validate user input
            if (FacebookExceptions.WrongInput(name.getText(), password.getText(), gender.getText(), date.getValue(), email.getText())) {
                noinfo.setText("Account created successfully!");

                // Create a new user
                int id = DATA.users.size() + 1;
                User user = new User(id, name.getText(), email.getText(), gender.getText(), date.getValue(), password.getText(), new ArrayList<>(), new ArrayList<>());

                // Set additional user properties
                if (user.gender.equalsIgnoreCase("female")) {
                    user.gender = "Female";
                } else {
                    user.gender = "Male";
                }
                user.profile_photo_path = "file:/C:/Users/ahmed/Desktop/facebook_source/source/src/main/resources/images/profile.png";
                user.likedPosts = new ArrayList<>();

                // Add the new user to the list
                DATA.users.add(user);

                // Transition to the login scene
                Scene_Changer scene_changer = new Scene_Changer();
                scene_changer.loadAndSetScene(event, "Login.fxml");
            }
        } catch (IOException e) {
            // Handle exceptions (e.g., display an error message)
            e.printStackTrace();
        }
    }
}
