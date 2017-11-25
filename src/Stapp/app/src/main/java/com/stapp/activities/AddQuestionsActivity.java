package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.stapp.R;
import com.stapp.other.SimpleRandomSampling;
import com.stapp.other.Toaster;
import com.stapp.school.Assignment;
import com.stapp.terminals.AssignmentTerminal;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionsActivity extends AppCompatActivity {
    // Android elements
    private EditText questionEdit;
    private EditText answer1Edit;
    private EditText answer2Edit;
    private EditText answer3Edit;
    private EditText answer4Edit;
    private RadioGroup rGroup;

    private int assignment_id, correct_index;
    private Assignment assignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        Intent intent = getIntent();
        assignment_id = intent.getIntExtra("assignment_id", 0);

        // Link Android elements
        questionEdit = findViewById(R.id.Question_string);
        answer1Edit = findViewById(R.id.AddQuestions_editText1);
        answer2Edit = findViewById(R.id.AddQuestions_editText2);
        answer3Edit = findViewById(R.id.AddQuestions_editText3);
        answer4Edit = findViewById(R.id.AddQuestions_editText4);
        rGroup = findViewById(R.id.AddQuestions_radioGroup);
    }

    protected void AddNewQuestions(View view) {
            addThisQuestion();

            Intent intent = getIntent();
            finish();
            startActivity(intent);
    }

    /**
     * Clears all the fields in the create question activity.
     * @param view
     */
    protected void clearFields(View view) {
        questionEdit.setText("");
        answer1Edit.setText("");
        answer2Edit.setText("");
        answer3Edit.setText("");
        answer4Edit.setText("");
        rGroup.clearCheck();
    }

    private boolean addThisQuestion() {
        String question = questionEdit.getText().toString();
        String answer1 = answer1Edit.getText().toString();
        String answer2 = answer2Edit.getText().toString();
        String answer3 = answer3Edit.getText().toString();
        String answer4 = answer4Edit.getText().toString();

        // Check if a radio button is pressed and also all the answers are entered
        if (rGroup.getCheckedRadioButtonId() == -1 || answer1.matches("") || answer2.matches("") ||
            answer3.matches("") || answer4.matches("")) {
            Toaster.toastShort("Please fill in the question/answer box and check the correct answer");
            return false;

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
            return true;

        }
    }

    protected void Finish(View view) {
        if (addThisQuestion()) {
            finish();
        }
    }


}
