package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.stapp.R;
import com.stapp.other.RVStudentAdapter;
import com.stapp.school.Course;
import com.stapp.terminals.CourseTerminal;
import com.stapp.users.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseDisplay extends AppCompatActivity implements RVStudentAdapter.RecyclerViewClickListener {

  String course_code;
  Course course;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_course);

    // get the course_title from the Intent
    Intent intent = getIntent();
    course_code = intent.getStringExtra("course code");

    // set the course_title TextView
    TextView title = new TextView(this);
    title = (TextView) findViewById(R.id.course_title);
    title.setText("Course: " + course_code);

    //get the course
    course = CourseTerminal.getCourse(course_code);

    // Recycler for displaying all courses
    RecyclerView StudentRecycler = (RecyclerView) findViewById(R.id.students_recycler);

    // layout manager for Recycler
    LinearLayoutManager llm = new LinearLayoutManager(this);
    StudentRecycler.setLayoutManager(llm);

    // Generate list of students
    ArrayList<String> students = course.getStudentUsernames();

    // Adapter to populate Recycler with Student Names
    RVStudentAdapter adapter = new RVStudentAdapter(students, this);
    StudentRecycler.setAdapter(adapter);
  }

  public void showNewAssignment(View view) {
    // for the new assignment button onClick
    Intent intent = new Intent(this, CreateNewAssignment.class);
    intent.putExtra("course code", course_code);

    // start the new Activity
    startActivity(intent);
  }

  public void showOldAssignments(View view) {
    // for the old Assignments button onClick
    Intent intent = new Intent(this, ShowOldAssignments.class);
    intent.putExtra("course code", course_code);

    startActivity(intent);
  }

  public void DummyOnClick (View view){}
  public void onListItemClick(int clickedPosition) {
    // Toaster.toastShort("Item #" + clickedPosition + " clicked");
    Intent intent = new Intent(this, StudentInfoActivity.class);
    intent.putExtra("student_name", course.getStudentUsernames().get(clickedPosition));
    intent.putExtra("course_code", course_code);

    startActivity(intent);
  }
}
