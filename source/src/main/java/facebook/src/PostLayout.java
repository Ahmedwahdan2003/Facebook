package facebook.src;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PostLayout extends VBox {

    private Label postAuthorUsername;
    private HBox taggedUsersHBox;
    private TextArea postContent;
    private Button postLikeBtn;
    private Button commentPostBtn;

     private int author_id;
     private int post_id;
     private int logged_in_user_id;

    public PostLayout(int author_id,int post_id,int logged_in_user_id) {
        this.author_id=author_id;
        this.post_id=post_id;
        this.logged_in_user_id=logged_in_user_id;
        initialize();
        setHandlers();
    }

    private void initialize() {
        postAuthorUsername = new Label();
        taggedUsersHBox = new HBox();
        postContent = new TextArea();
        postContent.setWrapText(true);
        postContent.setEditable(false);
        //postContent.setPrefHeight(250);
        //postContent.setPrefWidth(500);  // Set the preferred width

        //postContent.setMaxSize(500,250);
        postContent.setStyle("-fx-control-inner-background: #ffffff;");
        Pane buttonsPane = new Pane();
        postLikeBtn = new Button("LIKE");
        commentPostBtn = new Button("COMMENT");
        buttonsPane.getChildren().addAll(postLikeBtn, commentPostBtn);
        postAuthorUsername.setStyle("-fx-border-color: grey; -fx-border-width: 1px; -fx-padding: 10px;");
        getChildren().addAll(postAuthorUsername, taggedUsersHBox, postContent, buttonsPane);
        buttonsPane.setPrefSize(500, 60);
        buttonsPane.setMaxSize(500,60);
        buttonsPane.setMinSize(500,60);
        setStyle("-fx-border-color: #1877f2; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px;");
        postLikeBtn.setLayoutX(26);
        postLikeBtn.setLayoutY(71);
        setSpacing(10);
        commentPostBtn.setLayoutX(431);
        commentPostBtn.setLayoutY(71);
        setMinSize(600, 400);
        setMaxSize(600, 400);
        postLikeBtn.setTextFill(Color.WHITE);

    }
    Post post = DATA.getPostById(post_id);
    User author = DATA.getUserById(author_id);
    public VBox setPostData(Post p) {
        User author = DATA.getUserById(p.author_id);
        assert author != null;
        postAuthorUsername.setText(author.name);

        //for (String taggedUser : post.getTaggedUsers()) {
            Label userLabel = new Label("taggedUser");
            taggedUsersHBox.getChildren().add(userLabel);
        //}
        postContent.setText(p.content);


        return this; // Return the VBox layout
    }
    private boolean isLiked=false;
    private void setHandlers() {
        postLikeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLikeButtonClick();
            }
        });
    }

    private void handleLikeButtonClick() {
        if (isLiked) {
            // If already liked, change color to grey
            postLikeBtn.setStyle("-fx-background-color: #808080;");
            postLikeBtn.setText("LIKE");
            DATA.Remove_interaction(logged_in_user_id,post_id);
        } else {
            // If not liked, change color to blue
            postLikeBtn.setStyle("-fx-background-color: #1877f2;");
            postLikeBtn.setText("UNLIKE");
            interactions interaction = new interactions(logged_in_user_id,post_id);
            DATA.interactionList.add(interaction);

        }

        // Toggle the liked state
        isLiked = !isLiked;
    }


}
