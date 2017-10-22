package com.stapp.databasehelpers;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;
import com.stapp.exceptions.UserAlreadyExistsException;

/**
 * Created by rahulkumar1998 on 2017-10-21.
 */

public class UserHelper {

  private static DatabaseDriver databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());

  public static long insertUser(String username, String name, String password, int role) throws UserAlreadyExistsException {
    if (databaseDriver.userExists(username)) {
      throw new UserAlreadyExistsException();
    }
    return databaseDriver.insertUser(username, name, password, role);
  }
}
