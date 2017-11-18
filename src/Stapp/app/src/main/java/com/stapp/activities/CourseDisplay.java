package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.stapp.R;

public class CourseDisplay extends AppCompatActivity {
    
    String course_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the course_title from the Intent
        Intent intent = getIntent();
        course_code = intent.getStringExtra("course code");


        //set the course_title TextView
        TextView title = new TextView(this);
        title = (TextView)findViewById(R.id.course_title);
        title.setText(course_code);
    }

    public void showNewAssignment(View view){
        //for the new assignment button onClick
        Intent intent = new Intent(this, CreateNewAssignment.class);
        intent.putExtra("course_code", course_code);
	
	//start the new Activity
	startActivity(intent); 
    }

    public void showOldAssignments(View view){
        //for the old Assignments button onClick
        Intent intent = new Intent(this,ShowOldAssignments.class);
    }

}
