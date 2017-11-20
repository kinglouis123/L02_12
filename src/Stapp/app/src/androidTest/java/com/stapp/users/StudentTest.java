package com.stapp.users;

import com.stapp.database.DatabaseDriverHelper;
import com.stapp.database.InitializeDatabase;
import com.stapp.school.Assignment;
import com.stapp.school.Course;
import com.stapp.terminals.AssignmentTerminal;
import com.stapp.terminals.CourseTerminal;
import com.stapp.terminals.LoginTerminal;
import com.stapp.terminals.StudentSubmissionTerminal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by rahulkumar1998 on 2017-11-20.
 */
public class StudentTest {

  private String student;
  private String prof;
  private String course;
  private String assignment;

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

  private void generateStuff() {
    student = generateRandomString();
    prof = generateRandomString();
    course = generateRandomString();
    assignment = generateRandomString();
  }

  @Test
  public void getCourses() throws Exception {
    generateStuff();
    LoginTerminal.newProfessor(prof, generateRandomString(), generateRandomString());
    Student s = LoginTerminal.newStudent(student, generateRandomString(), generateRandomString());
    Course c = CourseTerminal.createNewCourse(course, prof);
    c.addStudent(student);
    assertEquals(course, s.getCourses().get(0).toString());
  }

  @Test
  public void getGrade() throws Exception {
    generateStuff();
    LoginTerminal.newProfessor(prof, generateRandomString(), generateRandomString());
    Student s = LoginTerminal.newStudent(student, generateRandomString(), generateRandomString());
    Course c = CourseTerminal.createNewCourse(course, prof);
    c.addStudent(student);
    Assignment a = AssignmentTerminal.createNewAssignment(assignment, "2020-10-02", course);
    StudentSubmissionTerminal.startNewSubmission(student, a.getId());
    assertEquals("(Not Attempted)", s.getGrade(a.getId()));
  }

}