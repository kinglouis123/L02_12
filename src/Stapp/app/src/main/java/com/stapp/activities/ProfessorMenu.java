package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stapp.Other.RVCourseAdapter;
import com.stapp.R;
import com.stapp.school.Course;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Professor;

import java.util.List;

public class ProfessorMenu extends AppCompatActivity {
    private Professor professor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_menu);

        // Login the professor with passed in intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        this.professor = LoginTerminal.getProfessor(username, password);



        // Recycler for displaying all courses
        RecyclerView coursesRecycler =(RecyclerView)findViewById(R.id.prof_course_list);

        //layout manager for Recycler
        LinearLayoutManager llm = new LinearLayoutManager(this);
        coursesRecycler.setLayoutManager(llm);

        // Generate list of classes
        List<Course> courses = professor.getCourses();

        //Adapter to populate Recycler with courses
        RVCourseAdapter adapter = new RVCourseAdapter(courses);
        coursesRecycler.setAdapter(adapter);


    }

    protected void createClassActivity(View view) {
        // Go to the create class activity
        Intent intent = new Intent(this, CreateClass.class);
        intent.putExtra("username", this.professor.getUsername());
        intent.putExtra("password", this.professor.getPassword());
        startActivity(intent);
    }

    //Courses List OnClick Navigation
    protected void showCourseActivity(View view) {
        Intent intent = new Intent (this, CourseDisplay.class);
        String course_code = new String();
        TextView tv = findViewById(R.id.course_code);
        course_code = tv.getText().toString();
        intent.putExtra("course code",view.toString());
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(this.getIntent());
    }

}








