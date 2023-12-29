package facebook.src;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The feed_Controller class is the controller for the main feed view in the Facebook application.
 */
public class feed_Controller {

    @FXML
    private BorderPane mainContainer;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox feedContainer;

    private recommendation_system recommend_posts = new recommendation_system();
    private ArrayList<Post> Feed = recommend_posts.get_logged_in_user_feed();

    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView searchIcon;
    @FXML
    private ImageView chatIcon;

    private final Image homeiconDefault = new Image(getClass().getResourceAsStream("/images/home.png"));
    private final Image homeiconSelected = new Image(getClass().getResourceAsStream("/images/home_blue.png"));

    private final Image searchiconDefault = new Image(getClass().getResourceAsStream("/images/search.png"));
    private final Image searchiconSelected = new Image(getClass().getResourceAsStream("/images/search_blue.png"));

    private final Image chaticonDefault = new Image(getClass().getResourceAsStream("/images/chat.png"));
    private final Image chaticonSelected = new Image(getClass().getResourceAsStream("/images/chat_blue.png"));

    /**
     * Initializes the feed view with recommended posts and sets up the UI elements.
     */
    public void initialize() {
        Feed.sort((post1, post2) -> post2.Date.compareTo(post1.Date));
        for (Post p : Feed) {
            PostLayout postLayout = new PostLayout(p.author_id, p.post_id);
            VBox postVBox = postLayout.setPostData(p);
            feedContainer.getChildren().add(postVBox);
        }

        homeIcon.setImage(homeiconSelected);
    }

    /**
     * Handles the click event for the home button.
     *
     * @param mouseEvent The mouse event triggering the button click.
     */
    public void home_btn_clicked(MouseEvent mouseEvent) {
        if (homeIcon.getImage().equals(homeiconDefault)) {
            homeIcon.setImage(homeiconSelected);
        } else {
            homeIcon.setImage(homeiconDefault);
        }
    }

    /**
     * Handles the click event for the search button.
     *
     * @param mouseEvent The mouse event triggering the button click.
     * @throws IOException Thrown if there is an issue with input/output operations.
     */
    public void search_btn_clicked(Event mouseEvent) throws IOException {
        if (searchIcon.getImage().equals(searchiconDefault)) {
            searchIcon.setImage(searchiconSelected);
            Scene_Changer scene_changer = new Scene_Changer();
            scene_changer.loadAndSetScene(mouseEvent, "Search_For_User.fxml");
        } else {
            searchIcon.setImage(searchiconDefault);
        }
    }

    /**
     * Handles the click event for the chat button.
     *
     * @param mouseEvent The mouse event triggering the button click.
     * @throws IOException Thrown if there is an issue with input/output operations.
     */
    public void chat_btn_clicked(MouseEvent mouseEvent) throws IOException {
        if (chatIcon.getImage().equals(chaticonDefault)) {
            chatIcon.setImage(chaticonSelected);

            Scene_Changer scene_changer = new Scene_Changer();
            scene_changer.loadAndSetScene(mouseEvent, "Home.fxml");

            try {
                new Thread(() -> {
                    ServerApp serverApp = new ServerApp();
                    serverApp.startServer(12345);
                }).start();

                Thread.sleep(1000);

                ClientApp clientApp = new ClientApp();

                clientApp.startClient("localhost", 12345);

                chatController chatController = scene_changer.loader.getController();
                chatController.setClient(clientApp);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            chatIcon.setImage(chaticonDefault);
        }
    }

    /**
     * Opens the scene for writing a new post.
     *
     * @param event The action event triggering the button click.
     * @throws IOException Thrown if there is an issue with input/output operations.
     */
    public void open_write_post_scene(ActionEvent event) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event, "write_post.fxml");
    }
}
