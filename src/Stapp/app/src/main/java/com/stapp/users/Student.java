package com.stapp.users;

import com.stapp.databasehelpers.AssignmentHelper;
import com.stapp.databasehelpers.CourseHelper;
import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.UserNotFoundException;
import com.stapp.school.Assignment;
import com.stapp.school.Course;
import com.stapp.terminals.CourseTerminal;

import java.util.ArrayList;
import java.util.List;

/** Created by rahulkumar1998 on 2017-10-22. */
public class Student extends User {

  public Student(String username, String password) throws UserNotFoundException {
    super(username, password);
  }

  public Student(String username, String name, String password) {
    super(username, name, password, UserHelper.getRoleIdGivenRoleName("STUDENT"));
  }

  /** @return every course the student is in */
  public List<Course> getCourses() {
    try {
      List<String> courseCodes = CourseHelper.getStudentCourseNames(this.getUsername());
      List<Course> courses = new ArrayList<>();

      for (String code : courseCodes) {
        Course curr = CourseTerminal.getCourse(code);
        if (curr != null) {
          courses.add(curr);
        }
      }
      return courses;
    } catch (UserNotFoundException e) {
      // Should be impossible since Student object already exists
      return null;
    }
  }

  public ArrayList<Assignment> getAssignments() {
    return AssignmentHelper.getAssignmentsOfStudent(this.getUsername());
  }

  public String getGrade(int assignmentId) {
    return AssignmentHelper.getGrade(this.getUsername(), assignmentId);
  }
}
