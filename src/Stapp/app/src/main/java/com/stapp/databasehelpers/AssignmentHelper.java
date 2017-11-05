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



}