package facebook.src;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Friends_View class represents the view for displaying friends in the Facebook application.
 */
public class Friends_View extends Search_For_User {

    @FXML
    private TextField text;

    @FXML
    private GridPane grid;

    public ArrayList<User> user_Friends = new ArrayList<>();

    /**
     * Searches for users based on the entered text and displays the found users in the view.
     *
     * @throws FacebookExceptions Thrown if there is an issue with the Facebook application.
     */
    @FXML
    private void search() throws FacebookExceptions {
        view_Found(find_user(text.getText(), user_Friends));
    }

    /**
     * Navigates back to the user profile view.
     *
     * @param event The event triggering the back action.
     * @throws IOException          Thrown if there is an issue with input/output operations.
     * @throws FacebookExceptions   Thrown if there is an issue with the Facebook application.
     */
    @Override
    @FXML
    protected void Back(Event event) throws IOException, FacebookExceptions {
        profile_view(event, User_Profile.viewedUser);
    }
}
