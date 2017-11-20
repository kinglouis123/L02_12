package com.stapp.users;

import com.stapp.database.DatabaseDriverHelper;
import com.stapp.database.InitializeDatabase;
import com.stapp.security.PasswordHelpers;
import com.stapp.terminals.LoginTerminal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by rahulkumar1998 on 2017-11-20.
 */
public class UserTest {

  @Before
  public void setUp() throws Exception {
    InitializeDatabase.initializeDatabase();
  }

  @After
  public void tearDown() throws Exception {
    DatabaseDriverHelper.reinitializeDatabase();
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

  @Test
  public void isLoggedIn() throws Exception {
    String name = generateRandomString();
    String pass = generateRandomString();
    User user = new User(name, generateRandomString(), pass, 1);
    assertTrue(user.isLoggedIn());
  }

  @Test
  public void isNotLoggedIn() throws Exception {
    String name = generateRandomString();
    String pass = generateRandomString();
    new User(name, generateRandomString(), pass, 1);
    User user = new User(name, generateRandomString());
    assertFalse(user.isLoggedIn());
  }

  @Test
  public void getUsername() throws Exception {
    String name = generateRandomString();
    String pass = generateRandomString();
    User user = new User(name, generateRandomString(), pass, 1);
    assertEquals(name, user.getUsername());
  }

  @Test
  public void getName() throws Exception {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    User user = new User(username, name, pass, 1);
    assertEquals(name, user.getName());
  }

  @Test
  public void getRoleName() throws Exception {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    User user = new User(username, name, pass, 1);
    assertEquals("STUDENT", user.getRoleName());
  }

  @Test
  public void getPassword() throws Exception {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    User user = new User(username, name, pass, 1);
    assertEquals(PasswordHelpers.passwordHash(pass), user.getPassword());
  }

  @Test
  public void updatePassword() throws Exception {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String newPass = generateRandomString();
    User user = new User(username, name, pass, 1);
    user.updatePassword(newPass);
    assertEquals(PasswordHelpers.passwordHash(newPass), user.getPassword());
  }

  @Test
  public void updateName() throws Exception {
    String username = generateRandomString();
    String name = generateRandomString();
    String newName = generateRandomString();
    String pass = generateRandomString();
    User user = new User(username, name, pass, 1);
    user.updateName(newName);
    assertEquals(newName, user.getName());
  }

}