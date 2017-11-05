package com.stapp.databasehelpers;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;
import com.stapp.exceptions.ClassAlreadyExistsException;
import com.stapp.exceptions.StudentAlreadyExistsException;
import com.stapp.exceptions.UserNotFoundException;

import java.util.ArrayList;

/**
 * Created by JR on 2017-11-01.
 */

public class CourseHelper {
    private static DatabaseDriver databaseDriver;

    private static void openDatabase() {
        if (databaseDriver == null) {
            databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());
        }
    }

    private static void closeDatabase() {
        databaseDriver.close();
        databaseDriver = null;
    }

    public static void insertCourse(String name, String profUsername)
            throws ClassAlreadyExistsException {
        openDatabase();
        if (databaseDriver.courseExists(name)) {
            closeDatabase();
            throw new ClassAlreadyExistsException();
        }
        databaseDriver.insertCourse(name, profUsername);
        closeDatabase();
    }

    public static long insertStudentToCourse(String className, String studentUsername)
            throws ClassNotFoundException, StudentAlreadyExistsException {
        openDatabase();
        // Make sure class exists and user is not already in the class
        if (!databaseDriver.courseExists(className)) {
            closeDatabase();
            throw new ClassNotFoundException();
        }
        if (databaseDriver.getStudentUsernames(className).contains(studentUsername)) {
            closeDatabase();
            throw new StudentAlreadyExistsException();
        }

        long id = databaseDriver.insertStudentToCourse(className, studentUsername);
        closeDatabase();
        return id;
    }

    public static boolean archiveCourse(String className) throws ClassNotFoundException {
        openDatabase();
        // Class already archived
        if (!databaseDriver.courseExists(className)) {
            closeDatabase();
            throw new ClassNotFoundException();
        }

        boolean updated = databaseDriver.archiveCourse(className);
        closeDatabase();
        return updated;
    }

    public static boolean removeStudentFromCourse(String className, String username)
            throws UserNotFoundException {
        openDatabase();
        if (!databaseDriver.getStudentUsernames(className).contains(username)) {
            closeDatabase();
            throw new UserNotFoundException();
        }

        boolean updated = databaseDriver.removeStudentFromCourse(className, username);
        closeDatabase();
        return updated;
    }

    public static ArrayList<String> getStudentUsernames(String className)
            throws ClassNotFoundException {
        openDatabase();
        if (!databaseDriver.courseExists(className)) {
            closeDatabase();
            throw new ClassNotFoundException();
        }

        ArrayList<String> usernames = databaseDriver.getStudentUsernames(className);
        closeDatabase();
        return usernames;
    }

    public static ArrayList<String> getStudentCourseNames(String username)
            throws UserNotFoundException {
        openDatabase();
        if (!databaseDriver.userExists(username)) {
            closeDatabase();
            throw new UserNotFoundException();
        }

        ArrayList<String> classNames = databaseDriver.getStudentCourseNames(username);
        closeDatabase();
        return classNames;
    }

    public static String getProfUsername(String className) throws ClassNotFoundException {
        openDatabase();
        if (!databaseDriver.courseExists(className)) {
            closeDatabase();
            throw new ClassNotFoundException();
        }

        String profName = databaseDriver.getProfUsername(className);
        closeDatabase();
        return profName;
    }

    public static ArrayList<String> getProfCourseNames(String username)
            throws UserNotFoundException {
        openDatabase();
        if (!UserHelper.userExists(username)) {
            throw new UserNotFoundException();
        }

        ArrayList<String> classes = databaseDriver.getProfCourses(username);
        closeDatabase();
        return classes;
    }

    public static boolean classNotArchived(String className) throws ClassNotFoundException {
        openDatabase();
        if (!databaseDriver.courseExists(className)) {
            closeDatabase();
            throw new ClassNotFoundException();
        }

        boolean notArchived = databaseDriver.courseNotArchived(className);
        closeDatabase();
        return notArchived;
    }

    public static boolean courseExists(String className) {
        openDatabase();
        boolean classExists = databaseDriver.courseExists(className);
        closeDatabase();
        return classExists;
    }


}
