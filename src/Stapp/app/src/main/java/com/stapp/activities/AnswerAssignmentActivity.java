package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.stapp.R;
import com.stapp.Toaster;
import com.stapp.school.Assignment;
import com.stapp.school.StudentSubmission;
import com.stapp.terminals.AssignmentTerminal;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Student;

public class AnswerAssignmentActivity extends AppCompatActivity {
    private StudentSubmission submission = null;
    private boolean isFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_assignment);

        // Get info for student submission object
        Intent intent = getIntent();
        String studentUsername = intent.getStringExtra("username");
        int assignmentId = intent.getIntExtra("assignment_id", -1);

        if (assignmentId == -1) {
            Toaster.toastShort("Invalid assignment!");
            finish();
        }

        // Get submission object to be used for answering questions
        this.submission = new StudentSubmission(intent.getStringExtra("username"),
                intent.getIntExtra("assignment_id", -1));
    }

    // Cycle to the next question, currently students can't go back and change answers
    protected void answerCurrentQuestion(View view) {
        // TODO
        // Call AssignmentResultsActivity after finishing questions, pass in results through intent
    }
}
