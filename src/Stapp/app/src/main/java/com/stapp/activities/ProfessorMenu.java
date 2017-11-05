package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stapp.Other.RVAdapter;
import com.stapp.R;
import com.stapp.database.DatabaseDriver;
import com.stapp.school.Course;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Professor;
import com.stapp.database.DatabaseDriver;

import java.util.ArrayList;
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
        RVAdapter adapter = new RVAdapter(courses);
        coursesRecycler.setAdapter(adapter);


    }

    protected void createClassActivity(View view) {
        // Go to the create class activity
        Intent intent = new Intent(this, CreateClass.class);
        intent.putExtra("username", this.professor.getUsername());
        intent.putExtra("password", this.professor.getPassword());
        startActivity(intent);
    }

    //Coures List OnClick Navigation
        private AdapterView.OnItemClickListener courseListClickedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TO-DO fill in onclick


            }
        };

    }






