package facebook.src;

import java.util.ArrayList;

/**
 * The LoginManager class is responsible for managing user authentication during login.
 */
public class LoginManager {
    private ArrayList<User> accounts;

    /**
     * Constructs a new LoginManager with the provided list of user accounts.
     *
     * @param accounts The list of user accounts.
     */
    public LoginManager(ArrayList<User> accounts) {
        this.accounts = accounts;
    }

    /**
     * Authenticates a user based on the entered email and password.
     *
     * @param enteredEmail    The email entered by the user.
     * @param enteredPassword The password entered by the user.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean authenticateUser(String enteredEmail, String enteredPassword) {
        if (!isValidEmail(enteredEmail)) {
            return false;
        }

        for (User account : accounts) {
            if (isMatchingAccount(account, enteredEmail, enteredPassword)) {
                DATA.currentUser = account;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the provided email is valid.
     *
     * @param email The email to be validated.
     * @return True if the email is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    /**
     * Checks if the provided email and password match a user account.
     *
     * @param account         The user account to check against.
     * @param enteredEmail    The email entered by the user.
     * @param enteredPassword The password entered by the user.
     * @return True if the email and password match the account, false otherwise.
     */
    private boolean isMatchingAccount(User account, String enteredEmail, String enteredPassword) {
        return account.getEmail().equalsIgnoreCase(enteredEmail) && checkPassword(enteredPassword, account.getPassword());
    }

    /**
     * Checks if the entered password matches the stored password.
     *
     * @param enteredPassword The password entered by the user.
     * @param storedPassword  The password stored in the user account.
     * @return True if the passwords match, false otherwise.
     */
    private boolean checkPassword(String enteredPassword, String storedPassword) {
        return enteredPassword.equals(storedPassword);
    }
}
