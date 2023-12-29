package facebook.src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * The CommentController class is the controller for handling user interactions related to comments and replies.
 */
public class CommentController {

    private Post currentpost;

    /**
     * Retrieves the current post associated with the controller.
     *
     * @return The current post.
     */
    public Post getCurrentpost() {
        return currentpost;
    }

    /**
     * Sets the current post for the controller.
     *
     * @param currentpost The post to be set as the current post.
     */
    public void setCurrentpost(Post currentpost) {
        this.currentpost = currentpost;
    }

    @FXML
    private TextField commentTextField;

    @FXML
    public VBox commentsContainer;

    @FXML
    private Label errorMessageLabel;

    /**
     * Creates a VBox to represent a comment and its associated information.
     *
     * @param comment The comment to be displayed.
     * @return The VBox representing the comment.
     */
    public VBox createCommentBox(Comment comment) {
        TextField commentTextField = new TextField(comment.getCommentText());
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

    /**
     * Creates an HBox to represent a reply and its associated information.
     *
     * @param reply The reply to be displayed.
     * @return The HBox representing the reply.
     */
    private HBox createReplyBox(Reply reply) {
        TextField commentTextField = new TextField(reply.getCommentText());
        commentTextField.setEditable(false);

        Label timestampLabel = new Label(reply.getFormattedTimestamp());
        Button likeButton = new Button("Like");
        likeButton.setOnAction(event -> handleLikeReplyButton(likeButton));

        HBox replyBox = new HBox(commentTextField, timestampLabel, likeButton);
        replyBox.setSpacing(10);

        return replyBox;
    }

    /**
     * Submits a new comment and updates the UI.
     */
    public void submitComment() {
        String newCommentText = commentTextField.getText().trim();

        if (!newCommentText.isEmpty()) {
            Comment newComment = new Comment(newCommentText, currentpost.post_id);
            DATA.Comments.add(newComment);

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

    /**
     * Handles the like button click for comments.
     *
     * @param commentlikeButton The like button for the comment.
     */
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

    /**
     * Handles the reply button click for comments.
     *
     * @param replybutton The reply button for the comment.
     */

    private void handleReplyButton(Button replybutton) {
        String replyText = commentTextField.getText();
        if (!replyText.isEmpty()) {
            Reply newreply = new Reply(replyText, currentpost.post_id);
            // Create an HBox for the reply
            HBox replyHBox = createReplyBox(newreply);

            // Find the parent comment's HBox
            Node parentCommentVBox = replybutton.getParent();
            if (parentCommentVBox instanceof VBox) {
                ((VBox) parentCommentVBox).getChildren().add(replyHBox);
            } else {
                errorMessageLabel.setText("Please enter a non-empty reply.");
            }
        }

        // Clear the reply TextField
        commentTextField.clear();
    }

    boolean isClickedReply = false;

    /**
     * Handles the like button click for replies.
     *
     * @param likeButton The like button for the reply.
     */
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

    /**
     * Navigates back to the feed view.
     *
     * @param mouseEvent The mouse event triggering the back button click.
     * @throws IOException Thrown if there is an issue with input/output operations.
     */
    public void commentBack_btn(MouseEvent mouseEvent) throws IOException {
        Scene_Changer scene_changer = new Scene_Changer();
        scene_changer.loadAndSetScene(mouseEvent, "feed.fxml");
    }
}
