package fxLogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Valid {

    private static final String USER_NAME_REGEX_PATERN = "^[a-zA-z0-9]{5,12}$";
    private static final String USER_PASSWORD_REGEX_PATTERN = "^[a-zA-z0-9@!?_#&%.^]{5,12}$";
    private static final String USER_EMAIL_REGEX_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,50}$";

    static boolean isValidUsername(String usernameInput) {
        Pattern pattern = Pattern.compile(USER_NAME_REGEX_PATERN);
        Matcher matcher = pattern.matcher(usernameInput);
        return matcher.find();
    }

    static boolean isValidPassword(String passwordInput) {
        Pattern pattern = Pattern.compile(USER_PASSWORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(passwordInput);
        return matcher.find();
    }

    static boolean isValidEmail(String emailInput) {
        Pattern pattern = Pattern.compile(USER_EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(emailInput);
        return matcher.find();
    }
}