package com.stapp.databasehelpers;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;
import com.stapp.exceptions.RoleAlreadyExistsException;
import com.stapp.exceptions.UserAlreadyExistsException;
import com.stapp.exceptions.UserNotFoundException;

/** Created by rahulkumar1998 on 2017-10-21. */
public class UserHelper {

  private static DatabaseDriver databaseDriver;

  private static void openDatabase() {
    if (databaseDriver == null) {
      databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());
    }
  }

  private static void closeDatabase() {
    databaseDriver.close();
    databaseDriver = null;
  }

  public static boolean userExists(String username) {
    openDatabase();
    boolean result = databaseDriver.userExists(username);
    closeDatabase();
    return result;
  }

  // INSERTERS

  public static long insertUser(String username, String name, String password, int role)
      throws UserAlreadyExistsException {
    openDatabase();
    if (databaseDriver.userExists(username)) {
      closeDatabase();
      throw new UserAlreadyExistsException();
    }
    if (databaseDriver.roleExists(role)) {
      long id = databaseDriver.insertUser(username, name, password, role);
      closeDatabase();
      return id;
    }
    closeDatabase();
    return -1;
  }

  public static long insertRole(String roleName) throws RoleAlreadyExistsException {
    openDatabase();
    if (databaseDriver.roleExists(roleName)) {
      closeDatabase();
      throw new RoleAlreadyExistsException();
    }
    long role = databaseDriver.insertRole(roleName);
    closeDatabase();
    return role;
  }

  // UPDATERS

  public static boolean updatePassword(String password, String username)
      throws UserNotFoundException {
    openDatabase();
    if (databaseDriver.userExists(username)) {
      boolean updated = databaseDriver.updatePassword(password, username);
      closeDatabase();
      return updated;
    }
    closeDatabase();
    throw new UserNotFoundException();
  }

  public static boolean updateName(String name, String username) throws UserNotFoundException {
    openDatabase();
    if (databaseDriver.userExists(username)) {
      boolean updated = databaseDriver.updateName(name, username);
      closeDatabase();
      return updated;
    }
    closeDatabase();
    throw new UserNotFoundException();
  }

  public static boolean updateRole(String roleName, String username) throws UserNotFoundException {
    openDatabase();
    if (databaseDriver.userExists(username)) {
      if (databaseDriver.roleExists(roleName)) {
        boolean updated =
            databaseDriver.updateRole(databaseDriver.getRoleIdGivenRoleName(roleName), username);
        closeDatabase();
        return updated;
      }
      closeDatabase();
      return false;
    }
    closeDatabase();
    throw new UserNotFoundException();
  }

  // SELECTORS

  public static String getRoleNameGivenUsername(String username) throws UserNotFoundException {
    openDatabase();
    if (databaseDriver.userExists(username)) {
      String role = databaseDriver.getRoleNameGivenUsername(username);
      closeDatabase();
      return role;
    }
    closeDatabase();
    throw new UserNotFoundException();
  }

  public static int getRoleIdGivenRoleName(String roleName) {
    openDatabase();
    if (databaseDriver.roleExists(roleName)) {
      int roleId = databaseDriver.getRoleIdGivenRoleName(roleName);
      closeDatabase();
      return roleId;
    }
    closeDatabase();
    return -1;
  }

  public static int getRoleIdGivenUsername(String username) throws UserNotFoundException {
    openDatabase();
    if (databaseDriver.userExists(username)) {
      int roleId = databaseDriver.getRoleIdGivenUsername(username);
      closeDatabase();
      return roleId;
    }
    closeDatabase();
    throw new UserNotFoundException();
  }

  public static int getId(String username) throws UserNotFoundException {
    openDatabase();
    if (databaseDriver.userExists(username)) {
      int id = databaseDriver.getId(username);
      closeDatabase();
      return id;
    }
    closeDatabase();
    throw new UserNotFoundException();
  }

  public static String getName(String username) throws UserNotFoundException {
    openDatabase();
    if (databaseDriver.userExists(username)) {
      String name = databaseDriver.getName(username);
      closeDatabase();
      return name;
    }
    closeDatabase();
    throw new UserNotFoundException();
  }

  public static String getPassword(String username) throws UserNotFoundException {
    openDatabase();
    if (databaseDriver.userExists(username)) {
      String password = databaseDriver.getPassword(username);
      closeDatabase();
      return password;
    }
    closeDatabase();
    throw new UserNotFoundException();
  }
}
