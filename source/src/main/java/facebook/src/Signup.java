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

    private static final String ACCOUNTS_FILE = "C:\\Users\\RAWAN\\Desktop\\accounts.txt";

    public void usercreateacc(ActionEvent event) throws IOException {


        if (name.getText().isEmpty() || password.getText().isEmpty() || gender.getText().isEmpty() || date.getValue() == null || email.getText().isEmpty() || !isValidEmail(email.getText())) {
            noinfo.setText("Please enter valid data, including a valid email address.");
        } else {

            updateAccountsFile(email.getText(), password.getText(), name.getText(), gender.getText(), date.getValue());

            noinfo.setText("Account created successfully!");
            Scene_Changer scene_changer = new Scene_Changer();
            scene_changer.loadAndSetScene(event, "Login.fxml");
        }
    }

    private void updateAccountsFile(String email, String password, String name, String gender, LocalDate date) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(ACCOUNTS_FILE), StandardOpenOption.APPEND)) {
            String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String accountInfo = String.format("%n%s:%s:%s:%s:%s%n", email, password, name, gender, formattedDate);
            writer.write(accountInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }
}

