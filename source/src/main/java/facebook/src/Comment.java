package facebook.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Comment class represents a comment made by a user on a post in the Facebook application.
 */
public class Comment {

    private int postId;
    private int commentId;
    private static String UserId;

    private String commentText;
    private LocalDateTime timestamp;

    /**
     * Sets the unique identifier for the comment.
     * @param commentId The identifier to be set for the comment.
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    /**
     * Sets the identifier of the post to which the comment belongs.
     * @param postId The identifier of the post.
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * Retrieves the identifier of the post to which the comment belongs.
     * @return The identifier of the post.
     */
    public int getPostId() {
        return postId;
    }

    /**
     * Constructs a new Comment object with the specified comment text and post identifier.
     * @param commentText The content of the comment.
     * @param postId The identifier of the post to which the comment is made.
     */
    public Comment(String commentText, int postId) {
        this.commentText = commentText;
        this.timestamp = LocalDateTime.now();
        this.postId = postId;
    }

    /**
     * Retrieves the formatted timestamp of the comment.
     * @return The formatted timestamp string.
     */
    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }

    /**
     * Retrieves the content of the comment.
     * @return The content of the comment.
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Retrieves the identifier of the comment.
     * @return The identifier of the comment.
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * Retrieves the static user identifier associated with the comment.
     * @return The static user identifier.
     */
    public static String getUserId() {
        return UserId;
    }

    /**
     * Sets the static user identifier associated with the comment.
     * @param userId The static user identifier to be set.
     */
    public static void setUserId(String userId) {
        UserId = userId;
    }
}
