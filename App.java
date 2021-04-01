package tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main method of this application calls the start method.
 * PaneOrganizer is the top-level object which is created here
 * and the scene is also set up for graphics to appear in.
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Tetris!");
        stage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
