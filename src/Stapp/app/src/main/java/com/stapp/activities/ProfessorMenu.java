package com.stapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stapp.R;
import com.stapp.other.RVCourseAdapter;
import com.stapp.school.Course;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Professor;

import java.util.List;

public class ProfessorMenu extends AppCompatActivity
    implements RVCourseAdapter.RecyclerViewClickListener {
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
    RecyclerView coursesRecycler = (RecyclerView) findViewById(R.id.prof_course_list);

    // layout manager for Recycler
    LinearLayoutManager llm = new LinearLayoutManager(this);
    coursesRecycler.setLayoutManager(llm);

    // Generate list of classes
    List<Course> courses = professor.getCourses();

    // Adapter to populate Recycler with courses
    RVCourseAdapter adapter = new RVCourseAdapter(courses, this);
    coursesRecycler.setAdapter(adapter);
  }

  protected void createClassActivity(View view) {
    // Go to the create class activity
    Intent intent = new Intent(this, CreateClass.class);
    intent.putExtra("username", this.professor.getUsername());
    intent.putExtra("password", this.professor.getPassword());
    startActivity(intent);
  }

  // Courses List OnClick Navigation
  /*   protected void showCourseActivity(View view) {
      Intent intent = new Intent (this, CourseDisplay.class);
      String course_code;
      TextView tv = findViewById(R.id.course_code);
      course_code = tv.getText().toString();
      intent.putExtra("course code", course_code);
      Toaster.toastShort("The course code is: " + course_code);
      startActivity(intent);

  } */

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

  @Override
  public void onListItemClick(int clickedPosition) {
    // Toaster.toastShort("Item #" + clickedPosition + " clicked");
    Intent intent = new Intent(this, CourseDisplay.class);
    intent.putExtra("course code", professor.getCourses().get(clickedPosition).toString());
    startActivity(intent);
  }
}
