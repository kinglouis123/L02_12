package com.stapp.school;

import com.stapp.databasehelpers.AssignmentHelper;
import com.stapp.terminals.AssignmentTerminal;

import java.util.ArrayList;

/** Created by rahulkumar1998 on 2017-11-16. */
public class StudentSubmission {

  private String studentUsername;
  private Assignment assignment;
  private int assignmentId;
  private ArrayList<Question> questions;
  private Question currentQuestion;
  private int currentQuestionIndex = 0;
  private int numerator = -1;
  private int denominator;
  private String grade;

  public StudentSubmission(String studentUsername, int assignmentId) {
    this.studentUsername = studentUsername;
    this.assignmentId = assignmentId;
    this.assignment = AssignmentTerminal.getAssignment(assignmentId);
    this.questions = (ArrayList<Question>) assignment.getQuestions();
    this.denominator = questions.size();
    this.grade = AssignmentHelper.getGrade(studentUsername, assignmentId);
  }

  /**
   * Gets the next question in the assignment, null if end of assignment. Before calling this method
   * again, make sure to answer previous question!
   */
  public Question getNextQuestion() {
    if (questions.size() > currentQuestionIndex) {
      currentQuestion = questions.get(currentQuestionIndex);
      return currentQuestion;
    }
    return null;
  }

  /** Only call AFTER calling getNextQuestion. */
  public boolean answerCurrentQuestion(String answer) {
    boolean result = false;
    if (numerator == -1) {
      numerator += 1;
    }
    if (currentQuestion.getAnswer().equals(answer)) {
      result = true;
      numerator += 1;
    }
    currentQuestionIndex += 1;
    return result;
  }

  public String getCurrentMark() {
    if (numerator == -1) {
      return this.grade;
    }
    return numerator + "/" + denominator;
  }

  public boolean submitAssignment() {
    boolean success = false;
    String submitDate = AssignmentHelper.getCurrentDate();
    if (assignment.withinDueDate()) {
      AssignmentHelper.submitAssignment(
          studentUsername, assignmentId, getCurrentMark(), submitDate);
      success = true;
    }
    return success;
  }
}
