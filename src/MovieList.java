import java.util.ArrayList;

/**
 * This class is a wrapper class for an arraylist that holds the movies
 *
 * @author Joshua Winchester
 * @version 1.0
 */
public class MovieList {

    private ArrayList<Movie> movieArrayList = new ArrayList<>();
    private int currentIndex = 0;

    public void add(Movie newMovie) {
        movieArrayList.add(newMovie);
    }

    public void removeCurrentMovie() {
        movieArrayList.remove(currentIndex);
    }

    public boolean hasNext() {
        return currentIndex >= movieArrayList.size();
    }

    public boolean hasPrev() {
        return currentIndex == 0;
    }

    public Movie nextMovie() {
        currentIndex++;
        return movieArrayList.get(currentIndex);
    }

    public Movie prevMovie() {
        currentIndex--;
        return movieArrayList.get(currentIndex);
    }

    public int getNumOfMovies() {
        return movieArrayList.size();
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
