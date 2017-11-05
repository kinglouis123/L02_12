package com.stapp.school;

import java.util.ArrayList;

import com.stapp.databasehelpers.ClassHelper;
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
      return ClassHelper.getProfUsername(courseCode);
    } catch(ClassNotFoundException e) {
      return null;
    }
  }

  public long addStudent(String username) throws StudentAlreadyExistsException, ClassNotFoundException {
    return ClassHelper.insertStudentToClass(courseCode, username);
  }

  public ArrayList<String> getStudentUsernames() {
    try  {
      ArrayList<String> studentlist = ClassHelper.getStudentUsernames(courseCode);
      return studentlist;
    } catch (ClassNotFoundException E) {
      return null;
    }
  }

  public void removeStudent(String username) {
    try {
      ClassHelper.removeStudentFromClass(courseCode, username);
    } catch (UserNotFoundException e) {
    }
  }

  public void makeArchives() throws ClassNotFoundException{
      ClassHelper.archiveClass(this.courseCode);
  }
}
