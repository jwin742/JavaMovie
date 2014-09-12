import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
    public static MovieList Movies = new MovieList();
    private final String apikey = "009e4fe826142aaa0bc0fdabbd1b7470"; // You can get your own key for free. Don't use mine.
    private static Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Movie Folder");
        movieDirectory = directoryChooser.showDialog(primaryStage);
        if (movieDirectory != null) {
            createMovies(getMovieTitles());

        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("display.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("JavaMovie");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void createMovies(String[] titles) {

        for (String title : titles) {
            JsonObject mov = getJSONObject(title);
            if (mov != null) {
                Movies.add(new Movie(mov));
            }
        }
    }

    private JsonObject getJSONObject(String title) {
        StringBuilder url = new StringBuilder("http://api.themoviedb.org/3/search/movie?api_key=" + apikey);
        try {
            url.append("&query=").append(URLEncoder.encode(title, "UTF-8"));
            URL searchCall = new URL(url.toString());
            JsonReader reader = Json.createReader(searchCall.openStream());
            JsonObject results = reader.readObject();
            if (results.getInt("total_results") != 0) {
                JsonArray arr = results.getJsonArray("results");
                return arr.getJsonObject(0);
            }
        } catch (MalformedURLException e) {
            Action response = Dialogs.create()
                    .title("Error!")
                    .message("Bad URL")
                    .showException(e);
        } catch (UnsupportedEncodingException e) {
            Action response = Dialogs.create()
                    .title("Error!")
                    .message("Unsupported Encoding:\n" + e.getMessage())
                    .showException(e);
        } catch (IOException e) {
            Action response = Dialogs.create()
                    .title("Error!")
                    .message("IO Error:\n" + e.getMessage().substring(0, 39))
                    .showException(e);
        }
        return null;
    }

    private String[] getMovieTitles() {
        String[] movieTitles = movieDirectory.list((current, name) -> new File(current, name).isDirectory());
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
