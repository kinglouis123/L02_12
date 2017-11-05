package com.stapp.school;

import java.util.ArrayList;

import com.stapp.databasehelpers.CourseHelper;
import com.stapp.exceptions.StudentAlreadyExistsException;
import com.stapp.exceptions.UserNotFoundException;


/**
 * Created by wenboma on 04/11/2017.
 */

public class Course {

  private String courseCode;

  public Course(String courseCode) {
    this.courseCode = courseCode;
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

  public void makeArchives() throws ClassNotFoundException{
      CourseHelper.archiveCourse(this.courseCode);
  }
}
