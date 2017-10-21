package com.stapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.widget.Toast;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;
import com.stapp.database.InitializeDatabase;

import java.io.File;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InitializeDatabase.initializeDatabase();
    setContentView(R.layout.activity_main);
  }
}
