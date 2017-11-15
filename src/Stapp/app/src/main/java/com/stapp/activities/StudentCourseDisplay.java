package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.stapp.Other.RVAssignmentAdapter;
import com.stapp.R;
import com.stapp.school.Assignment;
import com.stapp.school.Course;
import com.stapp.terminals.CourseTerminal;

import java.util.List;


public class StudentCourseDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        String course_code = intent.getStringExtra("course code");


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

}
