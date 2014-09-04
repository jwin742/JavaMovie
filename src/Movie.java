import javafx.scene.image.Image;

import java.io.File;
import java.util.Date;

/**
 * A class representing one users movie
 * @author Joshua Winchester
 */
public class Movie {

    private String title;
    private Date releaseYear;
    private Image poster;
    private File fileLoc;
    private double rating;

    public Movie(String title, Date releaseYear, Image poster, File fileLoc, double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.poster = poster;
        this.fileLoc = fileLoc;
        this.rating = rating;
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

    public File getFileLoc() {
        return fileLoc;
    }

    public double getRating() {
        return rating;
    }

    public void setFileLoc(File fileLoc) {
        this.fileLoc = fileLoc;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
