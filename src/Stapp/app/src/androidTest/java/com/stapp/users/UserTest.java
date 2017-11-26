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

/** Created by rahulkumar1998 on 2017-11-20. */
public class UserTest {

  private String name = generateRandomString();
  private String username = generateRandomString();
  private String password = generateRandomString();
  private int roleid = 1;
  private User bobby;

  @Before
  public void setUp() throws Exception {
    InitializeDatabase.initializeDatabase();
    this.bobby = new dummyUser(this.username, this.name, this.password, this.roleid);
  }

  @After
  public void tearDown() throws Exception {
    DatabaseDriverHelper.reinitializeDatabase();
  }

  /**
   * Random string generator. obtained from:
   * https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
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

    assertTrue(bobby.isLoggedIn());
  }

  @Test
  public void isNotLoggedIn() throws Exception {

    assertFalse(bobby.isLoggedIn());
  }

  @Test
  public void getUsername() throws Exception {

    assertEquals(this.name, bobby.getUsername());
  }

  @Test
  public void getName() throws Exception {
    assertEquals(this.name, bobby.getName());
  }

  @Test
  public void getRoleName() throws Exception {
    assertEquals("STUDENT", bobby.getRoleName());
  }

  @Test
  public void getPassword() throws Exception {

    assertEquals(PasswordHelpers.passwordHash(this.password), bobby.getPassword());
  }

  @Test
  public void updatePassword() throws Exception {

    String newPass = generateRandomString();
    bobby.updatePassword(newPass);
    assertEquals(PasswordHelpers.passwordHash(newPass), bobby.getPassword());
  }

  @Test
  public void updateName() throws Exception {
    String newName = generateRandomString();
    bobby.updateName(newName);
    assertEquals(newName, bobby.getName());
  }
}
