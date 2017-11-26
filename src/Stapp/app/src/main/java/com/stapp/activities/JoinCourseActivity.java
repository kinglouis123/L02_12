package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.stapp.R;
import com.stapp.exceptions.StudentAlreadyExistsException;
import com.stapp.other.Toaster;
import com.stapp.school.Course;
import com.stapp.terminals.CourseTerminal;

public class JoinCourseActivity extends AppCompatActivity {
  private String username = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_join_course);
    // get the username passed from the previous activity
    Intent intent = getIntent();
    username = intent.getStringExtra("username");
  }

  public void addStudentCourse(View view) {
    String coursecode = ((EditText) findViewById(R.id.course_code_edit)).getText().toString();
    Course course = CourseTerminal.getCourse(coursecode);

    try {
      if (course != null) {
        if (course.isValidCourse()) {
          course.addStudent(username);
        } else {
          Toaster.toastShort("Class does not exist.");
        }
      } else {
        Toaster.toastShort("Class does not exist.");
      }

    } catch (ClassNotFoundException err) {
      Toaster.toastShort("Class does not exist.");

    } catch (StudentAlreadyExistsException err) {
      Toaster.toastShort("You are already enrolled in that course.");
    }

    finish();
  }
}
