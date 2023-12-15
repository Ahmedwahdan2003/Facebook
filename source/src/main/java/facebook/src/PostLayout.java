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

import java.io.IOException;

public class PostLayout extends VBox {

    protected Label postAuthorUsername;
    protected HBox taggedUsersHBox;
    protected TextArea postContent;
    protected Button postLikeBtn;
    protected Button commentPostBtn;

     protected int author_id;
     protected int post_id;
     protected int logged_in_user_id;

    public PostLayout(int author_id,int post_id,int logged_in_user_id) {
        this.author_id=author_id;
        this.post_id=post_id;
        this.logged_in_user_id=logged_in_user_id;
        initialize();
        setHandlers();
    }

    protected void initialize() {
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
        postAuthorUsername.setText(author.getName());

        //for (String taggedUser : post.getTaggedUsers()) {
            Label userLabel = new Label("taggedUser");
            taggedUsersHBox.getChildren().add(userLabel);
        //}
        postContent.setText(p.content);


        return this; // Return the VBox layout
    }
    private boolean isLiked=false;
    private void setHandlers() {
        commentPostBtn.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    handleCommentButtonClick(actionEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        postLikeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLikeButtonClick();
            }
        });
    }

    private void handleCommentButtonClick(ActionEvent event) throws IOException {
        Comment comment1 = new Comment("Comment1", 12);
        Comment comment2 = new Comment("Comment2", 12);
        Comment comment3 = new Comment("Comment3", 12);
        Comment comment4 = new Comment("Comment4", 12);
        Comment comment5 = new Comment("Comment5", 12);
        DATA.Comments.add(comment1);
        DATA.Comments.add(comment2);
        DATA.Comments.add(comment3);
        DATA.Comments.add(comment4);
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(event,"comments.fxml");
        System.out.println(post_id);
        CommentController commentController = scene_changer.get_loader().getController();
        for(Comment comment:DATA.Comments){
            if(comment.getPostId()==post_id){
                VBox CommentVbox = commentController.createCommentBox(comment);
                commentController.commentsContainer.getChildren().add(CommentVbox);
            }
        }
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
