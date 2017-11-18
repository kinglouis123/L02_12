package com.stapp.database;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by jr on 18/11/17.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseDriverTest {
    private DatabaseDriver db;
    @Before
    public void setUp() throws Exception {
        this.db = new DatabaseDriver(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        this.db.close();
    }

    // USER TESTS
    // INSERT
    // UPDATE
    // SELECT

    // COURSE TESTS
    // INSERT
    // UPDATE
    // SELECT

    // ASSIGNMENT TESTS
    // INSERT
    // UPDATE
    // SELECT

    // QUESTION TESTS
    // INSERT
    // SELECT

    // ASSIGNMENT SUBMISSION TESTS
    // INSERT
    // UPDATE
    // SELECT

    /*
    @Test
    public void testInsertUser() throws Exception {
        db.insertUser("user", "name", "password", 1);
        assertEquals("name", db.getName("user"));
    }
    */

}