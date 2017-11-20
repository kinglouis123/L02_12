package com.stapp.terminals;

import android.support.test.runner.AndroidJUnit4;

import com.stapp.school.Assignment;
import com.stapp.school.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by jr on 18/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class AssignmentTerminalTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Random string generator.
     * obtained from: https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
     */
    private String generateRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    @Test
    public void createNewAssignment_duplicateName() throws Exception {
        String name = generateRandomString();
        String due = "1999-12-28";
        String course = generateRandomString();
        Assignment assignment = AssignmentTerminal.createNewAssignment(name, due, course);
        // Attempt to create an assignment with the same name
        Assignment assignment2 = AssignmentTerminal.createNewAssignment(name, due, course);
        assertNull(assignment2);
    }

    @Test
    public void createNewAssignment_existingCourse() throws Exception {
        // Insert prof and course first
        String username = generateRandomString();
        String name = generateRandomString();
        String pass = generateRandomString();
        LoginTerminal.newProfessor(username, name, pass);

        String courseName = generateRandomString();
        Course course = CourseTerminal.createNewCourse(courseName, username);

        // Check that inserting an assignment works
        String assignmentName = generateRandomString();
        String due = "2019-12-28";
        Assignment assignment = AssignmentTerminal.createNewAssignment(name, due, courseName);

        boolean correctName = name.equals(assignment.getAssignmentName());
        boolean correctDue = due.equals(assignment.getDueDate());
        assertTrue(correctDue && correctName);
    }

    @Test
    public void getAssignment_nonExistent() throws Exception {
        // Try to get an assignment
        Assignment assignment = AssignmentTerminal.getAssignment(-12);
        assertNull(assignment);
    }

    @Test
    public void getAssignment_existent() throws Exception {
        // Insert prof and course first
        String username = generateRandomString();
        String name = generateRandomString();
        String pass = generateRandomString();
        LoginTerminal.newProfessor(username, name, pass);

        String courseName = generateRandomString();
        Course course = CourseTerminal.createNewCourse(courseName, username);

        // Check that inserting an assignment works
        String assignmentName = generateRandomString();
        String due = "2020-12-28";
        Assignment a = AssignmentTerminal.createNewAssignment(assignmentName, due, courseName);
        int id = a.getId();

        // Try to get an assignment
        Assignment b = AssignmentTerminal.getAssignment(id);
        assertTrue(b.getAssignmentName().equals(assignmentName) && b.getDueDate().equals(due));

    }
}