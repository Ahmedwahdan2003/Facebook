package facebook.src;

import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The FacebookExceptions class represents custom exceptions for handling various issues in the Facebook application.
 */
public class FacebookExceptions extends Exception {

    /**
     * Constructs a new FacebookExceptions instance with a default message and displays an error alert.
     */
    public FacebookExceptions() {
        super("The file is null or The Extensions is not correct");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong Input");
        alert.setContentText("The file is null or The Extensions is not correct");
        alert.showAndWait();
    }

    /**
     * Constructs a new FacebookExceptions instance with a specified path and message.
     *
     * @param path The path where the image/file is not found.
     */
    public FacebookExceptions(String path) {
        super("The image/file in " + path + " Not Found");
    }


    /**
     * Checks for wrong input in user registration and displays appropriate error messages.
     *
     * @param name     The user's name.
     * @param password The user's password.
     * @param gender   The user's gender.
     * @param date     The user's birthdate.
     * @param email    The user's email.
     * @return True if input is valid, false otherwise.
     */
    public static boolean WrongInput(String name, String password, String gender, LocalDate date, String email) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong Input");

        if (name.isEmpty() || name.startsWith(" ")) {
            alert.setHeaderText("The data can't be null or start with space Character");
        } else if (date == null || LocalDateTime.now().getYear() - date.getYear() < 13) {
            alert.setHeaderText("You Should be at least 13 years old to make an account");
        } else if (!gender.equalsIgnoreCase("female") && !gender.equalsIgnoreCase("male")) {
            alert.setHeaderText("The Gender Should be Male or Female");
        } else if (!email.contains("@") || email.contains(" ")) {
            alert.setHeaderText("The Email is not correct");
        } else if (password.isEmpty() || password.contains(" ") || password.length() < 8) {
            alert.setHeaderText("The Password should be at least 8 characters without any space");
        } else {
            return true;
        }

        alert.setContentText("Note: Clear the space before and after the input");
        alert.showAndWait();
        return false;
    }

    public static boolean WrongInput(String name, String password, String email) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong Input");

        if (name.startsWith(" ")) {
            alert.setHeaderText("The data can't start with space Character");
        } else if (!email.contains("@") || email.contains(" ")) {
            alert.setHeaderText("The Email is not correct");
        } else if (password.contains(" ") || password.length() < 8) {
            alert.setHeaderText("The Password should be at least 8 characters without any space");
        } else {
            return true;
        }

        alert.setContentText("Note: Clear the space before and after the input");
        alert.showAndWait();
        return false;
    }

}
