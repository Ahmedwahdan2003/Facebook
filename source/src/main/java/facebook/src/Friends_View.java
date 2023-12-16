package facebook.src;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class Friends_View extends Search_For_User {
    @FXML private TextField text;
    @FXML private GridPane grid;
    public ArrayList<User> user_Friends = new ArrayList<>();

    @FXML private void search() throws FacebookExceptions {
        view_Found(find_user(text.getText(), user_Friends));
    }

    @Override @FXML protected void Back(Event event) throws IOException, FacebookExceptions {
        profile_view(event, User_Profile.viewedUser);
    }
}
