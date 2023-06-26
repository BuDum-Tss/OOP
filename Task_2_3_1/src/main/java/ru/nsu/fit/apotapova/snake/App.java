package ru.nsu.fit.apotapova.snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.fit.apotapova.snake.utils.Configuration;
import ru.nsu.fit.apotapova.snake.utils.Settings;

/**
 * Application.
 */
public class App extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), Configuration.WINDOW_WIDTH,
        Configuration.WINDOW_HEIGHT);
    stage.setTitle("Snake");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Main method.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    new Configuration().loadConfiguration("src/main/resources/config.ini");
    new Settings().loadSettings("src/main/resources/settings.ini");
    launch();
  }
}
