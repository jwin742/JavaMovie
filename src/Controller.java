import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class Controller {
    public ImageView posterView;
    public Button nextButton;
    public Button prevButton;
    public TextArea movieInfo;

    public void nextButtonClicked(ActionEvent actionEvent) {
        MovieList movieList = Main.Movies;
        if (movieList.hasNext()) {
            System.out.println("yo");
            displayMovie(movieList.nextMovie());
        }
    }

    public void prevButtonClicked(ActionEvent actionEvent) {
        MovieList movieList = Main.Movies;
        if (movieList.hasPrev()) {
            System.out.println("bo");
            displayMovie(movieList.prevMovie());
        }
    }

    private void displayMovie(Movie movie) {
        posterView.setImage(movie.getPoster());
        movieInfo.setText(movie.toPrettyString());
        System.out.println(movie.toString());
    }
}
