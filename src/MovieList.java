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

    /**
     * Returns the currently selected movie
     *
     * @return The current movie.
     */
    public Movie getCurrentMovie() {
        return movieArrayList.get(currentIndex);
    }

    /**
     * Adds a new movie to the back of the MovieList.
     *
     * @param newMovie A new Movie of the users.
     */
    public void add(Movie newMovie) {
        movieArrayList.add(newMovie);
    }

    /**
     * Removes the current Movie from the List.
     */
    public void removeCurrentMovie() {
        movieArrayList.remove(currentIndex);
    }

    /**
     * Checks if the movie has another movie after it in the list.
     *
     * @return true if there is another movie after it in the list.
     */
    public boolean hasNext() {
        return currentIndex <= movieArrayList.size() - 2;
    }

    /**
     * Checks if the movie has another movie before it in the list.
     *
     * @return true if there is another movie after it in the list.
     */
    public boolean hasPrev() {
        return currentIndex != 0;
    }

    /**
     * Gets the next movie in the list.
     *
     * @return The next movie in the list.
     */
    public Movie nextMovie() {
        currentIndex++;
        return movieArrayList.get(currentIndex);
    }

    /**
     * Gets the previous movie in the list.
     *
     * @return The previous movie in the list.
     */
    public Movie prevMovie() {
        currentIndex--;
        return movieArrayList.get(currentIndex);
    }

    /**
     * Gets the total number of movies in the list
     *
     * @return The number of Movies in list.
     */
    public int getNumOfMovies() {
        return movieArrayList.size();
    }

    /**
     * Gets the index of the movie currently selected.
     *
     * @return The index of the movie selected
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Sets the index of the movie currently selected.
     *
     * @param currentIndex The index of the selected movie
     */
    public void setCurrentIndex(int currentIndex) throws IndexOutOfBoundsException {
        if (currentIndex >= movieArrayList.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.currentIndex = currentIndex;
    }
}
