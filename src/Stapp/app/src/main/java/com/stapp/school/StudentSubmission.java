package com.stapp.school;

import com.stapp.databasehelpers.AssignmentHelper;
import com.stapp.terminals.AssignmentTerminal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rahulkumar1998 on 2017-11-16.
 */

public class StudentSubmission {

  private String studentUsername;
  private Assignment assignment;
  private int assignmentId;
  private ArrayList<Question> questions;
  private Question currentQuestion;
  private int currentQuestionIndex = 0;
  private int numerator;
  private int denominator;

  public StudentSubmission(String studentUsername, int assignmentId) {
    this.studentUsername = studentUsername;
    this.assignmentId = assignmentId;
    this.assignment = AssignmentTerminal.getAssignment(assignmentId);
    this.questions = (ArrayList<Question>) assignment.getQuestions();
    this.denominator = questions.size();
  }

  /**
   * Gets the next question in the assignment, null if end of assignment.
   * Before calling this method again, make sure to answer previous question!
   */
  public Question getNextQuestion() {
    if (questions.size() > currentQuestionIndex) {
      currentQuestion = questions.get(currentQuestionIndex);
      return currentQuestion;
    }
    return null;
  }

  public boolean answerCurrentQuestion(String answer) {
    boolean result = false;
    if (currentQuestion.getAnswer().equals(answer)) {
      result = true;
      numerator += 1;
    }
    currentQuestionIndex += 1;
    return result;
  }

  public String getCurrentMark() {
    return numerator + "/" + denominator;
  }

  public boolean submitAssignment() {
    boolean success = false;
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, 1);
    // formatted to the form YYYY-MM-DD
    SimpleDateFormat formatting = new SimpleDateFormat("yyyy-MM-dd");
    String submitDate = formatting.format(calendar.getTime());
    String dueDate = assignment.getDueDate();

    if (withinDueDate(submitDate, dueDate, formatting)) {
      AssignmentHelper.submitAssignment(studentUsername, assignmentId, getCurrentMark(), submitDate);
      success = true;
    }
    return success;
  }

  /**
   * Checks if the submit date is <= due date.
   */
  private boolean withinDueDate(String submitDate, String dueDate, SimpleDateFormat formatting) {
    try {
      Date submit = formatting.parse(submitDate);
      Date due = formatting.parse(dueDate);
      return (submit.compareTo(due) <= 0);
    } catch (ParseException e) {
      return false;
    }
  }
}
