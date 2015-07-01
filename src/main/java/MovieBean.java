import javax.faces.bean.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 7/1/15.
 */
@ManagedBean (name = "movieBean")
@RequestScoped
public class MovieBean {
    @ManagedProperty("#{userManager}")
    UserManager userManager;
    @ManagedProperty("#{restBean}")
    RestBean restBean;

    Movie movie;
    RatingManager ratingManager;
    String comment;
    int score = -1;

    public MovieBean() {
        ratingManager = new RatingManager();
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setRestBean(RestBean restBean) {
        this.restBean = restBean;
        movie = restBean.getCurrMovie();
    }

    public Movie getMovie() {
       return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String openMovie(Movie movie) {
        this.movie = movie;
        return "movie";
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void rateMovie() {
        if (movie == null || comment == null || score == -1) {
            return;
        }
        ratingManager.storeRateAndComment(score, comment, userManager.getUser().getUsername(), movie.getId());
    }

    public ArrayList<MyRating> getRatings() {
        return ratingManager.getRating(movie.getId());
    }
}
