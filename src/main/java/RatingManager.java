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
            String query = "INSERT INTO comment(score, comment, username, movieID)"
                    + " values(?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, rate);
            preparedStmt.setString(2, comment);
            preparedStmt.setString(3, username);
            preparedStmt.setString(4, movieID);
            preparedStmt.execute();
        } catch (Exception exc) {
            System.out.printf("There is something wrong.");
        } finally {
            Database.makeClosed(con);
        }
    }
    public void storeComment(String comment, String username, String movieID) {
        Connection con = Database.makeConnection();
        try {
            String query = "INSERT INTO comment(comment, username, movieID)" + "values(?, ?, ?)";
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
            String query = "INSERT INTO comment(comment, username, movieID)" + "values(?, ?, ?)";
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
        ArrayList<Integer> rateList = new ArrayList<Integer>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT movieID, score FROM comment WHERE movieID = " + movieID);
            while (result.next()) {
                rateList.add(result.getInt("score"));
            }
        } catch (Exception e){
            e.getMessage();
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
            ResultSet result = state.executeQuery("SELECT movieID, comment FROM comment WHERE movieID = " + movieID);
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
    public ArrayList getRecommendation(String major) {
        ArrayList<String> reco = new ArrayList<>();
        Connection con = Database.makeConnection();
        try {
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT movieID, major FROM comment WHERE major = " + major);
            while (result.next()) {
                reco.add(result.getString("movieID"));
            }
        } catch (Exception e){
            e.getMessage();
        }
        finally {
            Database.makeClosed(con);
        }
        return reco;
    }
}
