package com.example.user.nottspark.View;


import android.text.TextUtils;

/**
 * Created by aiman on 21/03/2017.
 */

public class PasswordValidation {

    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 20;

    public static String ValidatePassword(String password) {
        String check = "";
        if (TextUtils.isEmpty(password)) {
            return "empty string";
        }
        password = password.trim();
        int len = password.length();
        if (len < MIN_PASSWORD_LENGTH || len > MAX_PASSWORD_LENGTH) {
            return "wrong size, it must have at least 8 characters and less than 20.";
        }
        char[] aC = password.toCharArray();
        for (char c : aC) {
            if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                return "Password contains invalid symbol.";
            }
        }
        return check;
    }
}