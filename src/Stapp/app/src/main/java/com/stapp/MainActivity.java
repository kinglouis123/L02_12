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
    if (!(InitializeDatabase.initializeDatabase())) {
      Toast.makeText(this, ":^(", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(this, ":^)", Toast.LENGTH_SHORT).show();
    }
    try {
      long role1 = UserHelper.insertRole("STUDENT");
      Toast.makeText(this, Long.toString(role1), Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      Toast.makeText(this, ":^(", Toast.LENGTH_SHORT).show();
    }
    setContentView(R.layout.activity_main);
  }
}
