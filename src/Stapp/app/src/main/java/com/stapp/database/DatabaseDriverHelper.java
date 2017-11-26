package com.stapp.database;

import java.io.File;

/** Class used to upgrade Database. */
public class DatabaseDriverHelper {
  private static DatabaseDriver databaseDriver;

  /** Initializes database. */
  public static void initializeDatabase() {
    databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());
    databaseDriver.close();
  }

  /** Reninitializes database. */
  public static void reinitializeDatabase() {
    deleteDatabase();
    databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());
    databaseDriver.close();
  }

  public static boolean databaseExists() {
    File database = ContextHelper.getStappContext().getDatabasePath("database.db");
    return database.exists();
  }

  public static boolean deleteDatabase() {
    File database = ContextHelper.getStappContext().getDatabasePath("database.db");
    return database.delete();
  }
}
