package com.stapp.database;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.stapp.security.PasswordHelpers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by jr on 18/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseDriverTest {
    private DatabaseDriver db;
    @Before
    public void setUp() throws Exception {
        this.db = new DatabaseDriver(InstrumentationRegistry.getTargetContext());
        InitializeDatabase.initializeDatabase();
    }

    @After
    public void tearDown() throws Exception {
        InitializeDatabase.initializeDatabase();
        DatabaseDriverHelper.reinitializeDatabase();
        InitializeDatabase.initializeDatabase();
        this.db.close();
    }

    /**
     * Random string generator.
     * obtained from: https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
     */
    private String generateRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    // USER TESTS
    // INSERT

    // UPDATE

    @Test
    public void testUpdatePassword() {
        String username = generateRandomString();
        String name = generateRandomString();
        String password = generateRandomString();
        String newPassword = generateRandomString();

        db.insertUser(username, name, password, 1);
        db.updatePassword(newPassword, username);
        assertEquals(PasswordHelpers.passwordHash(newPassword), db.getPassword(username));
    }

    @Test
    public void testUpdateName() {
        String username = generateRandomString();
        String name = generateRandomString();
        String password = generateRandomString();
        String newName = generateRandomString();

        db.insertUser(username, name, password, 1);
        db.updateName(newName, username);
        assertEquals(newName, db.getName(username));
    }

    @Test
    public void testUpdateRole() {
        String username = generateRandomString();
        String name = generateRandomString();
        String password = generateRandomString();
        String role = generateRandomString();
        String newRole = generateRandomString();

        db.insertRole(role);
        db.insertRole(newRole);

        int roleId = db.getRoleIdGivenRoleName(role);
        int newRoleId = db.getRoleIdGivenRoleName(newRole);

        db.insertUser(username, name, password, roleId);
        db.updateRole(newRoleId, username);

        assertEquals(db.getRoleIdGivenUsername(username), newRoleId);
    }

    // SELECT



    // COURSE TESTS
    // INSERT
    // UPDATE
    // SELECT

    // ASSIGNMENT TESTS
    // INSERT
    // UPDATE
    // SELECT

    // QUESTION TESTS
    // INSERT
    // SELECT

    // ASSIGNMENT SUBMISSION TESTS
    // INSERT
    // UPDATE
    // SELECT

    /*
    @Test
    public void testInsertUser() throws Exception {
        db.insertUser("user", "name", "password", 1);
        assertEquals("name", db.getName("user"));
    }
    */

}