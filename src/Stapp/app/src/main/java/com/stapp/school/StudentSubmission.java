package com.stapp.school;

import com.stapp.terminals.AssignmentTerminal;

import java.util.ArrayList;

/**
 * Created by rahulkumar1998 on 2017-11-16.
 */

public class StudentSubmission {

  private String studentUsername;
  private Assignment assignment;
  private ArrayList<Question> questions;
  private Question currentQuestion;
  private int currentQuestionIndex = 0;
  private int numerator;
  private int denominator;

  public StudentSubmission(String studentUsername, int assignmentId) {
    this.studentUsername = studentUsername;
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

  // TODO
  public void submitAssignment() {

  }
}
