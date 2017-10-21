package com.stapp.database;

import android.app.Application;
import android.content.Context;

/**
 * Class used to get the Context of App.
 */

public class ContextHelper extends Application {

  private static Context context;

  @Override
  public void onCreate() {
    super.onCreate();
    ContextHelper.context = getApplicationContext();
  }

  public static Context getStappContext() {
    return ContextHelper.context;
  }
}
