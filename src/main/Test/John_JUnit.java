import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class John_JUnit {

    User user;
    User user1;
    User user2;
    User user3;
    User user4;
    User user5;



    @Before
    public void setUp() {
        user = new User("User","Pass");
        user1 = new User(NULL, NULL);
        user2 = new User(NULL, NULL, NULL, NULL, NULL, NULL);
        user3 = new User("HELLO","WORLD","AND", "ALL", "YOU", "PEOPLE");
        user4 = new User("NULL",NULL,NULL,"EXCEPTION","","ABABABABABABABBBABBABABABABABABABABABABAB");
        user5 = new User("","");
    }

    @Test
    public void testNormal() {
        assertEquals("User", user.getUsername());
        assertEquals("Pass", user.getPassword());
        assertEquals("", user.getFirstName());
        assertEquals("", user.getLastName());
        assertEquals("", user.getEmail());
        assertEquals("", user.getMajor());

    }

    @Test
    public void testNull() {
        assertNULL(user1.getUsername());
        assertNULL(user1.getPassword());
        assertEquals("", user1.getFirstName());
        assertEquals("", user1.getLastName());
        assertEquals("", user1.getEmail());
        assertEquals("", user1.getMajor());
    }

    @Test
    public void testLotsOfNull() {
        assertNULL(user2.getUsername());
        assertNULL(user2.getPassword());
        assertNULL(user2.getFirstName());
        assertNULL(user2.getLastName());
        assertNULL(user2.getEmail());
        assertNULL(user2.getMajor());
    }

    public void testLongNormal() {
        assertEquals("HELLO", user3.getUsername());
        assertEquals("WORLD", user3.getPassword());
        assertEquals("AND", user3.getFirstName());
        assertEquals("ALL", user3.getLastName());
        assertEquals("YOU", user3.getEmail());
        assertEquals("PEOPLE", user3.getMajor());

    }


    @Test
    public void testLongandNull() {
        assertEquals("NULL", user4.getUsername());
        assertNULL(user4.getPassword());
        assertNULL(user4.getFirstName());
        assertEquals("EXCEPTION", user4.getLastName());
        assertEquals("", user4.getEmail());
        assertEquals("ABABABABABABABBBABBABABABABABABABABABABAB", user4.getMajor());
    }

    @Test
    public void Emptystr() {

        assertEquals("", user5.getUsername());
        assertEquals("", user5.getPassword());
        assertEquals("", user5.getFirstName());
        assertEquals("", user5.getLastName());
        assertEquals("", user5.getEmail());
        assertEquals("", user5.getMajor());
    }








}