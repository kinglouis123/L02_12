package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.stapp.R;
import com.stapp.Toaster;
import com.stapp.database.DatabaseDriver;
import com.stapp.databasehelpers.CourseHelper;
import com.stapp.exceptions.StudentAlreadyExistsException;
import com.stapp.school.Course;
import com.stapp.terminals.CourseTerminal;
import com.stapp.users.Student;

public class JoinCourseActivity extends AppCompatActivity {
    private String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_course);
        //get the username passed from the previous activity
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
    }

    public void addStudentCourse(View view){
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

        }
        catch (ClassNotFoundException err){
            Toaster.toastShort("Class does not exist.");

        }
        catch (StudentAlreadyExistsException err){
            Toaster.toastShort("You are already enrolled in that course.");
        }

        finish();

    }

}
