package ru.nsu.fit.apotapova.snake.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Flow.Subscriber;

public class Settings {
  public static int WINDOW_WIDTH = 800;
  public static int WINDOW_HEIGHT = 800;
  public static int SPEED=600;
  private static final Map<Class<?>,List<Subscriber<?>>> subscribersMap = new HashMap<>();
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
    String spriteSheetPath = properties.getProperty("SPRITE_SHEET");
  }
  private static void subscribe(Subscriber<?> subscriber){
    subscribersMap.computeIfAbsent(subscriber.getClass(), k -> new ArrayList<>());
    subscribersMap.get(subscriber.getClass()).add(subscriber);
  }
  public void notify(Class<?> subscriberType){
    subscribersMap.get(subscriberType).forEach(Object::notify);
  }
}
