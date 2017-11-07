package com.stapp;

import com.stapp.databasehelpers.UserHelper;
import com.stapp.exceptions.UserAlreadyExistsException;

import static org.junit.Assert.*;

/**
 * Created by wenboma on 06/11/2017.
 */

public class UserHelperTest {


    public void testinsertuser() {
        try {
            long returned1 = UserHelper.insertUser("smithm1", "Micheal Smith", "password", 1);
            long returned2 = UserHelper.insertUser("smithm1", "Micheal Smith", "password", 1);
            fail("Should be an UserAlreadyExistsException");
        }catch (UserAlreadyExistsException U){

        }
    }

}
