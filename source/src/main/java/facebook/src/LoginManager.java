package facebook.src;

import java.util.ArrayList;
import java.util.List;

public class LoginManager {
    private ArrayList<User> accounts;

    public LoginManager(ArrayList<User> accounts) {
        this.accounts = accounts;
    }

    public boolean authenticateUser(String enteredEmail, String enteredPassword) {
        if (!isValidEmail(enteredEmail)) {
            return false;
        }

        for (User account : accounts) {
            if (isMatchingAccount(account, enteredEmail, enteredPassword)) {
                DATA.currentUser=account;
                return true;
            }
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    private boolean isMatchingAccount(User account, String enteredEmail, String enteredPassword) {

        return account.getEmail().equalsIgnoreCase(enteredEmail) && checkPassword(enteredPassword, account.getPassword());
    }

    private boolean checkPassword(String enteredPassword, String storedPassword) {
        return enteredPassword.equals(storedPassword);}
}
