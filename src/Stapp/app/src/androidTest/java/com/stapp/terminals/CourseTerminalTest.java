package com.stapp.terminals;

import com.stapp.database.DatabaseDriverHelper;
import com.stapp.database.InitializeDatabase;
import com.stapp.school.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/** Created by rahulkumar1998 on 2017-11-20. */
public class CourseTerminalTest {

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
  public void createNewInvalidCourse() throws Exception {
    assertNull(CourseTerminal.createNewCourse(generateRandomString(), generateRandomString()));
  }

  @Test
  public void createNewValidCourse() {
    String prof = generateRandomString();
    LoginTerminal.newProfessor(prof, generateRandomString(), generateRandomString());
    assertNotNull(CourseTerminal.createNewCourse(generateRandomString(), prof));
  }

  @Test
  public void getExistingCourse() throws Exception {
    String prof = generateRandomString();
    LoginTerminal.newProfessor(prof, generateRandomString(), generateRandomString());
    String course = generateRandomString();
    CourseTerminal.createNewCourse(course, prof);
    assertNotNull(CourseTerminal.getCourse(course));
  }

  @Test
  public void getNonExistentCourse() {
    assertNull(CourseTerminal.getCourse(generateRandomString()));
  }
}
