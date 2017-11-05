package com.stapp.databasehelpers;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;

import java.util.ArrayList;
import java.util.HashMap;

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

}