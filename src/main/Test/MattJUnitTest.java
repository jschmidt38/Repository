import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by matt on 7/15/15.
 */
public class MattJUnitTest {
    RatingManager manager;

    @Before
    public void setup() {
        manager = new RatingManager();
    }

    @Test
    public void testEmpty() {
        ArrayList<MyRating> recs = manager.getRecommendation("TEST");
        assertTrue(recs.isEmpty());
    }

    @Test
    public void testNorm() {
        manager.storeRateAndComment(8, "Test Comment", "Matt", "TEST1", "TestMovie1", "TEST");
        ArrayList<MyRating> recs = manager.getRecommendation("TEST");
        assertEquals("Failed to get Recommendation of single movie", 8, (int)recs.get(0).getScore());
    }

    @Test
    public void testAvg() {
        manager.storeRateAndComment(8, "Test Comment", "Matt", "TEST1", "TestMovie1", "TEST");
        manager.storeRateAndComment(10, "Test Comment", "Matt", "TEST2", "TestMovie2", "TEST");
        manager.storeRateAndComment(0, "Test Comment", "Matt", "TEST2", "TestMovie2", "TEST");
        String[] movies = {"TEST1", "TEST2"};

        ArrayList<MyRating> ratings = manager.getRecommendation("TEST");
        int i = 0;
        for (MyRating r : ratings) {
            assertEquals("Failed to average ratings", movies[i], r.getMovieID());
            i++;
        }
    }

    @After
    public void cleanup() {
        Connection con = null;
        PreparedStatement preparedStmt = null;
        try {
            con = Database.makeConnection();
            String query = "DELETE FROM comment WHERE major='TEST'";
            preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
