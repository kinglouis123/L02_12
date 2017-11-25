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
public class StudentSubmissionTest {

  private String student;

  private String courseName;
  private String assignmentName;
  private String question;
  private String question2;
  private ArrayList<String> choices;
  private ArrayList<String> choices2;

  private Assignment assignment;

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

  private void generateNewAssignment() {
    student = generateRandomString();
    courseName = generateRandomString();
    assignmentName = generateRandomString();
    assignment = AssignmentTerminal.createNewAssignment(assignmentName,
        "3000-01-01", courseName);
    question = generateRandomString();
    question2 = generateRandomString();
    choices = new ArrayList<>();
    choices.add(generateRandomString());
    choices.add(generateRandomString());
    choices.add(generateRandomString());
    choices.add(generateRandomString());
    choices2 = new ArrayList<>();
    choices2.add(generateRandomString());
    choices2.add(generateRandomString());
    choices2.add(generateRandomString());
    choices2.add(generateRandomString());
    assignment.insertMultipleChoiceQuestion(question, choices, 1);
    assignment.insertMultipleChoiceQuestion(question2, choices2, 2);
  }

  @Test
  public void testConstructor() {
    generateNewAssignment();
    StudentSubmission studentSubmission = new StudentSubmission(student, assignment.getId());
    assertEquals("(Not Attempted)", studentSubmission.getCurrentMark());
  }

  @Test
  public void getNextQuestion() throws Exception {
    generateNewAssignment();
    StudentSubmission studentSubmission = new StudentSubmission(student, assignment.getId());
    Question q1 = studentSubmission.getNextQuestion();
    studentSubmission.answerCurrentQuestion("placeholder");
    Question q2 = studentSubmission.getNextQuestion();
    studentSubmission.answerCurrentQuestion("placeholder");
    assertTrue(q1.getQuestionString().equals(question)
        && q2.getQuestionString().equals(question2)
        && studentSubmission.getNextQuestion() == null);
  }

  @Test
  public void answerCurrentQuestionRight() throws Exception {
    generateNewAssignment();
    StudentSubmission studentSubmission = new StudentSubmission(student, assignment.getId());
    studentSubmission.getNextQuestion();
    studentSubmission.answerCurrentQuestion(choices.get(1));
    assertEquals("1/2", studentSubmission.getCurrentMark());
  }

  @Test
  public void answerCurrentQuestionWrong() throws Exception {
    generateNewAssignment();
    StudentSubmission studentSubmission = new StudentSubmission(student, assignment.getId());
    studentSubmission.getNextQuestion();
    studentSubmission.answerCurrentQuestion(choices.get(2));
    assertEquals("0/2", studentSubmission.getCurrentMark());
  }


  @Test
  public void getCurrentMark() throws Exception {
    generateNewAssignment();
    StudentSubmission studentSubmission = new StudentSubmission(student, assignment.getId());
    assertEquals("(Not Attempted)", studentSubmission.getCurrentMark());
    studentSubmission.getNextQuestion();
    studentSubmission.answerCurrentQuestion(choices.get(1));
    assertEquals("1/2", studentSubmission.getCurrentMark());
    studentSubmission.getNextQuestion();
    studentSubmission.answerCurrentQuestion(choices2.get(2));
    assertEquals("2/2", studentSubmission.getCurrentMark());
  }

  @Test
  public void submitAssignmentOnTime() throws Exception {
    generateNewAssignment();
    StudentSubmission studentSubmission = new StudentSubmission(student, assignment.getId());
    assertTrue(studentSubmission.submitAssignment());
  }
  @Test
  public void submitAssignmentLate() throws Exception {
    generateNewAssignment();
    assignment = AssignmentTerminal.createNewAssignment(generateRandomString(),
        "2000-01-01", courseName);
    assertNull(assignment);
  }

}