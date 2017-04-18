package com.example.user.nottspark;

import org.junit.Test;

import static com.example.user.nottspark.View.PasswordValidation.ValidatePassword;
import static junit.framework.Assert.assertEquals;

public class PasswordValidation {

    @Test
    public void addition_isCorrect() throws Exception {

        assertEquals(ValidatePassword(""), "empty string");
        assertEquals(ValidatePassword("bcdk"), "wrong size, it must have at least 8 characters and less than 20.");
        assertEquals(ValidatePassword("bcd%}^&$"), "Password contains invalid symbol.");

    }
}