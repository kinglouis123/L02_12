package com.stapp.school;



import java.util.ArrayList;

/**
 * Created by wenboma on 2017-11-18.
 */

public class Question {

    private int id;

    public Question(int id){this.id = id;}

    public String getQuestionString() {
        if (this.id == 1)
            return "how?";
        else
            return "What?";
    }

    public ArrayList<String> getChoices() {

        if (this.id == 1L){
            ArrayList<String> cc = new ArrayList();
            cc.add("apple");
            cc.add("peach");
            return cc;
        }else{
            return new ArrayList<String>();
        }

    }

    public String getAnswer() {

        if (this.id == 1){
            return "apple!";
        }else{
            return "Eurika!";
        }
    }
}
