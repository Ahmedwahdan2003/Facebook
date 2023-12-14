package facebook.src;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class User {
    public final int id;
    public String name;
    public String password;
    public ArrayList<Integer> friends;
    public ArrayList<Integer> restricted_users;
    public String profile_photo_path;

    public User(int id, String name, String password, ArrayList<Integer> friends, ArrayList<Integer> restricted_users) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.friends = friends;
        this.restricted_users=restricted_users;
    }
    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.password = user.password;
        this.friends = user.friends;
    }

}
