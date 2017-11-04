package com.stapp.databasehelpers;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;

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
        return databaseDriver.insertStudentToClass(className, studentUsername);
    }
}
