import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.json.JsonReader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class Main extends Application {

    File movieDirectory;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("display.fxml"));
        primaryStage.setTitle("Movie");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Movie Folder");
        movieDirectory = directoryChooser.showDialog(primaryStage);
        if (movieDirectory != null) {
            getMovies();
        }
    }

    private void getMovies() {
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
        JsonReader js;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
