package com.example.user.nottspark.View;


import android.text.TextUtils;

/**
 * Created by aiman on 21/03/2017.
 */

public class PasswordValidation {

    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 20;

    public static boolean ValidatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            System.out.println("empty string.");
            return false;
        }
        password = password.trim();
        int len = password.length();
        if (len < MIN_PASSWORD_LENGTH || len > MAX_PASSWORD_LENGTH) {
            System.out.println("wrong size, it must have at least 8 characters and less than 20.");
            return false;
        }
        char[] aC = password.toCharArray();
        for (char c : aC) {
            if (Character.isUpperCase(c)) {
                System.out.println(c + " is uppercase.");
            } else if (Character.isLowerCase(c)) {
                System.out.println(c + " is lowercase.");
            } else if (Character.isDigit(c)) {
                System.out.println(c + " is digit.");
            } else if (SPECIAL_CHARACTERS.indexOf(String.valueOf(c)) >= 0) {
                System.out.println(c + " is valid symbol.");
            } else {
                System.out.println(c + " is an invalid character in the password.");
                return false;
            }
        }
        return true;
    }
}