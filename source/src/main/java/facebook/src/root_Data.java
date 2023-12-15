package facebook.src;

import java.util.ArrayList;

public abstract class root_Data {
    protected static ArrayList<User> users = new ArrayList<>();
    protected static ArrayList<Post> Posts = new ArrayList<>();
    protected static ArrayList<interactions> interactionList = new ArrayList<>();

    protected static ArrayList<Comment>Comments = new ArrayList<>();
    protected static User currentUser;

    protected static void Remove_interaction(int user_id,int post_id){
        interactionList.removeIf(i -> i.getPost_id() == post_id && i.getUser_id() == user_id);
    }

    protected static User getUserById(int userId) {
        for (User user : users) {
            if (user.id == userId) {
                return user;
            }
        }
        return null; // User not found
    }

    protected static Post getPostById(int postId) {
        for (Post post : Posts) {
            if (post.post_id == postId) {
                return post;
            }
        }
        return null; // Post not found
    }
}
