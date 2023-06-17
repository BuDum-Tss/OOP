package ru.nsu.fit.apotapova.snake.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Contains the configuration of the game (not changed by the player).
 */
public class Configuration {

  public static double MAX_WIN_SNAKE_LENGTH = 100;
  public static double MAX_FOOD_NUMBER = 10;
  public static int WINDOW_WIDTH = 800;
  public static int WINDOW_HEIGHT = 800;
  public static String LEVELS_PATH = "src/main/resources/ru/nsu/fit/apotapova/snake/levels/";
  public static int SNAKE_ID = 10;
  public static int MIN_SPEED = 500;
  public static int MAX_SPEED = 2000;

  /**
   * Loads configuration.
   *
   * @param configPath file path
   */
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
    MIN_SPEED = Integer.parseInt(properties.getProperty("MIN_SPEED"));
    MAX_SPEED = Integer.parseInt(properties.getProperty("MAX_SPEED"));
    MAX_FOOD_NUMBER = Integer.parseInt(properties.getProperty("MAX_FOOD_NUMBER"));
    MAX_WIN_SNAKE_LENGTH = Integer.parseInt(properties.getProperty("MAX_WIN_SNAKE_LENGTH"));
  }
}