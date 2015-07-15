import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Thanh on 7/13/15.
 */
public class Thanh_JUnit {

    UserManager manager;

    @Before
    public void setUp() {
        manager = new UserManager();
    }

    @Test
    public void testCorrectInformation() {
        User user = manager.login("thanh","1234");
        assertTrue(user.getUsername().equals("thanh"));
        assertTrue(user.getPassword().equals("1234"));
        assertEquals("thanh",user.getFirstName());
//        assertTrue(user.getLastName().equalsIgnoreCase("tran"));
//        assertTrue(user.getEmail().equalsIgnoreCase("email"));
    }


    @Test
    public void testEmptyUsernameAndPassWord() {
        assertNull(manager.login("",""));
    }
//
    @Test
    public void testNonExistUsername() {
        assertNull(manager.login("funnnyUserNameHere","password"));
    }
//
    @Test
    public void testWrongPassword() {
        assertNull(manager.login("thanh","thisIsAWrongPassword"));
    }
//
    @Test
    public void testNullUsername() {
        assertNull(manager.login(null, "password"));
    }

    @Test
    public void testNullPassword() {
        assertNull("thanh", null);
    }




}
