
/**
 * Created by lingyi on 6/27/15.
 */
public class MyRating {
    private String comment;
    private String movieID;
    private String username;
    private int score;

    public MyRating(int score, String comment, String username, String movieID) {
        this.score = score;
        this.comment = comment;
        this.username = username;
        this.movieID = movieID;
    }

    /**
     * getting score
     * @return score
     */
    public int getScore() {
        return score;
    }
    /**
     * setting score
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * getting comment
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * setting comment
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * getting movie ID
     * @return movie ID
     */
    public String getMovieID() {
        return movieID;
    }

    /**
     * setting movie ID
     * @param movieID
     */
    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    /**
     * getting username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * setting user
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
