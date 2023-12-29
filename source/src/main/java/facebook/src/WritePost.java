package facebook.src;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The WritePost class represents the functionality for creating a new post in the Facebook application.
 * It includes methods for handling user input, creating posts, and navigating to other scenes.
 */
public class WritePost {

    /**
     * The list of user IDs tagged in the post.
     */
    private final ArrayList<Integer> tags = new ArrayList<>();

    @FXML
    private TextArea post_content;

    @FXML
    private Label confirmationlabel;

    @FXML
    private CheckBox privacy_chkbx;

    /**
     * Adds a new post based on user input.
     *
     * @param mouseEvent The MouseEvent triggered by the user.
     */
    @FXML
    public void add_new_post(MouseEvent mouseEvent) {
        boolean ispublic = true;
        String postcontent;
        if (post_content.getText().trim().isEmpty()) {
            confirmationlabel.setTextFill(Color.RED);
            confirmationlabel.setText("Error: Please write something in the post.");
        } else {
            confirmationlabel.setTextFill(Color.BLUE);
            confirmationlabel.setText("Post created successfully!");
            postcontent = post_content.getText();

            if (privacy_chkbx.isSelected()) {
                ispublic = false;
            }

            LocalDate currentDate = LocalDate.now();
            Post new_post = new Post(DATA.Posts.size() + 1, currentDate, ispublic, postcontent, DATA.currentUser.id, tags);
            DATA.Posts.add(new_post);

            Post p = DATA.Posts.getLast();
            System.out.println(p.post_id + " " + p.content + " " + p.is_public + "  " + p.Date);
            System.out.println("Tags:");
            for (Integer id : tags) {
                System.out.print(" ".concat(DATA.users.get(id - 1).name));
            }
            System.out.println();
        }
    }

    /**
     * Navigates back to the feed scene.
     *
     * @param mouseEvent The MouseEvent triggered by the user.
     * @throws IOException If there is an issue loading the feed scene.
     */
    public void new_post_Back_btn(MouseEvent mouseEvent) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(mouseEvent, "feed.fxml");
    }

    /**
     * Opens a new window for tagging users in the post.
     *
     * @throws IOException If there is an issue loading the Tag_User scene.
     */
    @FXML
    private void tag_btn() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Tag_User.fxml"));
        Parent root = loader.load();
        Tag_User tagUser = loader.getController();
        tagUser.tags = tags;

        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setTitle("Tag Users");
        window.setScene(scene);
        window.show();
    }
}
