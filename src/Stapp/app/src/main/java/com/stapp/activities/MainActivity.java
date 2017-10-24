package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

  Student user0 = new Student("nick", "Nick Harrington", "nick");
  Student user1 = LoginTerminal.getStudent("nick", "nick");
  Professor user2 = new Professor("shierry", "Shierry Tans", "shierry");
  Student user3 = new Student("john", "John Doe", "john");
  Professor user4 = new Professor("Kohee", "Kohee Sang", "kohee");

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
        Toaster.toastShort("Welcome " + student.getName() + "!");
        // Start Student interface
      }
    } else {
      Toaster.toastShort("Welcome " + professor.getName() + "!");
      // Start Professor interface
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InitializeDatabase.initializeDatabase();

    setContentView(R.layout.activity_log_in);

  }
}
