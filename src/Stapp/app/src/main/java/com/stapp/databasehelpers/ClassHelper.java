package com.stapp.databasehelpers;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;

import java.util.ArrayList;

/**
 * Created by JR on 2017-11-01.
 */

public class ClassHelper {
    public static DatabaseDriver databaseDriver;

    private static void openDatabase() {
        if (databaseDriver == null) {
            databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());
        }
    }

    private static void closeDatabase() {
        databaseDriver.close();
        databaseDriver = null;
    }

    public static void insertClass(String name, String profUsername) {
        openDatabase();
        databaseDriver.insertClass(name, profUsername);
        closeDatabase();
    }

    public static long insertStudentToClass(String className, String studentUsername) {
        openDatabase();
        long id = databaseDriver.insertStudentToClass(className, studentUsername);
        closeDatabase();
        return id;
    }

    public static boolean archiveClass(String className) {
        openDatabase();
        boolean updated = databaseDriver.archiveClass(className);
        closeDatabase();
        return updated;
    }

    public static boolean removeStudentFromClass(String className, String username) {
        openDatabase();
        boolean updated = databaseDriver.removeStudentFromClass(className, username);
        closeDatabase();
        return updated;
    }

    public ArrayList<String> getStudentUsernames(String className) {
        openDatabase();
        ArrayList<String> usernames = databaseDriver.getStudentUsernames(className);
        closeDatabase();
        return usernames;
    }

    public ArrayList<String> getStudentClassNames(String username) {
        openDatabase();
        ArrayList<String> classNames = databaseDriver.getStudentClassNames(username);
        closeDatabase();
        return classNames;
    }

    public String getProfUsername(String className) {
        openDatabase();
        String profName = databaseDriver.getProfUsername(className);
        closeDatabase();
        return profName;
    }

    public boolean classNotArchived(String className) {
        openDatabase();
        boolean notArchived = databaseDriver.classNotArchived(className);
        closeDatabase();
        return notArchived;
    }

    public boolean classExists(String className) {
        openDatabase();
        boolean classExists = databaseDriver.classExists(className);
        closeDatabase();
        return classExists;
    }


}
