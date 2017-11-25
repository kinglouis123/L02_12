package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.stapp.other.RVAssignmentAdapter;
import com.stapp.R;
import com.stapp.school.Assignment;
import com.stapp.school.Course;
import com.stapp.terminals.CourseTerminal;

import java.util.List;

public class ShowOldAssignments extends AppCompatActivity {

    private String courseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_old_assignments);

        Intent intent = getIntent();
        courseCode = intent.getStringExtra("course code");

        // Recycler for displaying all courses
        RecyclerView assignmentRecycler =(RecyclerView)findViewById(R.id.old_assignments_list);

        //layout manager for Recycler
        LinearLayoutManager llm = new LinearLayoutManager(this);
        assignmentRecycler.setLayoutManager(llm);

        Course course = CourseTerminal.getCourse(courseCode);

        // Generate list of classes
        List<Assignment> assignments = course.getAssignments();

        //Adapter to populate Recycler with courses
        RVAssignmentAdapter adapter = new RVAssignmentAdapter(assignments);
        assignmentRecycler.setAdapter(adapter);


    }

}
