import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;

/**
 * Created by matt on 7/1/15.
 */
@ManagedBean (name = "movieBean")
@RequestScoped
public class MovieBean {
    @ManagedProperty("#{userManager}")
    private UserManager userManager;
    @ManagedProperty("#{restBean}")
    private RestBean restBean;

    private Movie movie;
    private RatingManager ratingManager;
    private String comment;
    private int score = -1;

    /**
     * Constructor for MovieBean
     */
    public MovieBean() {
        ratingManager = new RatingManager();
    }

    /**
     * Setter for userManager
     * @param userManager
     */
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Setter for restBaan
     * @param restBean
     */
    public void setRestBean(RestBean restBean) {
        this.restBean = restBean;
        movie = restBean.getCurrMovie();
    }

    /**
     * Getter for movie
     * @return movie
     */
    public Movie getMovie() {
       return movie;
    }

    /**
     * Setter for movie
     * @param movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * open a movie page
     * @param movie
     * @return the movie page
     */
    public String openMovie(Movie movie) {
        this.movie = movie;
        return "movie";
    }

    /**
     * Getter for score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for score
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter for comment
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter for comment
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * rate a movie
     */
    public void rateMovie() {
        if (movie == null || comment == null || score == -1) {
            return;
        }
        ratingManager.storeRateAndComment(score, comment, userManager.getUser().getUsername(), movie.getId(), movie.getTitle(), userManager.getUser().getMajor());
    }

    /**
     * gets all the ratings for a movie
     */
    public ArrayList<MyRating> getRatings() {
        return ratingManager.getRating(movie.getId());
    }

    /**
     * Gets list of recommended movies and makes an api call for each
     * @return toReturn the list of recommended movies
     */
    public ArrayList<Movie> getRecommendations() {
        ArrayList<Movie> toReturn = new ArrayList<Movie>();
        ArrayList<MyRating> list = ratingManager.getRecommendation(userManager.getUser().getMajor());
        for (MyRating e : list) {
            toReturn.add(restBean.getMovieByID(e.getMovieID()));
        }
        return toReturn;
    }
}
