package facebook.src;

/**
 * The interactions class represents user interactions with posts.
 */
public class interactions {
    private int user_id;
    private int post_id;

    /**
     * Constructs an interactions object with the specified user ID and post ID.
     *
     * @param user_id The ID of the user involved in the interaction.
     * @param post_id The ID of the post involved in the interaction.
     */
    interactions(int user_id, int post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }

    /**
     * Gets the ID of the post involved in the interaction.
     *
     * @return The post ID.
     */
    public int getPost_id() {
        return post_id;
    }

    /**
     * Gets the ID of the user involved in the interaction.
     *
     * @return The user ID.
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Sets the user ID involved in the interaction.
     *
     * @param user_id The user ID to set.
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Sets the post ID involved in the interaction.
     *
     * @param post_id The post ID to set.
     */
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
