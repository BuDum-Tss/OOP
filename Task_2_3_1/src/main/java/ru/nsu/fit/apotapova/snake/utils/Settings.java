package ru.nsu.fit.apotapova.snake.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
  public static int FOOD_NUMBER = 4;
  public static int MAX_LENGTH = 1000;
  public static int SPEED = 600;

  public void loadSettings(String configPath) {
    try {
      Properties properties = new Properties();
      properties.load(new FileInputStream(configPath));
      loadProperties(properties);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void loadProperties(Properties properties) {
    MAX_LENGTH = Integer.parseInt(properties.getProperty("MAX_LENGTH"));
    SPEED = Integer.parseInt(properties.getProperty("SPEED"));
    FOOD_NUMBER = Integer.parseInt(properties.getProperty("FOOD_NUMBER"));
  }
}
