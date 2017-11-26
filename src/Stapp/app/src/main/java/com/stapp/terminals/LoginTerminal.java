package com.stapp.terminals;

import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.UserNotFoundException;
import com.stapp.users.Professor;
import com.stapp.users.Student;

/** Created by rahulkumar1998 on 2017-10-23. */
public class LoginTerminal {

  /**
   * Get a Professor object from the Database.
   *
   * @param username of Professor
   * @param password of Professor
   * @return Professor object, null if authentication fails!
   */
  public static Professor getProfessor(String username, String password) {
    try {
      Professor professor = new Professor(username, password);
      if (professor.isLoggedIn() && professor.getRoleName().equals("PROFESSOR")) {
        return professor;
      }
    } catch (UserNotFoundException e) {
      return null;
    }
    return null;
  }

  /**
   * Get a Student object from the Database.
   *
   * @param username of Student
   * @param password of Student
   * @return Student object, null if authentication fails!
   */
  public static Student getStudent(String username, String password) {
    try {
      Student student = new Student(username, password);
      if (student.isLoggedIn() && student.getRoleName().equals("STUDENT")) {
        return student;
      }
      return null;
    } catch (UserNotFoundException e) {
      return null;
    }
  }

  /**
   * Create a new Professor and add to Database.
   *
   * @param username of Professor
   * @param name of Professor
   * @param password of Professor
   * @return Professor Object, null if Professor already exists or wrong password provided!
   */
  public static Professor newProfessor(String username, String name, String password) {
    if (UserHelper.userExists(username)) {
      return null;
    }
    Professor professor = new Professor(username, name, password);
    if (professor.isLoggedIn() && professor.getRoleName().equals("PROFESSOR")) {
      return professor;
    }
    return null;
  }

  /**
   * Create a new Student and add to Database.
   *
   * @param username of Student
   * @param name of Student
   * @param password of Student
   * @return Student Object, null if Student already exists or wrong password provided!
   */
  public static Student newStudent(String username, String name, String password) {
    if (UserHelper.userExists(username)) {
      return null;
    }
    Student student = new Student(username, name, password);
    if (student.isLoggedIn() && student.getRoleName().equals("STUDENT")) {
      return student;
    }
    return null;
  }
}
