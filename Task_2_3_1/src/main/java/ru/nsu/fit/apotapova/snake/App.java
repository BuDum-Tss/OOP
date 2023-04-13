package ru.nsu.fit.apotapova.snake;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.fit.apotapova.snake.controller.MainController;
import ru.nsu.fit.apotapova.snake.data.Configuration;
import ru.nsu.fit.apotapova.snake.data.Settings;

public class App extends Application {

  private static MainController mainController;

  public static void setMainController(MainController mainController) {
    App.mainController = mainController;
  }
  @Override
  public void start(Stage stage) throws Exception  {
     FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:///F:/GitHubProjects/OOP/Task_2_3_1/src/main/resources/ru/nsu/fit/apotapova/snake/view/main-view.fxml"));
    //TODO:Изменить путь на относительный
    Scene scene = new Scene(fxmlLoader.load(), 800, 600);
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
