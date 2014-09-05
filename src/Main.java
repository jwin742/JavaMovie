import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * This product uses the TMDb API but is not endorsed or certified by TMDb.
 *
 *
 * @author Joshua Winchester
 * @version 1.0
 */
public class Main extends Application {

    private File movieDirectory;
    private MovieList Movies = new MovieList();
    private final String apikey = "009e4fe826142aaa0bc0fdabbd1b7470"; // You can get your own key for free. Don't use mine.


    @Override
    public void start(Stage primaryStage) throws Exception{
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Movie Folder");
        movieDirectory = directoryChooser.showDialog(primaryStage);
        if (movieDirectory != null) {
            createMovies(getMovieTitles());

        }
        Parent root = FXMLLoader.load(getClass().getResource("display.fxml"));
        primaryStage.setTitle("Movie");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void createMovies(String[] titles) {

        for (int x = 0; x < titles.length; x++) {
            JsonObject mov = getJSONObject(titles[x]);
            if (mov != null) {
                Movies.add(new Movie(mov));
            }
        }
    }

    private JsonObject getJSONObject(String title) {
        StringBuilder url = new StringBuilder("http://api.themoviedb.org/3/search/movie?api_key=" + apikey);
        url.append("&query=" + title);
        try {
            URL searchCall = new URL(url.toString());
            JsonReader reader = Json.createReader(searchCall.openStream());
            JsonObject results = reader.readObject();
            if (results.getInt("total_results") != 0) {
                JsonArray arr = results.getJsonArray("results");
                return arr.getJsonObject(0);
            }
        } catch (MalformedURLException e) {
            //TODO: Add a popup error dialog
            e.printStackTrace();
        } catch (IOException e) {
            //TODO: Add a popup error dialog
            e.printStackTrace();
        }
        return null;
    }

    private String[] getMovieTitles() {
        String[] movieTitles = movieDirectory.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        for (int x = 0; x < movieTitles.length; x++) {
            if (movieTitles[x].contains("(")) {
                movieTitles[x] = movieTitles[x].substring(0, movieTitles[x].indexOf("("));
            }
        }
        System.out.println(Arrays.toString(movieTitles));
        return movieTitles;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
