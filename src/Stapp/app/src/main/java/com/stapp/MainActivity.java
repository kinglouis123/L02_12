package com.stapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InitializeDatabase.initializeDatabase();

    setContentView(R.layout.activity_log_in);

    EditText usernameField = (EditText) findViewById(R.id.editTextUsernameSignIn);
    EditText passwordField = (EditText) findViewById(R.id.editTextPasswordLogIn);
    

  }
}
