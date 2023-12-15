package facebook.src;

import java.time.LocalDate;
import java.util.ArrayList;

public class User extends Person {


    public ArrayList<Integer> likedPosts;
    public String password;
    public ArrayList<Integer> friends;
    public ArrayList<Integer> restricted_users;

    public String profile_photo_path;

    public User(int id, String name, String password, String email, String gender, LocalDate date) {

        super(id,name,email,gender,date);
        this.setPassword(password);
    }

    public User(int id, String name,String email,String gender,LocalDate date, String password, ArrayList<Integer> friends, ArrayList<Integer> restricted_users) {
        super(id,name,email,gender,date);
        this.password = password;
        this.friends = friends;
        this.restricted_users=restricted_users;
    }
    public User(User user) {
        super(user.id,user.getName(),user.getEmail(),user.getGender(),user.getDate());
        this.password = user.password;
        this.friends = user.friends;
        this.profile_photo_path= user.profile_photo_path;;
        this.restricted_users=user.restricted_users;
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Integer> friends) {
        this.friends = friends;
    }

    public ArrayList<Integer> getRestricted_users() {
        return restricted_users;
    }

    public void setRestricted_users(ArrayList<Integer> restricted_users) {
        this.restricted_users = restricted_users;
    }

    public String getProfile_photo_path() {
        return profile_photo_path;
    }

    public void setProfile_photo_path(String profile_photo_path) {
        this.profile_photo_path = profile_photo_path;
    }



}
