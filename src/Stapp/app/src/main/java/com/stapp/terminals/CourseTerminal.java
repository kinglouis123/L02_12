package com.stapp.terminals;

import com.stapp.school.Course;

/** Created by JR on 2017-11-04. */
public class CourseTerminal {
  /**
   * Creates a new course given a course code and
   *
   * @param courseCode unique code of the course (e.g. STAB22H317)
   * @param profUsername username of the course's professor
   * @return the new course
   */
  public static Course createNewCourse(String courseCode, String profUsername) {
    Course course = new Course(courseCode, profUsername);
    if (course.isValidCourse()) {
      return course;
    }
    return null;
  }

  /**
   * Generate a CourseDisplay object from the database, with all of its information (essentially a
   * factory method).
   *
   * @param courseCode
   * @return the course
   */
  public static Course getCourse(String courseCode) {
    Course course = new Course(courseCode);
    if (course.isValidCourse()) {
      return course;
    }
    return null;
  }
}
