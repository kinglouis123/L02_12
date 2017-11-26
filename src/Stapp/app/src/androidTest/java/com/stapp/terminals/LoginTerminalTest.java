package com.stapp.terminals;

import com.stapp.database.DatabaseDriverHelper;
import com.stapp.database.InitializeDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/** Created by rahulkumar1998 on 2017-11-20. */
public class LoginTerminalTest {

  @Before
  public void setUp() throws Exception {
    InitializeDatabase.initializeDatabase();
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
  public void getExistingProfessor() throws Exception {
    String prof = generateRandomString();
    String pass = generateRandomString();
    LoginTerminal.newProfessor(prof, generateRandomString(), pass);
    assertNotNull(LoginTerminal.getProfessor(prof, pass));
  }

  @Test
  public void getNonExistentProfessor() throws Exception {
    assertNull(LoginTerminal.getProfessor(generateRandomString(), generateRandomString()));
  }

  @Test
  public void getExistingStudent() throws Exception {
    String student = generateRandomString();
    String pass = generateRandomString();
    LoginTerminal.newStudent(student, generateRandomString(), pass);
    assertNotNull(LoginTerminal.getStudent(student, pass));
  }

  @Test
  public void getNonExistentStudent() throws Exception {
    assertNull(LoginTerminal.getStudent(generateRandomString(), generateRandomString()));
  }

  @Test
  public void createExistingProfessor() throws Exception {
    String prof = generateRandomString();
    String pass = generateRandomString();
    LoginTerminal.newProfessor(prof, generateRandomString(), pass);
    assertNull(LoginTerminal.newProfessor(prof, generateRandomString(), pass));
  }

  @Test
  public void createNonExistentProfessor() throws Exception {
    String prof = generateRandomString();
    String pass = generateRandomString();
    assertNotNull(LoginTerminal.newProfessor(prof, generateRandomString(), pass));
  }

  @Test
  public void createExistingStudent() throws Exception {
    String student = generateRandomString();
    String pass = generateRandomString();
    LoginTerminal.newStudent(student, generateRandomString(), pass);
    assertNull(LoginTerminal.newStudent(student, generateRandomString(), pass));
  }

  @Test
  public void createNonExistentStudent() throws Exception {
    String student = generateRandomString();
    String pass = generateRandomString();
    assertNotNull(LoginTerminal.newStudent(student, generateRandomString(), pass));
  }
}
