package com.stapp.databasehelpers;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;
import com.stapp.school.Assignment;
import com.stapp.school.Question;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rahulkumar1998 on 2017-11-05.
 */

public class AssignmentHelper {

  private static DatabaseDriver databaseDriver;

  private static void openDatabase() {
    if (databaseDriver == null) {
      databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());
    }
  }

  private static void closeDatabase() {
    databaseDriver.close();
    databaseDriver = null;
  }

  public static int getAssignmentId(String assignmentName, String className) {
    openDatabase();
    int Id = databaseDriver.getAssignmentId(assignmentName, className);
    closeDatabase();
    return Id;
  }

  public static String getAssignmentDueDate(String assignmentName, String className) {
    openDatabase();
    String dueDate = databaseDriver.getAssignmentDueDate(assignmentName, className);
    closeDatabase();
    return dueDate;
  }


  public static boolean assignmentExists(String assignmentName, String courseName) {
    openDatabase();
    boolean exists = databaseDriver.assignmentExists(assignmentName, courseName);
    closeDatabase();
    return exists;
  }

  public static boolean assignmentExists(int Id) {
    openDatabase();
    boolean exists = databaseDriver.assignmentExists(Id);
    closeDatabase();
    return exists;
  }

  public static int createAssignment(String assignmentName, String due, String courseName) {
    openDatabase();
    databaseDriver.insertAssignment(assignmentName, due, courseName);
    int Id = databaseDriver.getAssignmentId(assignmentName, courseName);
    closeDatabase();
    return Id;
  }

  public static List<Question> getQuestions(int assignmentId) {
      openDatabase();
      List<Question> questions = databaseDriver.getQuestions(assignmentId);
      closeDatabase();
      return questions;
  }

  /**
   * Insert a multiple choice question into the database
   * @param assignmentId
   * @param question the question to put
   * @param choices length <= 4, 4 choices only
   * @param correctIndex the index of the choice in choices that is the correct answer, < 4
   * @return id of the question
   */
  public static long insertMultipleChoiceQuestion(int assignmentId, String question, List<String> choices,
                                           int correctIndex) {
      openDatabase();
      if (choices.size() > 4) return -1;
      long id = databaseDriver.insertMultipleChoiceQuestion(assignmentId, question, choices.get(0),
              choices.get(1), choices.get(2), choices.get(3), choices.get(correctIndex));
      closeDatabase();
      return id;
  }

  public static boolean assignmentIsReleased(int assignmentId) {
    openDatabase();
    boolean result = databaseDriver.assignmentIsReleased(assignmentId);
    closeDatabase();
    return result;
  }

  public static boolean releaseAssignment(int assignmentId) {
    openDatabase();
    boolean result = databaseDriver.releaseAssignment(assignmentId);
    closeDatabase();
    return result;
  }

  public static ArrayList<Assignment> getAssignmentsOfStudent(String username) {
    openDatabase();
    ArrayList<Assignment> assignments = databaseDriver.getAssignmentsOfStudent(username);
    closeDatabase();
    return assignments;
  }

  public static void submitAssignment(String username, int assignmentId, String grade, String time) {
    openDatabase();
    databaseDriver.submitAssignment(username, assignmentId, grade, time);
    closeDatabase();
  }

  public static String getGrade(String username, int assignmentId) {
    openDatabase();
    String grade = databaseDriver.getGrade(username, assignmentId);
    closeDatabase();
    return grade;
  }

  /**
   * Returns a String representation of the current date in the appropriate YYYY-MM-DD format.
   */
  public static String getCurrentDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, 1);
    // formatted to the form YYYY-MM-DD
    SimpleDateFormat formatting = new SimpleDateFormat("yyyy-MM-dd");
    return formatting.format(calendar.getTime());
  }

  public static String getCourseCode(int assignmentId) {
    openDatabase();
    String course = databaseDriver.getCourseCode(assignmentId);
    closeDatabase();
    return course;
  }

  public static String getAssignmentName(int assignmentId) {
    openDatabase();
    String name = databaseDriver.getAssignmentName(assignmentId);
    closeDatabase();
    return name;
  }
}