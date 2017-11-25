package com.stapp.school;

import com.stapp.databasehelpers.AssignmentHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wenboma on 05/11/2017.
 */

public class Assignment {

  private int id;
  private boolean validAssignment = false;
  private String assignmentName;
  private String courseName;
  private String dueDate;

  public Assignment(int id) {
    if (AssignmentHelper.assignmentExists(id)) {
      this.id = id;
      this.assignmentName = AssignmentHelper.getAssignmentName(id);
      this.courseName = AssignmentHelper.getCourseCode(id);
      this.dueDate = AssignmentHelper.getAssignmentDueDate(assignmentName, courseName);
      validAssignment = true;
    }
  }

  public Assignment(String assignmentName, String due, String courseName) {
    this.dueDate = due;
    if (!AssignmentHelper.assignmentExists(assignmentName, courseName) && withinDueDate()) {
      AssignmentHelper.createAssignment(assignmentName, due, courseName);
      this.id = AssignmentHelper.getAssignmentId(assignmentName, courseName);
      this.assignmentName = assignmentName;
      this.courseName = courseName;
      validAssignment = true;
    }
  }

  public boolean isValidAssignment() {
    return validAssignment;
  }

  /**
   * Creates a new multiple choice question for the assignment.
   * @param choices size() <= 4
   * @param correctIndex index of the correct answer in the choices list
   * @return the question object, null if not successful
   */
  public Question insertMultipleChoiceQuestion(String question, List<String> choices, int correctIndex) {
      long id = AssignmentHelper.insertMultipleChoiceQuestion(this.id, question, choices, correctIndex);
      if (id == -1) return null;
      return new Question((int)id);
  }

  public String getDueDate() {
      return this.dueDate;
  }

  public String getAssignmentName(){
      return this.assignmentName;
  }

  public List<Question> getQuestions() {
      return AssignmentHelper.getQuestions(this.id);
  }

  public boolean isReleased() {
    return AssignmentHelper.assignmentIsReleased(this.id);
  }

  public boolean release() {
    return AssignmentHelper.releaseAssignment(this.id);
  }

  public int getId() {
    return this.id;
  }

  public boolean withinDueDate() {
    String submitDate = AssignmentHelper.getCurrentDate();
    SimpleDateFormat formatting = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date submit = formatting.parse(submitDate);
      Date due = formatting.parse(this.getDueDate());
      return (submit.compareTo(due) <= 0);
    } catch (ParseException e) {
      return false;
    }
  }

  public String getCourseCode() {
    return this.courseName;
  }


  public int getId(String assignmentName, String courseName) {
    return AssignmentHelper.getAssignmentId(assignmentName, courseName);
  }

  // Test Function
  public int getNumberOfQuestions() {
    return this.getQuestions().size();
  }


}
