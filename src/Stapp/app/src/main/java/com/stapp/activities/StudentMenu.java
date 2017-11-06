package com.stapp.activities;

/**
 * Created by Harry Jiang on 2017-11-04.
 */

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.stapp.R;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Student;

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


}
