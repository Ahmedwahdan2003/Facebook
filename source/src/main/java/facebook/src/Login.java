package facebook.src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void initialize() {
        loginManager = new LoginManager(DATA.users);
    }

    @FXML
    public void userlogin(ActionEvent event) throws IOException {
        checkLogin(event);
    }

    @FXML
    public void usersignup(ActionEvent event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event,"Signup.fxml");
    }


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
                scene_changer.loadAndSetScene(event,"feed.fxml");
            } else {
                wrong.setText("Wrong email or password");
            }
 }
}
}
