package facebook.src;

/**
 * The Reply class represents a reply to a comment in the Facebook system.
 * It extends the Comment class and includes additional properties specific to replies.
 */
public class Reply extends Comment {

    private int replyId;

    /**
     * Constructs a Reply object with the specified comment text and post ID.
     *
     * @param commentText The text of the reply.
     * @param post_id     The ID of the post to which the reply belongs.
     */
    public Reply(String commentText, int post_id) {
        super(commentText, post_id);
    }

    /**
     * Gets the ID of the reply.
     *
     * @return The ID of the reply.
     */
    public int getReplyId() {
        return replyId;
    }
}
