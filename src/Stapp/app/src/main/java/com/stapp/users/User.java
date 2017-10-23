package com.stapp.users;

import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.UserAlreadyExistsException;
import com.stapp.exceptions.UserNotFoundException;
import com.stapp.security.PasswordHelpers;

/**
 * Created by rahulkumar1998 on 2017-10-22.
 */

public class User {

  private String username;
  private boolean loggedIn = false;

  public static boolean exists(String username) {
    return UserHelper.userExists(username);
  }

  /**
   * USE THIS TO LOG IN AN EXISTING USER
   */
  public User(String username, String password) throws UserNotFoundException {
    this.username = username;
    login(password);
  }

  /**
   * USE THIS TO CREATE A NEW USER
   */
  public User(String username, String name, String password, int roleId) {
    this.username = username;
    try {
      UserHelper.insertUser(username, name, password, roleId);
      login(password);
    } catch (UserAlreadyExistsException | UserNotFoundException e) {
      // shouldn't happen
    }
  }

  private boolean login(String password) throws UserNotFoundException {
    String hashedPass = UserHelper.getPassword(username);
    loggedIn = PasswordHelpers.comparePassword(hashedPass, password);
    return loggedIn;
  }

}
