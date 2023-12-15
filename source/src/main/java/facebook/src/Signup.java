package facebook.src;


import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.fxml.FXML;

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


    public void usercreateacc(ActionEvent event) throws IOException {


        if (name.getText().isEmpty() || password.getText().isEmpty() || gender.getText().isEmpty() || date.getValue() == null || email.getText().isEmpty() || !isValidEmail(email.getText())) {
            noinfo.setText("Please enter valid data, including a valid email address.");
        } else {
            noinfo.setText("Account created successfully!");
            int id = DATA.users.size()+1;
            User user = new User(id, name.getText(), email.getText(), gender.getText(), date.getValue(), password.getText(), new ArrayList<Integer>(), new ArrayList<Integer>());
            DATA.users.add(user);
            Scene_Changer scene_changer = new Scene_Changer();
            scene_changer.loadAndSetScene(event, "Login.fxml");
        }
    }


    private boolean isValidEmail(String email) {
        return email.contains("@");
    }
}

