package com.stapp.school;

import com.stapp.databasehelpers.QuestionHelper;

import java.util.ArrayList;

/** Created by rahulkumar1998 on 2017-11-05. */
public class Question {

  private int Id;

  public Question(int Id) {
    this.Id = Id;
  }

  public String getQuestionString() {
    return QuestionHelper.getQuestionString(Id);
  }

  public ArrayList<String> getChoices() {
    return QuestionHelper.getChoices(Id);
  }

  public String getAnswer() {
    return QuestionHelper.getAnswer(Id);
  }

  public int getId() {
    return Id;
  }
}
