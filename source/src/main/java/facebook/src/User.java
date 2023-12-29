package facebook.src;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The User class represents a user in the Facebook application.
 * It extends the Person class and includes additional user-specific data.
 */
public class User extends Person {

    /**
     * List of post IDs that the user has liked.
     */
    public ArrayList<Integer> likedPosts;

    /**
     * User's password for authentication.
     */
    public String password;

    /**
     * List of user IDs representing friends of the user.
     */
    public ArrayList<Integer> friends;

    /**
     * List of user IDs representing users restricted by the current user.
     */
    public ArrayList<Integer> restricted_users;

    /**
     * Path to the user's profile photo.
     */
    public String profile_photo_path = "";

    /**
     * Constructs a User object with the specified parameters.
     *
     * @param id       The unique identifier for the user.
     * @param name     The name of the user.
     * @param password The user's password.
     * @param email    The email address of the user.
     * @param gender   The gender of the user.
     * @param date     The date of birth of the user.
     */
    public User(int id, String name, String password, String email, String gender, LocalDate date) {
        super(id, name, email, gender, date);
        this.setPassword(password);
    }

    /**
     * Constructs a User object with more parameters, including friends and restricted users.
     *
     * @param id               The unique identifier for the user.
     * @param name             The name of the user.
     * @param email            The email address of the user.
     * @param gender           The gender of the user.
     * @param date             The date of birth of the user.
     * @param password         The user's password.
     * @param friends          List of user IDs representing friends of the user.
     * @param restricted_users List of user IDs representing users restricted by the current user.
     */
    public User(int id, String name, String email, String gender, LocalDate date, String password,
                ArrayList<Integer> friends, ArrayList<Integer> restricted_users) {
        super(id, name, email, gender, date);
        this.password = password;
        this.friends = friends;
        this.restricted_users = restricted_users;
    }

    /**
     * Constructs a User object based on another User object (copy constructor).
     *
     * @param user The user object to copy.
     */
    public User(User user) {
        super(user.id, user.getName(), user.getEmail(), user.getGender(), user.getDate());
        this.password = user.password;
        this.friends = user.friends;
        this.profile_photo_path = user.profile_photo_path;
        this.restricted_users = user.restricted_users;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return The user's unique identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the list of user IDs representing friends of the user.
     *
     * @return List of user IDs representing friends.
     */
    public ArrayList<Integer> getFriends() {
        return friends;
    }

    /**
     * Sets the list of user IDs representing friends of the user.
     *
     * @param friends The new list of friends.
     */
    public void setFriends(ArrayList<Integer> friends) {
        this.friends = friends;
    }

    /**
     * Gets the list of user IDs representing users restricted by the current user.
     *
     * @return List of user IDs representing restricted users.
     */
    public ArrayList<Integer> getRestricted_users() {
        return restricted_users;
    }

    /**
     * Sets the list of user IDs representing users restricted by the current user.
     *
     * @param restricted_users The new list of restricted users.
     */
    public void setRestricted_users(ArrayList<Integer> restricted_users) {
        this.restricted_users = restricted_users;
    }

    /**
     * Gets the path to the user's profile photo.
     *
     * @return The path to the user's profile photo.
     */
    public String getProfile_photo_path() {
        return profile_photo_path;
    }

    /**
     * Sets the path to the user's profile photo.
     *
     * @param profile_photo_path The new path to the user's profile photo.
     */
    public void setProfile_photo_path(String profile_photo_path) {
        this.profile_photo_path = profile_photo_path;
    }
}
