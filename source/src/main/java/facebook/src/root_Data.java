package facebook.src;

import java.util.ArrayList;

public abstract class root_Data {
    protected static ArrayList<User> users = new ArrayList<>();
    protected static ArrayList<post> posts = new ArrayList<>();
    protected static ArrayList<interactions> interactionList = new ArrayList<>();
    protected static User currentUser;


    protected static User getUserById(int userId) {
        for (User user : users) {
            if (user.id == userId) {
                return user;
            }
        }
        return null; // User not found
    }

    protected static post getPostById(int postId) {
        for (post post : posts) {
            if (post.post_id == postId) {
                return post;
            }
        }
        return null; // Post not found
    }
}
