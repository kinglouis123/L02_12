package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.stapp.R;
import com.stapp.Toaster;
import com.stapp.school.Assignment;
import com.stapp.terminals.AssignmentTerminal;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionsActivity extends AppCompatActivity {

    String question, answer1, answer2, answer3, answer4;
    int assignment_id, correct_index;
    Assignment assignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        Intent intent = getIntent();
        assignment_id = intent.getIntExtra("assignment_id", 0);

    }

    protected void AddNewQuestions(View view) {

        RadioGroup rGroup = (RadioGroup) findViewById(R.id.AddQuestions_radioGroup);


        question = ((EditText) findViewById(R.id.Question_string)).getText().toString();
        answer1 = ((EditText) findViewById(R.id.AddQuestions_editText1)).getText().toString();
        answer2 = ((EditText) findViewById(R.id.AddQuestions_editText2)).getText().toString();
        answer3 = ((EditText) findViewById(R.id.AddQuestions_editText3)).getText().toString();
        answer4 = ((EditText) findViewById(R.id.AddQuestions_editText4)).getText().toString();

        // Check if a radio button is pressed and also all the answers are entered
        if (rGroup.getCheckedRadioButtonId() == -1 || answer1.matches("") || answer2.matches("") ||
                answer3.matches("") || answer4.matches("")) {

            Toaster.toastShort("Please fill in the question/answer box and check the correct answer");

        } else {

            switch (rGroup.getCheckedRadioButtonId()) {

                case R.id.AddQuestions_radioButton1:
                        correct_index = 0;
                    break;

                case R.id.AddQuestions_radioButton2:
                        correct_index = 1;
                    break;

                case R.id.AddQuestions_radioButton3:
                        correct_index = 2;
                    break;

                case R.id.AddQuestions_radioButton4:
                        correct_index = 3;
                    break;

            }
            List<String> ChoiceList = new ArrayList<>();
            ChoiceList.add(answer1);
            ChoiceList.add(answer2);
            ChoiceList.add(answer3);
            ChoiceList.add(answer4);
            assignment = AssignmentTerminal.getAssignment(assignment_id);

            assignment.insertMultipleChoiceQuestion(question, ChoiceList, correct_index);

            Integer numberOfQuestions = assignment.getNumberOfQuestions();

            Toaster.toastShort("Question successfully created!\n Number of Question(s): " + numberOfQuestions.toString());

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    protected void Finish(View view) {
        finish();
    }


}
