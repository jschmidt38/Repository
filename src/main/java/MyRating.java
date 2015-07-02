
/**
 * Created by lingyi on 6/27/15.
 */
public class MyRating {
    private String comment;
    private String movieID;
    private String username;
    private String movieName;
    private String major;
    private int score;

    public MyRating(int score, String comment, String username, String major, String movieID, String movieName) {
        this.score = score;
        this.comment = comment;
        this.username = username;
        this.major = major;
        this.movieID = movieID;
        this.movieName = movieName;
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

    /**
     * getting movieName
     * @return
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * setting movieName
     * @param movieName
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * getting major
     * @return
     */
    public String getmajor() {
        return major;
    }

    /**
     * setting major
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }
}
