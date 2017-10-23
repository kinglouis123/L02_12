package com.stapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stapp.database.InitializeDatabase;
import com.stapp.users.Student;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InitializeDatabase.initializeDatabase();
    Student student = new Student("usernameforstudent", "student", "pass4student");

      Toaster.toastShort(student.getName());
      Toaster.toastShort(student.getPassword());
      Toaster.toastShort(student.getRoleName());
      Toaster.toastShort(student.getId() + "");
      Toaster.toastShort(student.isLoggedIn() + "");

  }
}
