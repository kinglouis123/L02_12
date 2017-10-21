package com.stapp.database;

/**
 * Created by rahulkumar1998 on 2017-10-21.
 */

public class InitializeDatabase {
  private static DatabaseDriver databaseDriver;

  /**
   * Initializes database.
   */
  public static void initializeDatabase() {
    databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());
    databaseDriver.close();
  }
}
