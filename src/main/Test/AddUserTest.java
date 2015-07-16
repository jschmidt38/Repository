import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lingyi on 7/15/15.
 */
public class AddUserTest {

    UserManager manager;
    @Before
    public void setUp() {
        manager = new UserManager();
    }
    @Test
    public void testAddValidUser() {
        String username = "huahua";
        String password = "huahua";
        assertTrue( manager.addUser(username, password));
    }
    @Test
    public void testAddExistUser() {
        String username = "qwe";
        String password = "qwe";
        assertFalse(manager.addUser(username, password));
    }
}
