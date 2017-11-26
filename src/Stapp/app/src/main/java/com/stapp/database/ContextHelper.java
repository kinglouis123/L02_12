package com.stapp.database;

import android.app.Application;
import android.content.Context;

/** Class used to get the Context of App. */
public class ContextHelper extends Application {

  private static Context context;

  /** @return Context of app to be used to create database objects */
  public static Context getStappContext() {
    return ContextHelper.context;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    ContextHelper.context = getApplicationContext();
  }
}
