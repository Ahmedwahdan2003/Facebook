package facebook.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private int postId;
    private int commentId;
    private static String UserId;

    private String commentText;
    private LocalDateTime timestamp;

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public Comment(String commentText,int postId) {
        this.commentText = commentText;
        this.timestamp = LocalDateTime.now();
        this.postId=postId;

    }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }

    public String getCommentText() {
        return commentText;
    }

    public int getCommentId() {
        return commentId;
    }







    public static String getUserId() {
        return UserId;
    }

    public static void setUserId(String userId) {
        UserId = userId;}


}
