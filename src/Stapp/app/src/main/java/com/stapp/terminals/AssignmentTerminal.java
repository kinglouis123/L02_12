package com.stapp.terminals;

import com.stapp.school.Assignment;

/**
 * Created by rahulkumar1998 on 2017-11-05.
 */

public class AssignmentTerminal {

  public static Assignment createNewAssignment(String courseCode, String due, String profUsername) {
    Assignment assignment = new Assignment(courseCode, due, profUsername);
    if (assignment.isValidAssignment()) {
      return assignment;
    }
    return null;
  }

  public Assignment getAssignment(int id) {
    Assignment assignment = new Assignment(id);
    if (assignment.isValidAssignment()) {
      return assignment;
    }
    return null;
  }

}
