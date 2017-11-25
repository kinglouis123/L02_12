package com.stapp.school;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rahulkumar1998 on 2017-11-25.
 */

public class RandomQuestionFactory {

  private static HashMap<Object, Object> newQuestion(String questionString, ArrayList<String> choices, int index) {

    HashMap<Object, Object> question = new HashMap<>();

    question.put("QUESTION", "your question string");
    question.put("CHOICES", new String[]{"c1", "c2", "c3", "c4"});

    int answerIndex = -1;

    question.put("ANSWER", answerIndex);

    return question;
  }

  /**
   * Template for Wen.
   */
  public static HashMap<Object, Object> createNew___Question() {
    String question = "your question string";
    ArrayList<String> choices = new ArrayList<>();
    choices.add("c1");
    choices.add("c2");
    choices.add("c3");
    choices.add("c4");
    int index = -1;
    return newQuestion(question, choices, index);
  }

}
