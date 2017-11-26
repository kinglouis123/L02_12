package com.stapp.other;

import android.widget.Toast;

import com.stapp.database.ContextHelper;

/** Created by rahulkumar1998 on 2017-10-21. */
public class Toaster {

  public static void toastShort(String message) {
    Toast.makeText(ContextHelper.getStappContext(), message, Toast.LENGTH_SHORT).show();
  }

  public static void toastLong(String message) {
    Toast.makeText(ContextHelper.getStappContext(), message, Toast.LENGTH_LONG).show();
  }
}
