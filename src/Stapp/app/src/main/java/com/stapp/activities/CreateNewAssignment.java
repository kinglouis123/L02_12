package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.stapp.Toaster;
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

        //get the assignment info from the edit texts
        String year, month, day, name, duedate;
        Assignment assignment;

        name = ((EditText)findViewById(R.id.CreateAssignmentName)).getText().toString();
        year = ((EditText)findViewById(R.id.CreateAssignmentDueDateYear)).getText().toString();
        month = ((EditText)findViewById(R.id.CreateAssignmentDueDateMonth)).getText().toString();
        day = ((EditText)findViewById(R.id.CreateAssignmentDueDateDay)).getText().toString();

        //check if all the required fields are entered
        if (name.matches("") || year.matches("") ||
                month.matches("") || day.matches("")) {
            Toaster.toastShort("Please fill in all the required fields!");
            return;
        }

        //create the duedate string format "yyyy-mm-dd"
        duedate = year.substring(0, 3)+"-"+month.substring(0,1)+"-"+day.substring(0,1);
        assignment = AssignmentTerminal.createNewAssignment(name, duedate, course_code);
        if(assignment == null){
            Toaster.toastShort("Invalid due date or the assignment already exist!");
        } else {
            assignment = AssignmentTerminal.createNewAssignment(name, duedate, course_code);

            //get the assignment id for the intent.
            int assignment_id = assignment.getId();

            Intent addQuestions_intent = new Intent(this, AddQuestionsActivity.class);

            addQuestions_intent.putExtra("assignment_id", assignment_id);
            //start the new activity
            startActivity(addQuestions_intent);
        }
    }

	@Override
	public void onRestart() {
		super.onRestart();
		finish();
		startActivity(this.getIntent());
	}

}
