package ru.nsu.fit.apotapova.snake;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.fit.apotapova.snake.controller.SnakeMainController;
import ru.nsu.fit.apotapova.snake.utils.Configuration;
import ru.nsu.fit.apotapova.snake.utils.Settings;

public class App extends Application {

  private static SnakeMainController snakeMainController;

  public static void setMainController(SnakeMainController snakeMainController) {
    App.snakeMainController = snakeMainController;
  }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(new URL(
        "file:///F:/GitHubProjects/OOP/Task_2_3_1/src/main/resources/ru/nsu/fit/apotapova/snake/view/main-view.fxml"));
    //TODO:Изменить путь на относительный
    Scene scene = new Scene(fxmlLoader.load(), Configuration.WINDOW_WIDTH,
        Configuration.WINDOW_HEIGHT);
    stage.setTitle("Snake");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    new Configuration().loadConfiguration("src/main/resources/config.ini");
    new Settings().loadSettings("src/main/resources/settings.ini");
    launch();
  }
}
