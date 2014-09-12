import javafx.scene.image.Image;

import javax.json.JsonObject;

/**
 * A class representing one users movie
 * @author Joshua Winchester
 */
public class Movie {

    private String title;
    private String releaseDate;
    private Image poster;
    private double rating;
    private JsonObject source;

    public Movie(JsonObject source) {
        this.source = source;
        this.title = source.getString("title");
        this.releaseDate = source.getString("release_date");
        this.poster = new Image("https://image.tmdb.org/t/p/original" + source.getString("poster_path"));
        this.rating = source.getJsonNumber("vote_average").doubleValue();
    }

    public Movie(String title, String releaseDate, Image poster, double rating, JsonObject source) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.rating = rating;
        this.source = source;
    }

    /**
     * Returns the Move Title.
     *
     * @return The movie title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the movies release year.
     *
     * @return The movies release year.
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Returns a JavaFx image of the poster
     *
     * @return The movie poster.
     */
    public Image getPoster() {
        return poster;
    }

    /**
     * Returns the movie's rating on a scale of 1 - 10
     *
     * @return The movie's rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Lets the user set their own rating for the movie
     *
     * @param rating The users rating.
     */
    public void setRating(double rating) throws IllegalArgumentException {
        if (rating > 10) { throw new IllegalArgumentException("Rating was greater then 10."); }
        if (rating < 0) {throw new IllegalArgumentException("Rating was less then 0."); }
        this.rating = rating;
    }

    /**
     * Returns the JsonObject from TMDb API
     *
     * @return A JsonObject representing the Movie
     */
    public JsonObject getSource() {
        return source;
    }

    /**
     * Sets the JsonObject that the movie is based on.
     * May remove
     *
     * @param source A Json object that represents the movie.
     */
    public void setSource(JsonObject source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!releaseDate.equals(movie.releaseDate)) return false;
        if (source != null ? !source.equals(movie.source) : movie.source != null) return false;
        if (!title.equals(movie.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(title + "{");
        sb.append(" releaseDate=").append(releaseDate);
        sb.append(", poster=").append(poster);
        sb.append(", rating=").append(rating);
        sb.append(", source=").append(source);
        sb.append(" }");
        return sb.toString();
    }

    public String toPrettyString() {
        final StringBuilder sb = new StringBuilder(title + "\n");
        sb.append("Release Date: ").append(releaseDate).append("\n");
        sb.append("Rating: ").append(rating).append("\n");
        return sb.toString();
    }
}
