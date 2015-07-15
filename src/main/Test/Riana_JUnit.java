import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by rianadawson on 7/15/15.
 */
public class Riana_JUnit {
    RatingManager ratingManager;

    @Before
    public void setUp() {
        ratingManager = new RatingManager();
    }

    @Test
    public void testIDNegative() {
        assertNull(ratingManager.getAverageRate("-3"));
    }

    @Test
    public void testIDNull() {
        assertNull(ratingManager.getAverageRate(null));
    }

    @Test
    public void testIDNoRating() {
        assertEquals(-1, ratingManager.getAverageRate("222"));
    }

}
