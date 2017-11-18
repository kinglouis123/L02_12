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

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.stapp", appContext.getPackageName());
    }

    @Test
    public void testInsertUser() throws Exception {
        db.insertUser("user", "name", "password", 1);
        assertEquals("name", db.getName("user"));
    }

}