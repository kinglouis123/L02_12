package com.stapp.users;

import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.UserNotFoundException;

/**
 * Created by rahulkumar1998 on 2017-10-22.
 */

public class Professor extends User {

  public Professor(String username, String password) throws UserNotFoundException {
    super(username, password);
  }

  public Professor(String username, String name, String password) {
    super(username, name, password, UserHelper.getRoleIdGivenRoleName("PROFESSOR"));
  }

}
