package com.stapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.stapp.security.PasswordHelpers;


/**
 * Created by jr on 21/10/17.
 */

public class DatabaseDriver extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "bank.db";

  public DatabaseDriver(Context context) {
    super(context, DATABASE_NAME, null, 1);

  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("CREATE TABLE USERS " +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "USERNAME TEXT NOT NULL, NAME TEXT NOT NULL, " +
        "PASSWORD TEXT NOT NULL, " +
        "ROLE INTEGER NOT NULL)");
    sqLiteDatabase.execSQL("CREATE TABLE ROLES" +
        "(ID INTEGER PRIMARY KEY NOT NULL, " +
        "NAME TEXT NOT NULL)");
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    onCreate(sqLiteDatabase);
  }

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
