package com.stapp.school;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;
/**
 * Created by wenboma on 2017-11-18.
 */
public class AssignmentTest {

    private int id = 1;
    private String assignmentname;
    private String due = "2017-11-30";
    private String coursename = "cscc01h3";
    private Assignment assignfal;
    private Assignment assignlong;

    @Before
    public void setUp() throws Exception {
        assignmentname = "a01";
        assignlong = new Assignment(assignmentname,due,coursename);
        assignfal = new Assignment("A01","2017-09-01","ENGA01H3");
    }


    @Test
    public void isValidAssignment() throws Exception {
        assertTrue(assignlong.isValidAssignment());
        assertFalse(assignfal.isValidAssignment());
    }

    @Test
    public void insertMultipleChoiceQuestion() throws Exception {
        List<String> choices = new ArrayList<String>();
        choices.add("apple");
        choices.add("peach");
        Question pick = assignlong.insertMultipleChoiceQuestion("how?",choices,2);
       assertTrue(pick.getChoices().get(0).equals("apple"));
    }

    @Test
    public void getDueDate() throws Exception {
        assertEquals(assignlong.getDueDate(),"2017-11-30");
    }

    @Test
    public void getAssignmentName() throws Exception {
        assertEquals(assignlong.getAssignmentName(),"a01");
    }

    @Test
    public void getQuestions() throws Exception {
        List <Question> q = assignlong.getQuestions();
        assertEquals(q.get(0).getChoices().get(0),"apple");
    }

    @Test
    public void isReleased() throws Exception {
        assertTrue(assignlong.isReleased());
    }

    @Test
    public void release() throws Exception {
        assertTrue(assignlong.release());
    }

    @Test
    public void getId() throws Exception {

        assertEquals(assignlong.getId(),567);
        assertFalse(assignlong.getId() == 2);
    }

    @Test
    public void withinDueDate() throws Exception {
        assertTrue(assignlong.withinDueDate());
    }

}