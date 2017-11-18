package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.stapp.databasehelpers.AssignmentHelper;
import com.stapp.school.Assignment;
import com.stapp.terminals.AssignmentTerminal;

import com.stapp.R;
import android.widget.EditText;

public class CreateNewAssignment extends AppCompatActivity {
    private String course_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_assignment);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		Intent intent = getIntent();
		course_code = intent.getStringExtra("course_code");


	}

    protected void showAddQuestions(View view){
	Intent intent = new Intent(this, AddQuestionsActivity.class);

	//get the assignment info from the edit texts
	String year;
	String month;
	String day;
	String name;
	Assignment assignment;
	
	name = ((EditText)findViewById(R.id.CreateAssignmentName)).getText().toString();
	year = ((EditText)findViewById(R.id.CreateAssignmentDueDateYear)).getText().toString();
	month = ((EditText)findViewById(R.id.CreateAssignmentDueDateMonth)).getText().toString();
	day = ((EditText)findViewById(R.id.CreateAssignmentDueDateDay)).getText().toString();

	//create the duedate string format "yyyy-mm-dd"
	
	String duedate;
	duedate = year.substring(0, 3)+"-"+month.substring(0,1)+"-"+day.substring(0,1);

	//create the new Assignment
	assignment = AssignmentTerminal.createNewAssignment(name, duedate, course_code);
	
	//get the assignment id for the intent. 
	int assignment_id = AssignmentHelper.getAssignmentId(name, course_code);
	intent.putExtra("assignment_id", assignment_id);
	
	//start the new activity
	startActivity(intent);

	
    }

}
