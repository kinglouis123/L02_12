package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stapp.other.RVAssignmentAdapter;
import com.stapp.R;
import com.stapp.school.Assignment;
import com.stapp.school.Course;
import com.stapp.terminals.AssignmentTerminal;
import com.stapp.terminals.CourseTerminal;

import java.util.List;


public class StudentCourseDisplay extends AppCompatActivity {

    private String username;
    private String course_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_display);

        Intent intent = getIntent();

        course_code = intent.getStringExtra("course code");
        username = intent.getStringExtra("username");


        // Recycler for displaying all courses
        RecyclerView assignmentRecycler =(RecyclerView)findViewById(R.id.student_assignment_list);

        //layout manager for Recycler
        LinearLayoutManager llm = new LinearLayoutManager(this);
        assignmentRecycler.setLayoutManager(llm);

        // Get course
        Course course = CourseTerminal.getCourse(course_code);

        // Generate list of assignements
        List<Assignment> assignments = course.getAssignments();

        //Adapter to populate Recycler with courses
        RVAssignmentAdapter adapter = new RVAssignmentAdapter(assignments);
        assignmentRecycler.setAdapter(adapter);

    }

    protected void showAssignmentActivity(View view) {
        Intent intent = new Intent (this, AnswerAssignmentActivity.class);
        String assignment_title;
        TextView tv = findViewById(R.id.assignment_card_title);
        assignment_title = tv.getText().toString();
        int assignmentId = AssignmentTerminal.getAssignmentId(assignment_title, course_code);
        intent.putExtra("username", username);
        intent.putExtra("course code", course_code);
        intent.putExtra("assignment id", assignmentId);
        startActivity(intent);

    }

}
