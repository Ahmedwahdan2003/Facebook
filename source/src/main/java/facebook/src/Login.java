package facebook.src;

import facebook.src.Scene_Changer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * The Login class represents the controller for the login functionality in the application.
 */
public class Login {

    @FXML
    private Button login;
    @FXML
    private Label wrong;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button signup;

    private LoginManager loginManager;

    /**
     * Initializes the Login controller.
     */
    public void initialize() {
        loginManager = new LoginManager(DATA.users);
    }

    /**
     * Handles the user login action.
     *
     * @param event The ActionEvent triggered by the login button.
     * @throws IOException If an error occurs while loading the feed scene.
     */
    @FXML
    public void userlogin(ActionEvent event) throws IOException {
        checkLogin(event);
    }

    /**
     * Handles the user signup action, redirecting to the signup scene.
     *
     * @param event The ActionEvent triggered by the signup button.
     * @throws IOException If an error occurs while loading the signup scene.
     */
    @FXML
    public void usersignup(ActionEvent event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "Signup.fxml");
    }

    /**
     * Checks the entered login credentials and redirects to the feed scene if successful.
     *
     * @param event The ActionEvent triggered by the login button.
     * @throws IOException If an error occurs while loading the feed scene.
     */
    private void checkLogin(ActionEvent event) throws IOException {
        String enteredEmail = email.getText();
        String enteredPassword = password.getText();

        if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
            wrong.setText("Please enter your data");
        } else {
            boolean loginSuccessful = loginManager.authenticateUser(enteredEmail, enteredPassword);

            if (loginSuccessful) {
                wrong.setText("Success!");

                Scene_Changer scene_changer = new Scene_Changer();
                scene_changer.loadAndSetScene(event, "feed.fxml");
                System.out.println(DATA.currentUser.getName()+DATA.currentUser.id);
            } else {
                wrong.setText("Wrong email or password");
            }
        }
    }
}
