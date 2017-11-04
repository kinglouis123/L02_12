package com.stapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListAdapter;

import com.stapp.databasehelpers.ClassHelper;
import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.UserNotFoundException;
import com.stapp.security.PasswordHelpers;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Professor;
import com.stapp.users.Student;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jr on 21/10/17.
 */

public class DatabaseDriver extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "database.db";

  public DatabaseDriver(Context context) {
    super(context, DATABASE_NAME, null, 1);

  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("CREATE TABLE USERS " +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "USERNAME TEXT NOT NULL, " +
        "NAME TEXT NOT NULL, " +
        "PASSWORD TEXT NOT NULL, " +
        "ROLE INTEGER NOT NULL)");
    sqLiteDatabase.execSQL("CREATE TABLE ROLES " +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "NAME TEXT NOT NULL)");
    sqLiteDatabase.execSQL("CREATE TABLE CLASSES " +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "CLASSNAME TEXT NOT NULL, " +
        "PROFUSERNAME TEXT NOT NULL, " +
        "ARCHIVED INTEGER NOT NULL)");
    sqLiteDatabase.execSQL("CREATE TABLE STUDENTCLASSLINKS " +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "STUDENTUSERNAME TEXT NOT NULL," +
        "CLASSNAME TEXT NOT NULL)");
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    onCreate(sqLiteDatabase);
  }

  // CLASSES STUFF
  // INSERT
  public void insertClass(String name, String profUsername) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("CLASSNAME", name);
    contentValues.put("PROFUSERNAME", profUsername);
    contentValues.put("ARCHIVED", 0);
    sqLiteDatabase.insert("CLASSES", null, contentValues);
  }

  public long insertStudentToClass(String className, String studentUsername) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("STUDENTUSERNAME", studentUsername);
    contentValues.put("CLASSNAME", className);
    return sqLiteDatabase.insert("STUDENTCLASSLINKS", null, contentValues);
  }

  // UPDATE
  public boolean archiveClass(String className) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("ARCHIVED", 1);
    return sqLiteDatabase.update("CLASSES", contentValues, "CLASSNAME = ?", new String[]{className})
        > 0;
  }

  public boolean removeStudentFromClass(String className, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    return sqLiteDatabase.delete("STUDENTCLASSLINKS", "CLASSNAME = ? AND STUDENTUSERNAME = ?",
        new String[]{className, username}) > 0;
  }

  // SELECT
  /** useless
  public String getClassName(String uniqueKey) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT NAME FROM CLASSES WHERE UNIQUEKEY = ?",
        new String[]{uniqueKey});
    cursor.moveToFirst();
    String className = cursor.getString(cursor.getColumnIndex("NAME"));
    cursor.close();
    return className;
  }
   */

  public ArrayList<String> getStudentUsernames(String className) {
    ArrayList<String> usernames = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT STUDENTUSERNAME FROM STUDENTCLASSLINKS WHERE " +
        "CLASSNAME = ?", new String[]{className});
    while (cursor.moveToNext()) {
      usernames.add(cursor.getString(cursor.getColumnIndex("STUDENTUSERNAME")));
    }
    return usernames;
  }

  public ArrayList<String> getStudentClassNames(String username) {
    ArrayList<String> classNames = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT CLASSNAME FROM STUDENTCLASSLINKS WHERE " +
        "STUDENTUSERNAME = ?", new String[]{username});
    while (cursor.moveToNext()) {
      classNames.add(cursor.getString(cursor.getColumnIndex("CLASSNAME")));
    }
    return classNames;
  }

  public String getProfUsername(String className) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT PROFUSERNAME FROM CLASSES WHERE CLASSNAME = ?",
        new String[]{className});
    cursor.moveToFirst();
    return cursor.getString(cursor.getColumnIndex("PROFUSERNAME"));
  }

  /**
   * Checks if a class is archived or not (ie if it is still running this semester)
   * @return true if class isn't archived
   */
  public boolean classNotArchived(String className) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ARCHIVED FROM CLASSES WHERE CLASSNAME = ?",
        new String[]{className});
    int archived = cursor.getInt(cursor.getColumnIndex("ARCHIVED"));
    if (archived == 0) {
      return true;
    }
    return false;
  }

  public boolean classExists(String className) {
    boolean exists = true;
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CLASSES WHERE CLASSNAME = ?",
        new String[]{className});
    if (cursor.getCount() <= 0) {
      exists = false;
    }
    return exists;
  }


  // USERS/ROLES STUFF
  // INSERT
  public long insertUser(String username, String name, String password, int role) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("USERNAME", username);
    contentValues.put("NAME", name);
    // Hash pw
    password = PasswordHelpers.passwordHash(password);
    contentValues.put("PASSWORD", password);
    contentValues.put("ROLE", role);

    return sqLiteDatabase.insert("USERS", null, contentValues);
  }

  public long insertRole(String role) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("NAME", role);
    return sqLiteDatabase.insert("ROLES", null, contentValues);
  }

  // UPDATE

  public boolean updatePassword(String password, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("PASSWORD", password);
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?", new String[]{username})
        > 0;
  }

  public boolean updateName(String name, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("NAME", name);
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?", new String[]{username})
        > 0;
  }

  public boolean updateRole(int role, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("ROLE", role);
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?", new String[]{username})
        > 0;
  }

  // USER STUFF
  // SELECT

  public String getRoleNameGivenUsername(String username) {
    int roleId = getRoleIdGivenUsername(username);
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT NAME FROM ROLES WHERE ID = ?",
        new String[]{String.valueOf(roleId)});
    cursor.moveToFirst();
    String roleName = cursor.getString(cursor.getColumnIndex("NAME"));
    cursor.close();
    return roleName;
  }

  public int getRoleIdGivenRoleName(String roleName) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ID FROM ROLES WHERE NAME = ?",
        new String[]{roleName});
    cursor.moveToFirst();
    int roleId = cursor.getInt(cursor.getColumnIndex("ID"));
    cursor.close();
    return roleId;
  }

  public int getRoleIdGivenUsername(String username) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ROLE FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    cursor.moveToFirst();
    int value = cursor.getInt(cursor.getColumnIndex("ROLE"));
    cursor.close();
    return value;
  }

  public int getId(String username) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ID FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    cursor.moveToFirst();
    int value = cursor.getInt(cursor.getColumnIndex("ID"));
    cursor.close();
    return value;
  }

  public String getName(String username) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT NAME FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    cursor.moveToFirst();
    String value = cursor.getString(cursor.getColumnIndex("NAME"));
    cursor.close();
    return value;
  }

  public Cursor getUsersDetails() {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    return sqLiteDatabase.rawQuery("SELECT * FROM USERS", null);
  }

  public String getPassword(String username) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT PASSWORD FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    cursor.moveToFirst();
    String result = cursor.getString(cursor.getColumnIndex("PASSWORD"));
    cursor.close();
    return result;
  }

  public boolean userExists(String username) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    boolean result = true;
    if (cursor.getCount() <= 0) {
      result = false;
    }
    cursor.close();
    return result;
  }

  public boolean roleExists(int roleId) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ROLES WHERE ID = ?",
        new String[]{String.valueOf(roleId)});
    boolean result = true;
    if (cursor.getCount() <= 0) {
      result = false;
    }
    cursor.close();
    return result;
  }

  public boolean roleExists(String roleName) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ROLES WHERE NAME = ?",
        new String[]{roleName});
    boolean result = true;
    if (cursor.getCount() <= 0) {
      result = false;
    }
    cursor.close();
    return result;
  }

}
