package com.stapp.databasehelpers;

import com.stapp.database.ContextHelper;
import com.stapp.database.DatabaseDriver;

import java.util.ArrayList;

/** Created by rahulkumar1998 on 2017-11-05. */
public class QuestionHelper {

  private static DatabaseDriver databaseDriver;

  private static void openDatabase() {
    if (databaseDriver == null) {
      databaseDriver = new DatabaseDriver(ContextHelper.getStappContext());
    }
  }

  private static void closeDatabase() {
    databaseDriver.close();
    databaseDriver = null;
  }

  public static String getQuestionString(int id) {
    openDatabase();
    String question = databaseDriver.getQuestionString(id);
    closeDatabase();
    return question;
  }

  public static ArrayList<String> getChoices(int id) {
    openDatabase();
    ArrayList<String> choices = databaseDriver.getChoices(id);
    closeDatabase();
    return choices;
  }

  public static String getAnswer(int id) {
    openDatabase();
    String answer = databaseDriver.getAnswer(id);
    closeDatabase();
    return answer;
  }
}
