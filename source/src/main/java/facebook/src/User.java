package facebook.src;

import java.time.LocalDate;
import java.util.ArrayList;

public class User extends Person {

    public int id;

    public String Email;

    public User(int id, String name, String password, String email, String gender, LocalDate date) {

        super(name, gender, date);
        this.id=id;
        this.setPassword(password);
        this.setEmail(email);
    }

    public <E> User(int id, String name,String email,String gender,LocalDate Date, String password, ArrayList<Integer> friends, ArrayList<Integer> restricted_users) {
        super(name,gender,Date);
        this.setPassword(password);
        this.id=id;
        this.friends=friends;
        this.restricted_users=restricted_users;

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

    public String password;
    public ArrayList<Integer> friends;
    public ArrayList<Integer> restricted_users;

    public String profile_photo_path;

    public User(int id, String name, String password, ArrayList<Integer> friends, ArrayList<Integer> restricted_users, String gender, LocalDate Date,String profile_photo_path) {
        super(name,gender,Date);
        this.id = id;
        this.password = password;
        this.friends = friends;
        this.restricted_users=restricted_users;
        this.profile_photo_path=profile_photo_path;
    }
    public User(User user) {
        super(user.getName(),user.getGender(),user.getDate());
        this.id = user.id;
        this.password = user.password;
        this.friends = user.friends;
        this.profile_photo_path= user.profile_photo_path;;
        this.restricted_users=user.restricted_users;

    }

}
