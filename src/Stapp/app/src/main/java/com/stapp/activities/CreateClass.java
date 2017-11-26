package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.stapp.R;
import com.stapp.other.Toaster;
import com.stapp.terminals.CourseTerminal;

public class CreateClass extends AppCompatActivity {
  private String profUsername;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Saves professor username for class generation later
    Intent intent = getIntent();
    this.profUsername = intent.getStringExtra("username");

    setContentView(R.layout.activity_create_class);
  }

  protected void createClass(View view) {
    // Call class helper to register here
    EditText courseCode = (EditText) findViewById(R.id.CreateClassName);
    if (courseCode.getText() == null || courseCode.getText().equals("")) {
      Toaster.toastShort("Invalid course code!");
    }

    // Attempt to create course
    if (CourseTerminal.createNewCourse(courseCode.getText().toString(), this.profUsername)
        != null) {
      Toaster.toastLong("CourseDisplay successfully created!");
      finish();
    } else {
      Toaster.toastLong("CourseDisplay creation unsuccessful.");
      finish();
    }
  }
}
