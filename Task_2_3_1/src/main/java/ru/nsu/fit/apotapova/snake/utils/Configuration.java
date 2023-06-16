package ru.nsu.fit.apotapova.snake.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Содержит конфигурацию игры(не меняется игроком).
 */
public class Configuration {

  public static int WINDOW_WIDTH = 800;
  public static int WINDOW_HEIGHT = 800;
  public static double TILE_HEIGHT;
  public static String LEVELS_PATH = "src/main/resources/ru/nsu/fit/apotapova/snake/levels/";
  public static int SNAKE_ID = 10;
  public static double TILE_WIDTH;

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
    WINDOW_WIDTH = Integer.parseInt(properties.getProperty("WINDOW_WIDTH"));
    WINDOW_HEIGHT = Integer.parseInt(properties.getProperty("WINDOW_HEIGHT"));
    SNAKE_ID = Integer.parseInt(properties.getProperty("SNAKE_ID"));
    LEVELS_PATH = properties.getProperty("LEVELS_PATH");
    TILE_WIDTH = Double.parseDouble(properties.getProperty("TILE_WIDTH"));
    TILE_HEIGHT = Double.parseDouble(properties.getProperty("TILE_HEIGHT"));
  }
}