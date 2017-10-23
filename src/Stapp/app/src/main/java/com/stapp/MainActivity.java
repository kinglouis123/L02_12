package com.stapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stapp.database.InitializeDatabase;
import com.stapp.databasehelpers.UserHelper;
import com.stapp.users.Professor;
import com.stapp.users.Student;

public class MainActivity extends AppCompatActivity {

  Student user1 = new Student("nick", "Nick Harrington", "nick");
  Professor user2 = new Professor("shierry", "Shierry Tans", "shierry");
  Student user3 = new Student("john", "John Doe", "john");
  Professor user4 = new Professor("Kohee", "Kohee Sang", "kohee");

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InitializeDatabase.initializeDatabase();
    String roleName = "STUDENT";
    int role1 = UserHelper.getRoleIdGivenRoleName(roleName);
    Toaster.toastShort("ID of a " + roleName + " is: " + role1);

    String user1_Name = user1.getName();
    String user1_Role = user1.getRoleName();
    String user2_Name = user2.getName();
    String user2_Role = user2.getRoleName();
    String user3_Name = user3.getName();
    String user3_Role = user3.getRoleName();
    String user4_Name = user4.getName();
    String user4_Role = user4.getRoleName();

    Toaster.toastLong("User1 Name: " + user1_Name + " User1 Role: " + user1_Role);
    Toaster.toastLong("User2 Name: " + user2_Name + " User2 Role: " + user2_Role);
    Toaster.toastLong("User3 Name: " + user3_Name + " User2 Role: " + user3_Role);
    Toaster.toastLong("User4 Name: " + user4_Name + " User2 Role: " + user4_Role);
    setContentView(R.layout.activity_main);
  }
}
