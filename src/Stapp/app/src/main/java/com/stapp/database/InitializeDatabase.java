package com.stapp.database;

import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.RoleAlreadyExistsException;

/**
 * Created by rahulkumar1998 on 2017-10-21.
 */

public class InitializeDatabase {
  private static DatabaseDriver databaseDriver;

  /**
   * Initializes database.
   */
  public static boolean initializeDatabase() {
    databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());
    databaseDriver.close();
    return initializeRolesTable();
  }

  private static boolean initializeRolesTable() {
    try {
      UserHelper.insertRole("STUDENT");
      UserHelper.insertRole("PROFESSOR");
      return true;
    } catch (RoleAlreadyExistsException e) {
      return false;
    }
  }

}
