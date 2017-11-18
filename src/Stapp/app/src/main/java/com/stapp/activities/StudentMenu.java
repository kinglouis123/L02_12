package com.stapp.activities;

/**
 * Created by Harry Jiang on 2017-11-04.
 */

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.stapp.Other.RVAdapter;
import com.stapp.R;
import com.stapp.school.Course;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Student;

import java.util.List;

public class StudentMenu extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    private Student student = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.student_drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        this.student = LoginTerminal.getStudent(username, password);
        String name = student.getName();

        TextView welcome = (TextView) findViewById(R.id.welcomeMessage);
        welcome.setText("Welcome " + name);

        // Recycler for displaying all courses
        RecyclerView coursesRecycler =(RecyclerView)findViewById(R.id.student_course_list);

        //layout manager for Recycler
        LinearLayoutManager llm = new LinearLayoutManager(this);
        coursesRecycler.setLayoutManager(llm);

        // Generate list of classes
        List<Course> courses = student.getCourses();

        //Adapter to populate Recycler with courses
        RVAdapter adapter = new RVAdapter(courses);
        coursesRecycler.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showJoinCourseActivity (View view){
        Intent intent = new Intent(this, JoinCourseActivity.class);
        intent.putExtra("username", student.getUsername());
        startActivity(intent);
    }

    //Courses List OnClick Navigation
    protected void showCourseActivity(View view) {
        Intent intent = new Intent (this, StudentCourseDisplay.class);
        String course_code;
        TextView tv = findViewById(R.id.course_code);
        course_code = tv.getText().toString();
        intent.putExtra("course code", course_code);
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
