package com.stapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stapp.database.InitializeDatabase;
import com.stapp.databasehelpers.UserHelper;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InitializeDatabase.initializeDatabase();
    String roleName = "STUDENT";
    int role1 = UserHelper.getRoleIdGivenRoleName(roleName);
    Toaster.toastShort("ID of a " + roleName + " is: " + role1);
    setContentView(R.layout.activity_main);
  }
}
