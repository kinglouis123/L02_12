package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.stapp.R;

public class AssignmentResultsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_assignment_results);

    // Get results from the intent
    Intent intent = getIntent();
    String marks = intent.getStringExtra("marks");

    // Set text to marks
    TextView marksView = findViewById(R.id.resultsMarks);
    marksView.setText(marks);
  }

  protected void finishAssignment(View view) {
    finish();
  }
}
