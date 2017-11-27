package com.stapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.stapp.R;
import com.stapp.database.DatabaseDriverHelper;
import com.stapp.database.InitializeDatabase;

public class AdminMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin_menu);
  }

  protected void resetDatabase(View view) {
    if (DatabaseDriverHelper.databaseExists()) {
      DatabaseDriverHelper.reinitializeDatabase();
      InitializeDatabase.initializeDatabase();
    }
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }
}
