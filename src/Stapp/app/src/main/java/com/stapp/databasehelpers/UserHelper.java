package com.stapp.databasehelpers;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;
import com.stapp.exceptions.InvalidRoleException;
import com.stapp.exceptions.RoleAlreadyExistsException;
import com.stapp.exceptions.UserAlreadyExistsException;
import com.stapp.exceptions.UserNotFoundException;

/**
 * Created by rahulkumar1998 on 2017-10-21.
 */

public class UserHelper {

  private static DatabaseDriver databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());

  // INSERTERS

  public static long insertUser(String username, String name, String password, int role) throws UserAlreadyExistsException, InvalidRoleException {
    if (databaseDriver.userExists(username)) {
      throw new UserAlreadyExistsException();
    }
    if (databaseDriver.roleExists(role)) {
      return databaseDriver.insertUser(username, name, password, role);
    }
    throw new InvalidRoleException();
  }

  public static long insertRole(String roleName) throws RoleAlreadyExistsException {
    if (databaseDriver.roleExists(roleName)) {
      throw new RoleAlreadyExistsException();
    }
    return databaseDriver.insertRole(roleName);
  }

  // UPDATERS

  public static boolean updatePassword(String password, String username) throws UserNotFoundException {
    if (databaseDriver.userExists(username)) {
      return databaseDriver.updatePassword(password, username);
    }
    throw new UserNotFoundException();
  }

  public static boolean updateName(String name, String username) throws UserNotFoundException {
    if (databaseDriver.userExists(username)) {
      return databaseDriver.updateName(name, username);
    }
    throw new UserNotFoundException();
  }

  public static boolean updateRole(String roleName, String username) throws InvalidRoleException, UserNotFoundException {
    if (databaseDriver.userExists(username)) {
      if (databaseDriver.roleExists(roleName)) {
        return databaseDriver.updateRole(databaseDriver.getRoleIdGivenRoleName(roleName), username);
      }
      throw new InvalidRoleException();
    }
    throw new UserNotFoundException();
  }

  // SELECTORS

  public static String getRoleNameGivenUsername(String username) throws UserNotFoundException {
    if (databaseDriver.userExists(username)) {
      return databaseDriver.getRoleNameGivenUsername(username);
    }
    throw new UserNotFoundException();
  }

  public static int getRoleIdGivenRoleName(String roleName) throws InvalidRoleException {
    if (databaseDriver.roleExists(roleName)) {
      return databaseDriver.getRoleIdGivenRoleName(roleName);
    }
    throw new InvalidRoleException();
  }

  public static int getRoleIdGivenUsername(String username) throws UserNotFoundException {
    if (databaseDriver.userExists(username)) {
      return databaseDriver.getRoleIdGivenUsername(username);
    }
    throw new UserNotFoundException();
  }

  public static int getId(String username) throws UserNotFoundException {
    if (databaseDriver.userExists(username)) {
      return databaseDriver.getId(username);
    }
    throw new UserNotFoundException();
  }

  public static String getName(String username) throws UserNotFoundException {
    if (databaseDriver.userExists(username)) {
      return databaseDriver.getName(username);
    }
    throw new UserNotFoundException();
  }

  public static String getPassword(String username) throws UserNotFoundException {
    if (databaseDriver.userExists(username)) {
      return databaseDriver.getPassword(username);
    }
    throw new UserNotFoundException();
  }

}
