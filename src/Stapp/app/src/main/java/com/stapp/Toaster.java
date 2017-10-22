package com.stapp;

import android.widget.Toast;

/**
 * Created by rahulkumar1998 on 2017-10-21.
 */

public class Toaster {

  public static void toastShort(String message) {
    Toast.makeText(null, message, Toast.LENGTH_SHORT).show();
  }

  public static void toastLong(String message) {
    Toast.makeText(null, message, Toast.LENGTH_LONG).show();
  }

}
