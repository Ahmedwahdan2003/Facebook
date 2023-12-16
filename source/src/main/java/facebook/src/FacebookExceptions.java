package facebook.src;

import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FacebookExceptions extends Exception {

    public FacebookExceptions(){
        super("The file is null or The Extensions is not correct");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong Input");
        alert.setContentText("The file is null or The Extensions is not correct");
        alert.showAndWait();
    }
    public FacebookExceptions(String path){
        super("The image/file in " + path + " Not Found");
    }
    public static boolean WrongInput(String name, String password, String gender, LocalDate date, String email){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wrong Input");
        
        if (name.isEmpty() || name.startsWith(" ")){
            alert.setHeaderText("The data can't be null or start with space Character");
        } else if (date == null || LocalDateTime.now().getYear() - date.getYear() < 13) {
            alert.setHeaderText("You Should be at least 13 years old to make account");
        } else if(!gender.equalsIgnoreCase("female") && !gender.equalsIgnoreCase("male")){
            alert.setHeaderText("The Gender Should be Male or Female");
        } else if (!email.contains("@") || email.contains(" ")) {
            alert.setHeaderText("The Email is not correct");
        } else if (password.isEmpty() || password.contains(" ") || password.length() < 8) {
            alert.setHeaderText("The Password should be at least 8 characters without any space");
        }
        else return true;
        alert.setContentText("Note: Clear the space before and after the input");
        alert.showAndWait();
        return false;
    }

}
