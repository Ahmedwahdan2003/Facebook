package facebook.src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class feed_Controller {
    @FXML
    private BorderPane mainContainer;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox feedContainer;

    //privateArrayList<post>recommended_posts = new ArrayList<>();


    private final User logged_in_user_id = new User(DATA.getUserById(1));
    recommendation_system recommend_posts = new recommendation_system(logged_in_user_id);
   ArrayList<post>Feed = recommend_posts.get_logged_in_user_feed();

    public void initialize() {
        // initialization logic

        Feed.sort((post1, post2) -> post2.Date.compareTo(post1.Date));
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(post p:Feed){
            Label postLabel = p.createFixedPost();
            feedContainer.getChildren().add(postLabel);
        }

        homeIcon.setImage(homeiconSelected);
    }
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView searchIcon;
    @FXML
    private ImageView chatIcon;
    @FXML
    private Button chat_btn;
    @FXML
    private Button search_btn;
    @FXML
    private Button home_btn;

    private final Image homeiconDefault = new Image(getClass().getResourceAsStream("/images/home.png"));
    private final Image homeiconSelected = new Image(getClass().getResourceAsStream("/images/home_blue.png"));

    private final Image searchiconDefault = new Image(getClass().getResourceAsStream("/images/search.png"));

    private final Image searchiconSelected = new Image(getClass().getResourceAsStream("/images/search_blue.png"));

    private final Image chaticonDefault = new Image(getClass().getResourceAsStream("/images/chat.png"));
    private final Image chaticonSelected = new Image(getClass().getResourceAsStream("/images/chat_blue.png"));


    public void home_btn_clicked(MouseEvent mouseEvent) {
        if (homeIcon.getImage().equals(homeiconDefault)) {
            homeIcon.setImage(homeiconSelected);

            // home_btn.setStyle("-fx-border-color: #1877f2;");

        } else {
            homeIcon.setImage(homeiconDefault);
            //home_btn.setStyle("-fx-border-color: black;");
        }


    }

    public void search_btn_clicked(MouseEvent mouseEvent) {
        if (searchIcon.getImage().equals(searchiconDefault)) {
            searchIcon.setImage(searchiconSelected);
           // search_btn.setStyle("-fx-border-color: #1877f2;");

        } else {
            searchIcon.setImage(searchiconDefault);
           // search_btn.setStyle("-fx-border-color: black;");
        }


    }

    public void chat_btn_clicked(MouseEvent mouseEvent) {

        if (chatIcon.getImage().equals(chaticonDefault)) {
            chatIcon.setImage(chaticonSelected);
            //chat_btn.setStyle("-fx-border-color: #1877f2;");

        } else {
            chatIcon.setImage(chaticonDefault);
            //chat_btn.setStyle("-fx-border-color: black;");

        }


    }

        private Scene scene;
        private Parent root;
    public void open_write_post_scene(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("write_post.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    //scene_changer.loadAndSetScene("write_post.fxml","New Post!");
    }
}
