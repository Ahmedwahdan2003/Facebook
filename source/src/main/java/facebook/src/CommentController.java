package facebook.src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CommentController{
    public int post_id;
   //public CommentController(){
      //  initialize();
   // }


    ;
    @FXML
    private TextField commentTextField;


    @FXML
    public VBox commentsContainer;
    @FXML
    private Label errorMessageLabel;


    public VBox createCommentBox(Comment comment) {
        TextField commentTextField = new TextField (comment.getCommentText());
        commentTextField.setEditable(false);

        Label timestampLabel = new Label(comment.getFormattedTimestamp());
        Button CommentlikeButton = new Button("Like");
        CommentlikeButton.setOnAction(event -> handleLikeCommentButton(CommentlikeButton));

        Button replyButton = new Button("Reply");
        replyButton.setOnAction(event -> handleReplyButton(replyButton));

        VBox commentBox = new VBox(commentTextField, timestampLabel, CommentlikeButton, replyButton);
        commentBox.setSpacing(10);
        commentBox.setStyle("-fx-border-color:#1877f2; -fx-border-width: 2px; -fx-padding: 10px;");

        return commentBox;
    }

    private HBox createReplyBox(Reply reply) {
        TextField commentTextField = new TextField (reply.getCommentText());
        commentTextField.setEditable(false);

        Label timestampLabel = new Label(reply.getFormattedTimestamp());
        Button likeButton = new Button("Like");
        likeButton.setOnAction(event -> handleLikeReplyButton(likeButton));



        HBox replyBox = new HBox(commentTextField, timestampLabel, likeButton);
        replyBox.setSpacing(10);

        return replyBox;
    }
    public void submitComment() {
        String newCommentText = commentTextField.getText().trim();

        if (!newCommentText.isEmpty()) {
            Comment newComment = new Comment(newCommentText,DATA.Comments.size()+1);


            VBox commentBox = createCommentBox(newComment);

            commentsContainer.setSpacing(10);
            commentsContainer.getChildren().add(commentBox);


            commentTextField.clear();

            errorMessageLabel.setText("");
        } else {

            errorMessageLabel.setText("Please enter a non-empty comment.");
        }
    }

    boolean isClickedComment = false;
    @FXML
    private void handleLikeCommentButton(Button commentlikeButton) {
        if (isClickedComment) {
            // If already liked, change color to grey
            commentlikeButton.setStyle("-fx-background-color: #808080;");
            commentlikeButton.setText("LIKE");

        } else {
            // If not liked, change color to blue
            commentlikeButton.setStyle("-fx-background-color: #1877f2;");
            commentlikeButton.setText("UNLIKE");



        }

        // Toggle the liked state
        isClickedComment = !isClickedComment;
    }


    private void handleReplyButton(Button replybutton) {
        String replyText = commentTextField.getText();
        if (!replyText.isEmpty()) {
            Reply newreply = new Reply(replyText,1);
            // Create an HBox for the reply
            HBox replyHBox = createReplyBox(newreply);

            // Find the parent comment's HBox
            Node parentCommentVBox = replybutton.getParent();
            if (parentCommentVBox instanceof VBox) {
                ((VBox) parentCommentVBox).getChildren().add(replyHBox);
            }
            else {
                errorMessageLabel.setText("Please enter a non-empty reply.");
            }
        }

        // Clear the reply TextField
        commentTextField.clear();

    }
    boolean isClickedReply = false;
    @FXML
    private void handleLikeReplyButton(Button likeButton) {
        if (isClickedReply) {
            // If already liked, change color to grey
            likeButton.setStyle("-fx-background-color: #808080;");
            likeButton.setText("LIKE");

        } else {
            // If not liked, change color to blue
            likeButton.setStyle("-fx-background-color: #1877f2;");
            likeButton.setText("UNLIKE");



        }

        // Toggle the liked state
        isClickedReply = !isClickedReply;
    }

    public void setPostId(int postId) {
        this.post_id=postId;
        System.out.println(postId);
    }
}
