package ru.nsu.fit.apotapova.snake.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import javafx.scene.image.Image;

/**
 * Содержит конфигурацию игры(не меняется игроком).
 */
public class Configuration {

  public static Image SPRITE_SHEET;
  public static URL GAME_VIEW;
  public void loadConfiguration(String configPath) {
    try {
      Properties properties = new Properties();
      properties.load(new FileInputStream(configPath));
      loadProperties(properties);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void loadProperties(Properties properties) {
    String spriteSheetPath = properties.getProperty("SPRITE_SHEET");
    SPRITE_SHEET = new Image(spriteSheetPath);
    try {
      GAME_VIEW = new File(properties.getProperty("GAME_VIEW")).toURL();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}