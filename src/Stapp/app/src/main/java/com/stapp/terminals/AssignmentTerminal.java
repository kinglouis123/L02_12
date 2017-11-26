package com.stapp.terminals;

import com.stapp.databasehelpers.AssignmentHelper;
import com.stapp.school.Assignment;

/** Created by rahulkumar1998 on 2017-11-05. */
public class AssignmentTerminal {

  public static Assignment createNewAssignment(
      String assignmentName, String due, String courseCode) {
    Assignment assignment = new Assignment(assignmentName, due, courseCode);
    if (assignment.isValidAssignment()) {
      return assignment;
    }
    return null;
  }

  public static Assignment getAssignment(int id) {
    Assignment assignment = new Assignment(id);
    if (assignment.isValidAssignment()) {
      return assignment;
    }
    return null;
  }

  // Test function
  public static int getAssignmentId(String assignmentName, String className) {
    return AssignmentHelper.getAssignmentId(assignmentName, className);
  }
}
