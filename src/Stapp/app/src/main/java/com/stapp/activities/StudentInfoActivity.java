package com.stapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.stapp.R;
import com.stapp.other.RVInfoAdapter;
import com.stapp.school.Assignment;
import com.stapp.school.Course;
import com.stapp.school.StudentSubmission;
import com.stapp.terminals.CourseTerminal;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentInfoActivity extends AppCompatActivity {

    private List<StudentSubmission> submissions;
    String student_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        Intent intent = getIntent();
        student_username = intent.getStringExtra("student_username");

        Course course = CourseTerminal.getCourse(intent.getStringExtra("course_code"));
        List<Assignment> assignments = course.getAssignments();

        for (Assignment assignment:assignments){
            submissions.add(new StudentSubmission(student_username,assignment.getId()));
        }
        String student_name = intent.getStringExtra("student_name");

        // Recycler for displaying all courses
        RecyclerView InfoRecycler = (RecyclerView) findViewById(R.id.info_recycler);

        // layout manager for Recycler
        LinearLayoutManager llm = new LinearLayoutManager(this);
        InfoRecycler.setLayoutManager(llm);

        // Adapter to populate Recycler with Student Names
        RVInfoAdapter adapter = new RVInfoAdapter(submissions);
        InfoRecycler.setAdapter(adapter);



    }
}
