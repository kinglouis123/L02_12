package com.stapp.activities;

import java.util.ArrayList;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.stapp.databasehelpers.ClassHelper;
import com.stapp.exceptions.StudentAlreadyExistsException;
import com.stapp.exceptions.UserNotFoundException;


/**
 * Created by wenboma on 04/11/2017.
 */

public class classes {

    String coursecode;

    public classes(String coursecode){
        this.coursecode = coursecode;
    }
    public String getProf(){
        try{
            return ClassHelper.getProfUsername(this.coursecode);
        }catch(ClassNotFoundException e){
            return "Class not Found";
        }
    }

    public void addstudent(String studentid) throws StudentAlreadyExistsException, ClassNotFoundException{

            ClassHelper.insertStudentToClass(this.coursecode, studentid);

    }

    public ArrayList<String> getstudentlist(){

        try {
            ArrayList<String> studentlist = ClassHelper.getStudentUsernames(this.coursecode);
            return studentlist;
        }catch (ClassNotFoundException E){
            return null;
        }
    }

    public void removestudent(String studentname){
        try {
            ClassHelper.removeStudentFromClass(this.coursecode, studentname);
        }catch (UserNotFoundException e){

        }
    }
}
