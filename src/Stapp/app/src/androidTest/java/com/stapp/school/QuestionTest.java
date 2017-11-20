package com.stapp.school;

import com.stapp.database.DatabaseDriverHelper;
import com.stapp.database.InitializeDatabase;
import com.stapp.terminals.AssignmentTerminal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by rahulkumar1998 on 2017-11-20.
 */
public class QuestionTest {

  private String profUsername;
  private String courseName;
  private String assignmentName;
  private String question;
  private ArrayList<String> choices;

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
    profUsername = generateRandomString();
    courseName = generateRandomString();
    assignmentName = generateRandomString();
    assignment = AssignmentTerminal.createNewAssignment(assignmentName,
        "3000-01-01", courseName);
    question = generateRandomString();
    choices = new ArrayList<>();
    choices.add(generateRandomString());
    choices.add(generateRandomString());
    choices.add(generateRandomString());
    choices.add(generateRandomString());
  }

  @Test
  public void testCreateQuestion() {
    generateNewAssignment();
    Question q = assignment.insertMultipleChoiceQuestion(question, choices, 2);
    Question q1 = new Question(q.getId());
    assertEquals(q.getQuestionString(), q1.getQuestionString());
  }

  @Test
  public void getQuestionString() throws Exception {
    generateNewAssignment();
    Question q = assignment.insertMultipleChoiceQuestion(question, choices, 2);
    Question q1 = new Question(q.getId());
    assertEquals(q.getQuestionString(), q1.getQuestionString());
  }

  @Test
  public void getChoices() throws Exception {
    generateNewAssignment();
    Question q = assignment.insertMultipleChoiceQuestion(question, choices, 2);
    ArrayList<String> c = q.getChoices();
    assertTrue(c.get(0).equals(choices.get(0)) && c.get(1).equals(choices.get(1))
        && c.get(2).equals(choices.get(2)) && c.get(3).equals(choices.get(3)));
  }

  @Test
  public void getAnswer() throws Exception {
    generateNewAssignment();
    Question q = assignment.insertMultipleChoiceQuestion(question, choices, 1);
    assertEquals(choices.get(1), q.getAnswer());
  }

}