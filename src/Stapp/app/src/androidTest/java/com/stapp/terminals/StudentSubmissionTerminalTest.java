package com.stapp.terminals;

import com.stapp.database.DatabaseDriverHelper;
import com.stapp.database.InitializeDatabase;
import com.stapp.exceptions.StudentAlreadyExistsException;
import com.stapp.school.Assignment;
import com.stapp.school.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/** Created by rahulkumar1998 on 2017-11-20. */
public class StudentSubmissionTerminalTest {

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
  public void testSubmissionStudentNotInCourse() {
    String prof = generateRandomString();
    String student = generateRandomString();
    String course = generateRandomString();
    String assignment = generateRandomString();
    LoginTerminal.newProfessor(prof, generateRandomString(), generateRandomString());
    LoginTerminal.newStudent(student, generateRandomString(), generateRandomString());
    CourseTerminal.createNewCourse(course, prof);
    Assignment a = AssignmentTerminal.createNewAssignment(assignment, "2020-01-20", course);
    assertNull(StudentSubmissionTerminal.startNewSubmission(student, a.getId()));
  }

  @Test
  public void testSubmissionStudentInCourse() {
    String prof = generateRandomString();
    String student = generateRandomString();
    String course = generateRandomString();
    String assignment = generateRandomString();
    LoginTerminal.newProfessor(prof, generateRandomString(), generateRandomString());
    LoginTerminal.newStudent(student, generateRandomString(), generateRandomString());
    Course c = CourseTerminal.createNewCourse(course, prof);
    try {
      c.addStudent(student);
    } catch (StudentAlreadyExistsException | ClassNotFoundException e) {
    }
    Assignment a = AssignmentTerminal.createNewAssignment(assignment, "2020-01-20", course);
    a.release();
    assertNotNull(StudentSubmissionTerminal.startNewSubmission(student, a.getId()));
  }
}
