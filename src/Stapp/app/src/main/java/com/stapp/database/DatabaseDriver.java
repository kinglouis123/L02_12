package com.stapp.database;

import android.content.ContentValues;
import android.content.Context;
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
    sqLiteDatabase.execSQL("CREATE TABLES USER (ID INTEGER PRIMARY KEY NOT NULL, USERNAME TEXT NOT NULL, NAME TEXT NOT NULL, PASSWORD TEXT NOT NULL, ROLE INTEGER NOT NULL)");
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
  protected boolean updateUserName(String username, int id) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("USERNAME", username);
    return sqLiteDatabase.update("USERS", contentValues, "ID = ?", new String[]{String.valueOf(id)})
        > 0;
  }

  protected boolean updatePassword(String password, int id) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("PASSWORD", password);
    return sqLiteDatabase.update("USERS", contentValues, "ID = ?", new String[]{String.valueOf(id)})
        > 0;
  }

  protected boolean updateName(String name, int id) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("NAME", name);
    return sqLiteDatabase.update("USERS", contentValues, "ID = ?", new String[]{String.valueOf(id)})
        > 0;
  }

  protected boolean updateRole(int role, int id) {
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("ROLE", role);
    return sqLiteDatabase.update("USERS", contentValues, "ID = ?", new String[]{String.valueOf(id)})
        > 0;
  }

    // SELECT


}
