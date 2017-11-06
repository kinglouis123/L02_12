package com.stapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stapp.school.Assignment;
import com.stapp.school.Question;
import com.stapp.security.PasswordHelpers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;


/**
 * Driver for Database
 */

public class DatabaseDriver extends SQLiteOpenHelper {

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
    sqLiteDatabase.execSQL("CREATE TABLE COURSES " +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "COURSENAME TEXT NOT NULL, " +
        "PROFUSERNAME TEXT NOT NULL, " +
        "ARCHIVED INTEGER NOT NULL)");
    sqLiteDatabase.execSQL("CREATE TABLE STUDENTCOURSELINKS " +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "STUDENTUSERNAME TEXT NOT NULL," +
        "COURSENAME TEXT NOT NULL)");

    sqLiteDatabase.execSQL("CREATE TABLE ASSIGNMENTCOURSELINKS " +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "ASSIGNMENTNAME TEXT NOT NULL, " +
        "COURSE TEXT NOT NULL, " +
        "DUE TEXT NOT NULL)");

    sqLiteDatabase.execSQL("CREATE TABLE QUESTIONS " +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "ASSIGNMENTID INTEGER NOT NULL, " +
        "QUESTION TEXT NOT NULL, " +
        "CHOICE1 TEXT NOT NULL, " +
        "CHOICE2 TEXT NOT NULL, " +
        "CHOICE3 TEXT NOT NULL, " +
        "CHOICE4 TEXT NOT NULL, "+
        "CORRECTANSWER TEXT NOT NULL)");
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    onCreate(sqLiteDatabase);
  }


  // QUESTIONS STUFF
  // INSERT

  /**
   * @param correctAnswer must be the same as one of the choices
   * @return question ID in database
   */
  public long insertMultipleChoiceQuestion(int assignmentId, String question, String choice1,
                                           String choice2, String choice3, String choice4,
                                           String correctAnswer) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("ASSIGNMENTID", assignmentId);
    contentValues.put("QUESTION", question);
    contentValues.put("CHOICE1", choice1);
    contentValues.put("CHOICE2", choice2);
    contentValues.put("CHOICE3", choice3);
    contentValues.put("CHOICE4", choice4);
    contentValues.put("CORRECTANSWER", correctAnswer);
    return sqLiteDatabase.insert("QUESTIONS", null, contentValues);
  }

  // SELECT

  public String getQuestionString(int Id) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT QUESTION FROM QUESTIONS WHERE ID = ?",
        new String[]{String.valueOf(Id)});
    cursor.moveToFirst();
    String question = cursor.getString(cursor.getColumnIndex("QUESTION"));
    cursor.close();
    return question;
  }

  public ArrayList<String> getChoices(int Id) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    ArrayList<String> choices = new ArrayList<>();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM QUESTIONS WHERE ID = ?",
        new String[]{String.valueOf(Id)});
    cursor.moveToFirst();
    choices.add(cursor.getString(cursor.getColumnIndex("CHOICE1")));
    choices.add(cursor.getString(cursor.getColumnIndex("CHOICE2")));
    choices.add(cursor.getString(cursor.getColumnIndex("CHOICE3")));
    choices.add(cursor.getString(cursor.getColumnIndex("CHOICE4")));
    return choices;
  }

  public String getAnswer(int Id) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT CORRECTANSWER FROM QUESTIONS WHERE ID = ?",
        new String[]{String.valueOf(Id)});
    cursor.moveToFirst();
    String answer = cursor.getString(cursor.getColumnIndex("CORRECTANSWER"));
    cursor.close();
    return answer;
  }



  // ASSIGNMENTS STUFF
  // INSERT

  /**
   * @param due must be in format "yyyy-MM-dd HH:mm:ss"
   */
  public void insertAssignment(String assignmentName, String due, String courseName) {
    SQLiteDatabase sqliteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("ASSIGNMENTNAME", assignmentName);
    contentValues.put("COURSENAME", courseName);
    contentValues.put("DUE", due);
    sqliteDatabase.insert("ASSIGNMENTCOURSELINKS", null, contentValues);
  }

  public String getAssignmentDueDate(String assignmentName, String courseName) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT DUE FROM ASSIGNMENTCOURSELINKS WHERE " +
        "ASSIGNMENTNAME = ? AND COURSENAME = ?", new String[]{assignmentName, courseName});
    cursor.moveToFirst();
    String dueDate = cursor.getString(cursor.getColumnIndex("DUE"));
    cursor.close();
    return dueDate;
  }


  // UPDATE
  // SELECT

  public boolean assignmentExists(String assignmentName, String courseName) {
    boolean exists = true;
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ASSIGNMENTCOURSELINKS WHERE " +
            "ASSIGNMENTNAME = ? AND COURSENAME = ?", new String[]{assignmentName, courseName});
    if (cursor.getCount() <= 0) {
      exists = false;
    }
    cursor.close();
    return exists;
  }

  public boolean assignmentExists(int assignmentId) {
    boolean exists = true;
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ASSIGNMENTCOURSELINKS WHERE ID = ?",
        new String[]{String.valueOf(assignmentId)});
    if (cursor.getCount() <= 0) {
      exists = false;
    }
    cursor.close();
    return exists;
  }

  public int getAssignmentId(String assignmentName, String courseName) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ID FROM ASSIGNMENTCOURSELINKS WHERE " +
        "ASSIGNMENTNAME = ? AND COURSENAME = ?", new String[]{assignmentName, courseName});
    cursor.moveToFirst();
    int Id = cursor.getInt(cursor.getColumnIndex("ID"));
    cursor.close();
    return Id;
  }

  public ArrayList<Question> getQuestions(int assignmentId) {
    ArrayList<Question> questions = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ID FROM QUESTIONS WHERE " +
        "ASSIGNMENTID = ?", new String[]{String.valueOf(assignmentId)});
    while (cursor.moveToNext()) {
      questions.add(new Question(cursor.getInt(cursor.getColumnIndex("ID"))));
    }
    cursor.close();
    return questions;
  }



  // COURSES STUFF STUFF
  // INSERT
  public void insertCourse(String name, String profUsername) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("COURSENAME", name);
    contentValues.put("PROFUSERNAME", profUsername);
    contentValues.put("ARCHIVED", 0);
    sqLiteDatabase.insert("COURSES", null, contentValues);
  }

  public long insertStudentToCourse(String courseName, String studentUsername) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("STUDENTUSERNAME", studentUsername);
    contentValues.put("COURSENAME", courseName);
    return sqLiteDatabase.insert("STUDENTCOURSELINKS", null, contentValues);
  }

  // UPDATE
  public boolean archiveCourse(String courseName) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("ARCHIVED", 1);
    return sqLiteDatabase.update("COURSES", contentValues, "COURSENAME = ?",
        new String[]{courseName}) > 0;
  }

  public boolean removeStudentFromCourse(String courseName, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    return sqLiteDatabase.delete("STUDENTCOURSELINKS", "COURSENAME = ? " +
            "AND STUDENTUSERNAME = ?", new String[]{courseName, username}) > 0;
  }

  // SELECT

  public ArrayList<String> getStudentUsernames(String courseName) {
    ArrayList<String> usernames = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT STUDENTUSERNAME FROM STUDENTCOURSELINKS " +
        "WHERE COURSENAME = ?", new String[]{courseName});
    while (cursor.moveToNext()) {
      usernames.add(cursor.getString(cursor.getColumnIndex("STUDENTUSERNAME")));
    }
    cursor.close();
    return usernames;
  }

  public ArrayList<String> getStudentCourseNames(String username) {
    ArrayList<String> courseNames = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT COURSENAME FROM STUDENTCOURSELINKS " +
        "WHERE STUDENTUSERNAME = ?", new String[]{username});
    while (cursor.moveToNext()) {
      courseNames.add(cursor.getString(cursor.getColumnIndex("COURSENAME")));
    }
    cursor.close();
    return courseNames;
  }

  public String getProfUsername(String courseName) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT PROFUSERNAME FROM COURSES " +
        "WHERE COURSENAME = ?", new String[]{courseName});
    cursor.moveToFirst();
    String profUserName = cursor.getString(cursor.getColumnIndex("PROFUSERNAME"));
    cursor.close();
    return profUserName;
  }

  public ArrayList<String> getProfCourses(String profUsername) {
    ArrayList<String> profCourses = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT COURSENAME FROM COURSES WHERE " +
        "PROFUSERNAME = ?", new String[]{profUsername});
    while (cursor.moveToNext()) {
      profCourses.add(cursor.getString(cursor.getColumnIndex("COURSENAME")));
    }
    cursor.close();
    return profCourses;
  }

  /**
   * Checks if a class is archived or not (ie if it is still running this semester)
   * @return true if class isn't archived
   */
  public boolean courseNotArchived(String courseName) {
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ARCHIVED FROM COURSES WHERE " +
        "COURSENAME = ?", new String[]{courseName});
    int archived = cursor.getInt(cursor.getColumnIndex("ARCHIVED"));
    cursor.close();
    return archived == 0;
  }

  public boolean courseExists(String courseName) {
    boolean exists = true;
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COURSES WHERE COURSENAME = ?",
        new String[]{courseName});
    if (cursor.getCount() <= 0) {
      exists = false;
    }
    cursor.close();
    return exists;
  }

  public ArrayList<Assignment> getAssignments(String courseName) {
    Assignment assignment;
    ArrayList<Assignment> assignments = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ID FROM " +
        "ASSIGNMENTCOURSELINKS WHERE COURSENAME = ?", new String[]{courseName});
    while (cursor.moveToNext()) {
      assignment = new Assignment(cursor.getInt(cursor.getColumnIndex("ID")));
      if (assignment.isValidAssignment()) {
        assignments.add(assignment);
      }
    }
    cursor.close();
    return assignments;
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
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?",
        new String[]{username})
        > 0;
  }

  public boolean updateName(String name, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("NAME", name);
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?",
        new String[]{username})
        > 0;
  }

  public boolean updateRole(int role, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("ROLE", role);
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?",
        new String[]{username})
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
