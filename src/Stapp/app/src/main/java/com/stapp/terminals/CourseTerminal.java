package com.stapp.terminals;

import com.stapp.databasehelpers.ClassHelper;
import com.stapp.exceptions.ClassAlreadyExistsException;
import com.stapp.school.Course;

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
        if (ClassHelper.classExists(courseCode)) {
            return null;
        }

        try {
            ClassHelper.insertClass(courseCode, profUsername);
            return new Course(courseCode);
        } catch (ClassAlreadyExistsException e) {
            return null;
        }
    }

    /**
     * Generate a Course object from the database, with all of its information (essentially
     * a factory method).
     * @param courseCode
     * @return the course
     */
    public static Course getCourse(String courseCode) {
        if (!ClassHelper.classExists(courseCode)) {
            return null;
        }
        return new Course(courseCode);
    }
}
