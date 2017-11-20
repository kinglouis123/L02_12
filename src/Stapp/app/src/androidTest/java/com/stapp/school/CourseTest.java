package com.stapp.school;

import com.stapp.database.DatabaseDriverHelper;
import com.stapp.database.InitializeDatabase;
import com.stapp.terminals.AssignmentTerminal;
import com.stapp.terminals.CourseTerminal;
import com.stapp.terminals.LoginTerminal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by rahulkumar1998 on 2017-11-20.
 */
public class CourseTest {
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
  public void testConstructorInsert() {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    Course course = new Course(coursename, username);
    assertTrue(course.isValidCourse());
  }

  @Test
  public void testConstructorGet() {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    CourseTerminal.createNewCourse(coursename, username);
    Course course = new Course(coursename);
    assertTrue(course.isValidCourse());
  }

  @Test  public void testConstructorGetArchived() {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    Course course = CourseTerminal.createNewCourse(coursename, username);
    try {
      course.archiveCourse();
    } catch (ClassNotFoundException e) {
    }
    Course course1 = new Course(coursename);
    assertFalse(course1.isValidCourse());
  }

  @Test
  public void isValidCourse() throws Exception {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    Course course = CourseTerminal.createNewCourse(coursename, username);
    assertTrue(course.isValidCourse());
  }

  @Test
  public void isInvalidCourse() throws Exception {
    Course course = new Course(generateRandomString());
    assertFalse(course.isValidCourse());
  }

  @Test
  public void getProf() throws Exception {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    Course course = CourseTerminal.createNewCourse(coursename, username);
    assertEquals(username, course.getProf());
  }

  @Test
  public void addStudent() throws Exception {
    String student = generateRandomString();
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    LoginTerminal.newStudent(student, generateRandomString(), generateRandomString());
    Course course = CourseTerminal.createNewCourse(coursename, username);
    course.addStudent(student);
    assertTrue(course.getStudentUsernames().contains(student));
  }

  @Test
  public void getStudentUsernames() throws Exception {
    String student = generateRandomString();
    String student1 = generateRandomString();
    String student2 = generateRandomString();
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    LoginTerminal.newStudent(student, generateRandomString(), generateRandomString());
    LoginTerminal.newStudent(student1, generateRandomString(), generateRandomString());
    LoginTerminal.newStudent(student2, generateRandomString(), generateRandomString());
    Course course = CourseTerminal.createNewCourse(coursename, username);
    course.addStudent(student);
    course.addStudent(student1);
    course.addStudent(student2);
    ArrayList<String> students = course.getStudentUsernames();
    assertTrue(students.contains(student) && students.contains(student1)
        && students.contains(student2) && students.size() == 3);
  }

  @Test
  public void removeStudent() throws Exception {
    String student = generateRandomString();
    String student1 = generateRandomString();
    String student2 = generateRandomString();
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    LoginTerminal.newStudent(student, generateRandomString(), generateRandomString());
    LoginTerminal.newStudent(student1, generateRandomString(), generateRandomString());
    LoginTerminal.newStudent(student2, generateRandomString(), generateRandomString());
    Course course = CourseTerminal.createNewCourse(coursename, username);
    course.addStudent(student);
    course.addStudent(student1);
    course.addStudent(student2);
    course.removeStudent(student1);
    ArrayList<String> students = course.getStudentUsernames();
    assertTrue(students.contains(student) && !students.contains(student1)
        && students.contains(student2) && students.size() == 2);
  }

  @Test
  public void archiveCourse() throws Exception {
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    Course course = CourseTerminal.createNewCourse(coursename, username);
    try {
      course.archiveCourse();
    } catch (ClassNotFoundException e) {
    }
    Course course1 = new Course(coursename);
    assertFalse(course1.isValidCourse());
  }

  @Test
  public void getAssignments() throws Exception {
    String a1 = generateRandomString();
    String a2 = generateRandomString();
    String username = generateRandomString();
    String name = generateRandomString();
    String pass = generateRandomString();
    String coursename = generateRandomString();
    LoginTerminal.newProfessor(username, name, pass);
    Course course = CourseTerminal.createNewCourse(coursename, username);
    AssignmentTerminal.createNewAssignment(a1, "2017-03-04", coursename);
    AssignmentTerminal.createNewAssignment(a2, "2019-04-02", coursename);
    ArrayList<Assignment> assignments = (ArrayList) course.getAssignments();
    Assignment assignment1 = assignments.get(0);
    Assignment assignment2 = assignments.get(1);
    assertTrue(assignment1.getCourseCode().equals(coursename)
        && assignment2.getCourseCode().equals(coursename));
  }

}