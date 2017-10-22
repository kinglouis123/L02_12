package com.stapp.database;

import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.RoleAlreadyExistsException;

/**
 * Created by rahulkumar1998 on 2017-10-21.
 */

public class InitializeDatabase {

  /**
   * Initializes database.
   */
  public static boolean initializeDatabase() {
    DatabaseDriverHelper.initializeDatabase();
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
