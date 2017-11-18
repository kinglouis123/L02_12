package com.stapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.stapp.school.Assignment; 
import com.stapp.terminals.AssignmentTerminal;

import com.stapp.R;

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

       
        });

    protected void showAddQuestions(View view){
	Intent intent = new Intent(this, AddQuestions.class);

	//get the assignment info from the edit texts
	private String year;
	private String month;
	private String day;
	private String name;
	private Assignment assignment;
	
	name = findViewById(R.id.CreateAssignmentName).getText().toString();
	year = findViewById(R.id.CreateAssignmentDueDateYear).getText().toString();
	month = findViewById(R.id.CreateAssignmentDueDateMonth).getText().toString(); 
	day = findViewById(R.id.CreateAssignmentDueDateDay).getText().toString(); 

	//create the duedate string format "yyyy-mm-dd"
	
	private String duedate;
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
