import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 6/17/15.
 */
@ManagedBean
@ApplicationScoped
public class RestBean {

    private String data;
    private String keyword;
    private List<Movie> movieData;

    public RestBean() {
        System.out.println("made RestBean");
        movieData = new ArrayList<>();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void apicall(String str) {
        URL url = null;
        try {
            url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            data = "";
            String buffer;
            while ((buffer = br.readLine()) != null) {
                data += buffer;
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        } catch (IOException e) {
            System.out.println("Cannot open url");
        }
    }

    public List<Movie> search() {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=yedukp76ffytfuy24zsqk7f5&q="
                + keyword + "&page_limit=20";
        apicall(url);
        System.out.println(url);
        System.out.println(data);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        MovieResponse response = gson.fromJson(data, MovieResponse.class);
        List<Movie> movies = response.getMovies();
        movieData = movies;
        return movieData;
    }
    public List<Movie> theaters() {
        String url = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?"
                + "apikey=yedukp76ffytfuy24zsqk7f5";
        apicall(url);

        Gson gson = new Gson();
        MovieResponse response = gson.fromJson(data, MovieResponse.class);
        List<Movie> movies = response.getMovies();
        movieData = movies;
        System.out.println(url);
        return movieData;
    }

    public List<Movie> getMovieData() {
        return movieData;
    }

    public String getSize() {
        return "size: " + movieData.size();
    }
}
