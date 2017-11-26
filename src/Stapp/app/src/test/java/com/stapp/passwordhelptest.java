package com.stapp;

import com.stapp.security.PasswordHelpers;

import static org.junit.Assert.assertEquals;

/** Created by wenboma on 06/11/2017. */
public class passwordhelptest {

  public void passwordHastTest() {
    String passwords = PasswordHelpers.passwordHash("Hello");
    System.out.println(passwords);
    assertEquals(passwords, "185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969");
  }
}
