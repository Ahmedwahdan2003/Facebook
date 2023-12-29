package facebook.src;

import java.util.ArrayList;

/**
 * The root data class serves as the base class for managing users, posts, interactions, comments, and the current user in the Facebook system.
 * It provides common functionality and data structures for the core entities in the application.
 */
public abstract class RootData {

    protected static ArrayList<User> users = new ArrayList<>();
    protected static ArrayList<Post> Posts = new ArrayList<>();
    protected static ArrayList<interactions> interactionList = new ArrayList<>();
    protected static ArrayList<Comment> Comments = new ArrayList<>();
    protected static User currentUser;

    /**
     * Removes an interaction based on the specified user ID and post ID.
     *
     * @param user_id The ID of the user involved in the interaction.
     * @param post_id The ID of the post involved in the interaction.
     */
    protected static void Remove_interaction(int user_id, int post_id) {
        interactionList.removeIf(i -> i.getPost_id() == post_id && i.getUser_id() == user_id);
    }

    /**
     * Retrieves a user based on the specified user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The User object if found, otherwise null.
     */
    protected static User getUserById(int userId) {
        for (User user : users) {
            if (user.id == userId) {
                return user;
            }
        }
        return null; // User not found
    }

    /**
     * Retrieves a post based on the specified post ID.
     *
     * @param postId The ID of the post to retrieve.
     * @return The Post object if found, otherwise null.
     */
    protected static Post getPostById(int postId) {
        for (Post post : Posts) {
            if (post.post_id == postId) {
                return post;
            }
        }
        return null; // Post not found
    }
}
