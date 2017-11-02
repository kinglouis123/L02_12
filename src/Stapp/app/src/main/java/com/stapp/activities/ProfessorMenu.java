package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.stapp.R;
import com.stapp.terminals.LoginTerminal;
import com.stapp.users.Professor;

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
    }

    protected void createClassActivity(View view) {
        // Go to the create class activity
        Intent intent = new Intent(this, CreateClass.class);
        intent.putExtra("username", this.professor.getUsername());
        intent.putExtra("password", this.professor.getPassword());
        startActivity(intent);
    }
}
