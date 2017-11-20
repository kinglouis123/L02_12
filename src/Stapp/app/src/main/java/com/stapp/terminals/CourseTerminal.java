package com.stapp.terminals;

import com.stapp.databasehelpers.CourseHelper;
import com.stapp.exceptions.ClassAlreadyExistsException;
import com.stapp.school.Assignment;
import com.stapp.school.Course;

import java.util.List;

/**
 * Created by JR on 2017-11-04.
 */

public class CourseTerminal {
    /**
     * Creates a new course given a course code and
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
     * Generate a CourseDisplay object from the database, with all of its information (essentially
     * a factory method).
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

    /**
     * Generate a list of Assignments associated with the given courseCode from the database.
     * @param courseCode
     * @return the list of courses
     */
    public static List<Assignment> getAssignments(String courseCode){
        return null; // To be implemented
    }
}
