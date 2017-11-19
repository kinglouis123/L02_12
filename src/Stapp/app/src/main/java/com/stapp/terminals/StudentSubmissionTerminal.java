package com.stapp.terminals;

import com.stapp.databasehelpers.AssignmentHelper;
import com.stapp.databasehelpers.CourseHelper;
import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.UserNotFoundException;
import com.stapp.school.Assignment;
import com.stapp.school.StudentSubmission;
import com.stapp.users.Student;

import java.util.ArrayList;

/**
 * Created by rahulkumar1998 on 2017-11-18.
 */

public class StudentSubmissionTerminal {

  public static StudentSubmission startNewSubmission(String username, int assignmentId) {
    Assignment assignment = AssignmentTerminal.getAssignment(assignmentId);
    if (assignment != null) {
      try {
        String courseCode = AssignmentHelper.getCourseCode(assignmentId);
        if (assignment.isReleased() && assignment.withinDueDate()) {
          if (CourseHelper.getStudentCourseNames(username).contains(courseCode)) {
              return new StudentSubmission(username, assignmentId);
          }
        }
      } catch (UserNotFoundException e) {
        return null;
      }
    }
    return null;
  }

}
