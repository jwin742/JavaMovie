import javafx.scene.image.Image;

import javax.json.JsonObject;
import java.io.File;
import java.util.Date;

/**
 * A class representing one users movie
 * @author Joshua Winchester
 */
public class Movie {

    private String title;
    private Date releaseYear = new Date();
    private Image poster;
    private double rating;
    private JsonObject source;

    public Movie(JsonObject source) {
        this.source = source;
        this.title = source.getString("title");
        this.releaseYear.setTime(Date.parse(source.getString("release_date")));
        this.poster = new Image("https://image.tmdb.org/t/p/original" + source.getString("poster_path"));
    }

    public Movie(String title, Date releaseYear, Image poster, double rating, JsonObject source) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.poster = poster;
        this.rating = rating;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public Image getPoster() {
        return poster;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public JsonObject getSource() {
        return source;
    }

    public void setSource(JsonObject source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!releaseYear.equals(movie.releaseYear)) return false;
        if (source != null ? !source.equals(movie.source) : movie.source != null) return false;
        if (!title.equals(movie.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + releaseYear.hashCode();
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(title + "{");
        sb.append(" releaseYear=").append(releaseYear);
        sb.append(", poster=").append(poster);
        sb.append(", rating=").append(rating);
        sb.append(", source=").append(source);
        sb.append(" }");
        return sb.toString();
    }
}
