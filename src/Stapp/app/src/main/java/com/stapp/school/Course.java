package com.stapp.school;

import java.util.ArrayList;

import com.stapp.database.DatabaseDriver;
import com.stapp.databasehelpers.CourseHelper;
import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.ClassAlreadyExistsException;
import com.stapp.exceptions.StudentAlreadyExistsException;
import com.stapp.exceptions.UserNotFoundException;


/**
 * Created by wenboma on 04/11/2017.
 */

public class Course {

  private String courseCode = "";
  private boolean validCourse = false;

  /**
   * Gets existing course from database.
   */
  public Course(String courseCode) {
    if (CourseHelper.courseExists(courseCode)) {
      this.courseCode = courseCode;
      this.validCourse = true;
    }
  }

  /**
   * Creates a new course and adds to Database.
   */
  public Course(String courseCode, String profUsername) {
      try {
        if (UserHelper.getRoleNameGivenUsername(profUsername).equals("PROFESSOR")) {
          CourseHelper.insertCourse(courseCode, profUsername);
          this.validCourse = true;
          this.courseCode = courseCode;
        }
      } catch (UserNotFoundException | ClassAlreadyExistsException e) {
      }
  }

  public boolean isValidCourse() {
    return validCourse;
  }

  public String getProf() {
    try {
      return CourseHelper.getProfUsername(courseCode);
    } catch(ClassNotFoundException e) {
      return null;
    }
  }

  public long addStudent(String username) throws StudentAlreadyExistsException, ClassNotFoundException {
    return CourseHelper.insertStudentToCourse(courseCode, username);
  }

  public ArrayList<String> getStudentUsernames() {
    try  {
      ArrayList<String> studentlist = CourseHelper.getStudentUsernames(courseCode);
      return studentlist;
    } catch (ClassNotFoundException E) {
      return null;
    }
  }

  public void removeStudent(String username) {
    try {
      CourseHelper.removeStudentFromCourse(courseCode, username);
    } catch (UserNotFoundException e) {
    }
  }

  public void archiveCourse() throws ClassNotFoundException{
      CourseHelper.archiveCourse(this.courseCode);
  }
}
