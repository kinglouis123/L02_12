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
  protected long insertUser(String username, String name, String password, int role) {
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

  // UPDATE
  protected boolean updateUserName(String newUsername, String oldUsername) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("USERNAME", newUsername);
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?", new String[]{oldUsername})
        > 0;
  }

  protected boolean updatePassword(String password, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("PASSWORD", password);
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?", new String[]{username})
        > 0;
  }

  protected boolean updateName(String name, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("NAME", name);
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?", new String[]{username})
        > 0;
  }

  protected boolean updateRole(int role, String username) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("ROLE", role);
    return sqLiteDatabase.update("USERS", contentValues, "USERNAME = ?", new String[]{username})
        > 0;
  }

  // SELECT
  protected int getId(String username) {
    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ID FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    cursor.moveToFirst();
    int value = cursor.getInt(cursor.getColumnIndex("ID"));
    cursor.close();
    return value;
  }

  protected String getName(String username) {
    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT NAME FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    cursor.moveToFirst();
    String value = cursor.getString(cursor.getColumnIndex("NAME"));
    cursor.close();
    return value;
  }

  protected int getRole(String username) {
    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT ROLE FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    cursor.moveToFirst();
    int value = cursor.getInt(cursor.getColumnIndex("ROLE"));
    cursor.close();
    return value;
  }

  protected Cursor getUsersDetails() {
    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    return sqLiteDatabase.rawQuery("SELECT * FROM USERS", null);
  }

  protected String getPassword(String username) {
    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT PASSWORD FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    cursor.moveToFirst();
    String result = cursor.getString(cursor.getColumnIndex("PASSWORD"));
    cursor.close();
    return result;
  }

  protected boolean userExists(String username) {
    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE USERNAME = ?",
        new String[]{username});
    boolean result = true;
    if (cursor.getCount() <= 0) {
      result = false;
    }
    cursor.close();
    return result;
  }

}
