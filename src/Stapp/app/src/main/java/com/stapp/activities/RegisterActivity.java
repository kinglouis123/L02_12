package com.stapp.activities;

import android.os.Bundle;
import android.provider.MediaStore.Audio.Radio;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.stapp.R;
import com.stapp.Toaster;
import com.stapp.databasehelpers.UserHelper;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Professor;
import com.stapp.users.Student;

/**
 * Created by jr on 23/10/17.
 */

public class RegisterActivity extends AppCompatActivity {

  protected void registerUser(View view) {
    // Grab text from fields
    EditText username = (EditText) findViewById(R.id.RegisterEditTextUsername);
    EditText name = (EditText) findViewById(R.id.RegisterEditTextName);
    EditText password = (EditText) findViewById(R.id.RegisterEditTextPassword);
    EditText confirmPassword = (EditText) findViewById(R.id.RegisterEditTextConfirm);

    // TODO: error check username/name/pw for invalid characters

    // Confirm passwords are the same
    if (confirmPassword.getText().toString().equals(password.getText().toString())) {
      // Get user type from radio buttons
      RadioGroup userTypes = (RadioGroup) findViewById(R.id.RegisterRadioGroup);
      int userType = userTypes.getCheckedRadioButtonId();

      // Student
      if (userType == R.id.radioButton) {
        Student student = LoginTerminal.newStudent(username.getText().toString(),
            name.getText().toString(), password.getText().toString());

        if (student == null) {
          Toaster.toastShort("A student of the same username is the already registered!");
        }

        else {
          Toaster.toastShort("Successfully registered");
          finish();
        }
      }

      // Professor
      else if (userType == R.id.radioButton2) {
        Professor professor = LoginTerminal.newProfessor(username.getText().toString(),
            name.getText().toString(), password.getText().toString());

        if (professor == null) {
          Toaster.toastShort("A professor of the same username is the already registered!");
        }

        else {
          Toaster.toastShort("Successfully registered");
          finish();
        }
      }
    }

    else {
      Toaster.toastShort("Passwords are not the same!");
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

  }
}
