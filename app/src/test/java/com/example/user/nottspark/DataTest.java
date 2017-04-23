package com.example.user.nottspark;

import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;

public class DataTest {

    @Test
    public void classes_data_isCorrect() throws Exception {
        User user = new User("mockuser96", "mockname", "0125885745", "mock@email.cd", "mockBrand", "mockmodel", "mockCarPlate", "Yellow Parking Permit", "12345678");
        User user2 = new User("mockuser96", "mockname", "0125885745", "mock@email.cd", "mockBrand", "mockmodel", "mockCarPlate", "Red Parking Permit", "12345678");

        assertNotSame(user, user2);
        user2.setUserAccountType("Yellow Parking Permit");
        assertEquals(user.getUserAccountType(), user2.getUserAccountType());

        Leaver leaver = new Leaver(user.getUserID(), "ZONE B1 - Between Trent and Blue Building", "ZONE B1 - Between Trent and Blue Building", "18/04/2017 03:30 pm");
        assertNotNull(leaver);

    }
}