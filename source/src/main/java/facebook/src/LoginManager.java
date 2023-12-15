package facebook.src;

import java.util.List;

public class LoginManager {
    private List<User> accounts;

    public LoginManager(List<User> accounts) {
        this.accounts = accounts;
    }

    public boolean authenticateUser(String enteredEmail, String enteredPassword) {
        if (!isValidEmail(enteredEmail)) {
            return false;
        }

        for (User account : accounts) {
            if (isMatchingAccount(account, enteredEmail, enteredPassword)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    private boolean isMatchingAccount(User account, String enteredEmail, String enteredPassword) {

        return account.getEmail().equals(enteredEmail) && checkPassword(enteredPassword, account.getPassword());
    }

    private boolean checkPassword(String enteredPassword, String storedPassword) {
        return enteredPassword.equals(storedPassword);}
}
