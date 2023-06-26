package ru.nsu.fit.apotapova.snake.utils;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Contains game settings (changed by the player).
 */
public class Settings {

  private static Properties properties;
  private static String path;
  public static String LEVEL_PATH = "level1.json";
  public static int FOOD_NUMBER = 4;
  public static int WIN_SNAKE_LENGTH = 1000;
  public static int SPEED = 600;

  /**
   * Loads settings.
   *
   * @param path file path.
   */
  public void loadSettings(String path) {
    try {
      Settings.path = path;
      properties = new Properties();
      properties.load(new FileInputStream(path));
      loadProperties();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void loadProperties() {
    WIN_SNAKE_LENGTH = Integer.parseInt(properties.getProperty("WIN_SNAKE_LENGTH"));
    SPEED = Integer.parseInt(properties.getProperty("SPEED"));
    FOOD_NUMBER = Integer.parseInt(properties.getProperty("FOOD_NUMBER"));
    LEVEL_PATH = properties.getProperty("LEVEL_PATH");
  }

  public static void addProperty(String key, Object value) {
    properties.put(key, value.toString());
  }

  /**
   * Saves settings.
   */
  public static void saveProperties() {
    try {
      properties.store(new FileWriter(path), "");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
