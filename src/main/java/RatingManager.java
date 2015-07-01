import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by lingyi on 6/26/15.
 */
public class RatingManager {
    public void storeRateAndComment(int rate, String comment, String username, String movieID) {
        Connection con = Database.makeConnection();
        try {
            String query = "INSERT INTO Rate(score, comment, username, movieID)"
                    + " values(?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, rate);
            preparedStmt.setString(2, comment);
            preparedStmt.setString(3, username);
            preparedStmt.setString(4, movieID);
            preparedStmt.execute();
            System.out.println("added entry to RATE");
        } catch (Exception exc) {
            System.out.printf("There is something wrong.");
            System.out.println(exc.getMessage());
        } finally {
            Database.makeClosed(con);
        }
    }
    public void storeComment(String comment, String username, String movieID) {
        Connection con = Database.makeConnection();
        try {
            String query = "INSERT INTO Rate(comment, username, movieID)" + "values(?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, comment);
            preparedStmt.setString(2, username);
            preparedStmt.setString(3, movieID);
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }finally {
            Database.makeClosed(con);
        }
    }
    public void  storeRate(int score, String movieID, String username) {
        Connection con = Database.makeConnection();
        try {
            String query = "INSERT INTO Rate(comment, username, movieID)" + "values(?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, score);
            preparedStmt.setString(2, username);
            preparedStmt.setString(3, movieID);
            preparedStmt.execute();
        } catch (Exception e) {
            e.getMessage();
        }finally {
            Database.makeClosed(con);
        }
    }

    public ArrayList getRating(String movieID) {
        ArrayList<MyRating> rateList = new ArrayList<MyRating>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT movieID, score, comment, username FROM Rate WHERE movieID = " + movieID);
            while (result.next()) {
                rateList.add(new MyRating(result.getInt("score"), result.getString("comment"),
                        result.getString("username"), result.getNString("movieID")));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            Database.makeClosed(con);
        }
        return rateList;
    }
    public int getAverageRate(String movieID) {
        ArrayList<Integer> rateList = getRating(movieID);
        int total = 0;
        for (int score : rateList) {
            total = total + score;
        }
        return total/rateList.size();
    }
    public ArrayList getComment(String movieID) {
        ArrayList<String> commentList = new ArrayList<String>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT movieID, comment FROM Rate WHERE movieID = " + movieID);
            while (result.next()) {
                commentList.add(result.getString("comment"));
            }
        } catch (Exception e){
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
        return commentList;
    }
}
