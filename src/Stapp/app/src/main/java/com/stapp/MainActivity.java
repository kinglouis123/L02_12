package com.stapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.widget.Toast;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;
import com.stapp.database.InitializeDatabase;
import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.InvalidRoleException;

import java.io.File;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InitializeDatabase.initializeDatabase();
    try {
      String roleName = "STUDENT";
      int role1 = UserHelper.getRoleIdGivenRoleName(roleName);
      Toaster.toastShort("ID of a " + roleName + " is: " + role1);
    } catch (InvalidRoleException e) {
      Toaster.toastShort("Invalid Role");
    }
    setContentView(R.layout.activity_main);
  }
}
