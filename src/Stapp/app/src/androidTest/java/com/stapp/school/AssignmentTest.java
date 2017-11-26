package com.stapp.school;

import com.stapp.database.DatabaseDriverHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;
/** Created by wenboma on 2017-11-18. */
public class AssignmentTest {

  private int id = 1;
  private String assignmentname;
  private String due = "2018-11-30";
  private String coursename = "cscc01h3";
  private Assignment assignfal;
  private Assignment assignlong;

  @Before
  public void setUp() throws Exception {
    this.assignmentname = "a01";
    assignlong = new Assignment(this.assignmentname, this.due, this.coursename);
    assignfal = new Assignment("a01", "2020-09-01", "ENGA01H3");
    List<String> choices = new ArrayList<String>();
    choices.add("apple");
    choices.add("peach");
    choices.add("Grape");
    choices.add("Orange");
    Question pick = assignlong.insertMultipleChoiceQuestion("how?", choices, 1);
  }

  @After
  public void tearDown() throws Exception {
    DatabaseDriverHelper.reinitializeDatabase();
  }

  @Test
  public void isValidAssignment() throws Exception {
    assertTrue(assignlong.isValidAssignment());
    assertTrue(assignfal.isValidAssignment());
  }
  /**
   * @Test public void insertMultipleChoiceQuestion() throws Exception { List<String> choices = new
   * ArrayList<String>(); choices.add("red"); choices.add("blue"); choices.add("yellow");
   * choices.add("Gray"); Question pick =
   * assignlong.insertMultipleChoiceQuestion("Colour?",choices,1); assertTrue(true); }
   */
  @Test
  public void getDueDate() throws Exception {
    assertEquals(assignlong.getDueDate(), "2018-11-30");
  }

  @Test
  public void getAssignmentName() throws Exception {
    assertEquals(assignlong.getAssignmentName(), "a01");
  }

  @Test
  public void getQuestions() throws Exception {
    List<Question> q = assignlong.getQuestions();
    assertEquals(q.get(0).getChoices().get(0), "apple");
  }

  @Test
  public void isReleased() throws Exception {
    assertFalse(assignlong.isReleased());
  }

  @Test
  public void release() throws Exception {
    assertTrue(assignlong.release());
  }

  @Test
  public void withinDueDate() throws Exception {
    assertTrue(assignlong.withinDueDate());
  }
}
