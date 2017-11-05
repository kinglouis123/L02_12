package com.stapp.school;

import com.stapp.databasehelpers.AssignmentHelper;

/**
 * Created by wenboma on 05/11/2017.
 */

public class Assignment {

  private int Id;
  private boolean validAssignment = false;

  public Assignment(int Id) {
    if (AssignmentHelper.assignmentExists(Id)) {
      this.Id = Id;
      validAssignment = true;
    }
  }

  public Assignment(String assignmentName, String due, String courseName) {
    if (!AssignmentHelper.assignmentExists(assignmentName, courseName)) {
      AssignmentHelper.createAssignment(assignmentName, due, courseName);
      this.Id = AssignmentHelper.getAssignmentId(assignmentName, courseName);
      validAssignment = true;
    }
  }

  public boolean isValidAssignment() {
    return validAssignment;
  }

}
