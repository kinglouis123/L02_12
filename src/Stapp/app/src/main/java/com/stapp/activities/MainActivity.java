package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.stapp.R;
import com.stapp.Toaster;
import com.stapp.activities.RegisterActivity;
import com.stapp.database.InitializeDatabase;
import com.stapp.databasehelpers.UserHelper;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Professor;
import com.stapp.users.Student;
import com.stapp.users.User;

public class MainActivity extends AppCompatActivity {

  Student user1 = LoginTerminal.newStudent("nick", "Nick Harrington", "nick");
  Professor prof1 = LoginTerminal.newProfessor("nick", "nicc", "nick");
  Student student1 = LoginTerminal.newStudent("nick", "nicccc", "nick2");
  Professor user2 = LoginTerminal.newProfessor("shierry", "Shierry Tans", "shierry");
  Student user3 = LoginTerminal.newStudent("john", "John Doe", "john");
  Professor user4 = LoginTerminal.newProfessor("Kohee", "Kohee Sang", "kohee");

  protected void showRegisterActivity(View view) {
    Intent intent = new Intent(this, RegisterActivity.class);
    startActivity(intent);
  }

  protected void loginUser(View view) {

    String username = ((EditText) findViewById(R.id.editTextUsernameSignIn)).getText().toString();
    String password = ((EditText) findViewById(R.id.editTextPasswordLogIn)).getText().toString();

    Student student;
    Professor professor;

    if ((professor = LoginTerminal.getProfessor(username, password)) == null) {
      if ((student = LoginTerminal.getStudent(username, password)) == null) {
        Toaster.toastShort("Login failed!");
      } else {
        //Toaster.toastShort("Welcome Student " + student.getName() + "!");
        //Toaster.toastShort("DATABASE ID: " + student.getId());
        // Start Student interface
        Intent student_intent = new Intent(this, StudentMenu.class);
        student_intent.putExtra("username", username);
        student_intent.putExtra("password", password);
        startActivity(student_intent);
      }
    } else {
      Toaster.toastShort("Welcome Professor " + professor.getName() + "!");
      Toaster.toastShort("DATABASE ID: " + professor.getId());
      // Start Professor interface
      Intent intent = new Intent(this, ProfessorMenu.class);
      intent.putExtra("username", username);
      intent.putExtra("password", password);
      startActivity(intent);
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InitializeDatabase.initializeDatabase();

    setContentView(R.layout.activity_log_in);

  }

  @Override
  public void onBackPressed() {
    Intent startMain = new Intent(Intent.ACTION_MAIN);
    startMain.addCategory(Intent.CATEGORY_HOME);
    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(startMain);
  }

}
