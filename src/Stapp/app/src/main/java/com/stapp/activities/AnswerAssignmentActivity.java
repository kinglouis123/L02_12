package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.stapp.R;
import com.stapp.Toaster;
import com.stapp.school.Assignment;
import com.stapp.school.Question;
import com.stapp.school.StudentSubmission;
import com.stapp.terminals.AssignmentTerminal;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Student;

import java.util.List;

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

        // Get submission object to be used for answering questions, start assignment
        this.submission = new StudentSubmission(studentUsername, assignmentId);
        this.displayNextQuestion(this.submission.getNextQuestion());
    }

    /**
     * Displays the next question on the activity screen.
     * @param nextQuestion question to be displayed
     */
    protected void displayNextQuestion(Question nextQuestion) {
        Question currentQuestion = submission.getNextQuestion();
        // Display question
        String questionText = currentQuestion.getQuestionString();
        TextView questionView = findViewById(R.id.questionText);
        questionView.setText(questionText);

        // Display answers
        RadioGroup answerGroup = findViewById(R.id.answerGroup);
        // Add all answers
        List<String> answers = currentQuestion.getChoices();
        for (String answer : answers) {
            RadioButton choice = new RadioButton(this);
            choice.setText(answer);
            answerGroup.addView(choice);
        }
    }

    // Cycle to the next question, currently students can't go back and change answers
    protected void answerCurrentQuestion(View view) {
        Question nextQuestion = this.submission.getNextQuestion();
        // End of the assignment
        // Call AssignmentResultsActivity after finishing questions, pass in results through intent
        if (nextQuestion == null) {
            // Intent intent = new Intent(this, AssignmentResultsActivity);
            // intent.putExtra("marks", this.submission.getCurrentMark());
            // finish();
            // startActivity(intent);
        }

        // Clear choices and display next question
        RadioGroup choices = findViewById(R.id.answerGroup);
        choices.removeAllViews();
        this.displayNextQuestion(nextQuestion);
    }
}
