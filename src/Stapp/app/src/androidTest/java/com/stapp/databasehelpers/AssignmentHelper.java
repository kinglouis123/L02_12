package com.stapp.databasehelpers;

import com.stapp.school.Assignment;
import com.stapp.school.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenboma on 2017-11-18.
 */

public class AssignmentHelper {

    public static int getAssignmentId(String assignmentName, String className) {

        if (className.equals("cscc01h3") && assignmentName.equals("a01")){
            return 567;
        }else{
            return 200;
        }

    }

    public static String getAssignmentDueDate(String assignmentName, String className) {
        return "2017-11-30";
    }


    public static boolean assignmentExists(String assignmentName, String courseName) {
        if (courseName.equals("cscc01h3"))
            return false;
        else
            return true;
    }

    public static boolean assignmentExists(int Id) {
        return true;
    }

    public static int createAssignment(String assignmentName, String due, String courseName) {
        return 1;
    }

    public static List<Question> getQuestions(int assignmentId) {
        Question q1 = new Question(1);
        Question q2 = new Question(2);
        ArrayList<Question> q = new ArrayList<Question>();
        q.add(q1);
        q.add(q2);
        return q;
    }

    /**
     * Insert a multiple choice question into the database
     * @param assignmentId
     * @param question the question to put
     * @param choices length <= 4, 4 choices only
     * @param correctIndex the index of the choice in choices that is the correct answer, < 4
     * @return id of the question
     */
    public static long insertMultipleChoiceQuestion(int assignmentId, String question, List<String> choices,
                                                    int correctIndex) {

        if (assignmentId == 567)
            return 1L;
        else
            return 2L;
    }

    public static boolean assignmentIsReleased(int assignmentId) {
        if (assignmentId == 567)
            return true;
        else
            return false;
    }

    public static boolean releaseAssignment(int assignmentId) {
       if (assignmentId == 567)
        return true;
       else
           return false;
    }

    public static ArrayList<Assignment> getAssignmentsOfStudent(String username) {
        Assignment as1 = new Assignment(1);
        Assignment as2 = new Assignment(2);
        ArrayList<Assignment> as = new ArrayList<Assignment>();
        as.add(as1);
        as.add(as2);
        return  as;
    }

    public static void submitAssignment(String username, int assignmentId, String grade, String time) {
        return;
    }

    public static String getGrade(String username, int assignmentId) {
        return "8/10";
    }

    /**
     * Returns a String representation of the current date in the appropriate YYYY-MM-DD format.
     */
    public static String getCurrentDate() {
        return "2017-11-30";
    }
}
