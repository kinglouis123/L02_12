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
import com.stapp.school.Question;
import com.stapp.school.StudentSubmission;
import com.stapp.terminals.StudentSubmissionTerminal;

import java.util.List;

public class AnswerAssignmentActivity extends AppCompatActivity {
    private StudentSubmission submission = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_assignment);

        // Get info for student submission object
        Intent intent = getIntent();
        String studentUsername = intent.getStringExtra("username");
        int assignmentId = intent.getIntExtra("assignment id", -1);

        if (assignmentId == -1) {
            Toaster.toastShort("Invalid assignment!");
            finish();
        }

        // Get submission object to be used for answering questions, start assignment
        this.submission = StudentSubmissionTerminal.startNewSubmission(studentUsername, assignmentId);
        if (this.submission == null) {
            Toaster.toastShort("Sorry, the due date for this assignment has passed..");
            finish();
        }
        this.displayNextQuestion(this.submission.getNextQuestion());
    }

    /**
     * Displays the next question on the activity screen.
     * @param nextQuestion question to be displayed
     */
    protected void displayNextQuestion(Question nextQuestion) {
        // Display question
        String questionText = nextQuestion.getQuestionString();
        TextView questionView = findViewById(R.id.questionText);
        questionView.setText(questionText);

        // Display answers
        RadioGroup answerGroup = findViewById(R.id.answerGroup);
        // Add all answers
        List<String> answers = nextQuestion.getChoices();
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
            boolean success = this.submission.submitAssignment();
            if (!success) {
                Toaster.toastShort("Sorry, the due date for this assignment has passed..");
                finish();
            }
            Intent intent = new Intent(this, AssignmentResultsActivity.class);
            intent.putExtra("marks", this.submission.getCurrentMark());
            finish();
            startActivity(intent);
        }

        // Get answer from radio group and submit the answer
        RadioGroup choices = findViewById(R.id.answerGroup);
        int selectedId = choices.getCheckedRadioButtonId();
        RadioButton selectedButton = findViewById(selectedId);
        String answer = selectedButton.getText().toString();
        this.submission.answerCurrentQuestion(answer);

        // Clear choices and display next question
        choices.removeAllViews();
        this.displayNextQuestion(nextQuestion);
    }
}
