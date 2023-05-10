package ru.nsu.fit.apotapova.snake.model.playingarea;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;

public class LoadedMap extends Map {

  public LoadedMap(int length, int width) {
    super(length, width);
  }

  public static class MapLoader {

    public static LoadedMap load(String path) {
      List<List<Integer>> map;
      try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
        map = new Gson().fromJson(reader, new TypeToken<List<List<Integer>>>() {
        });
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      int length = map.size();
      int width = map.get(0).size();
      List<Point2D> spawnPoints = findSpawnPoints(map);
      LoadedMap loadedMap = new LoadedMap(length, width);
      loadedMap.map = map;
      loadedMap.spawnPoints = spawnPoints;
      return loadedMap;
    }

    private static List<Point2D> findSpawnPoints(List<List<Integer>> map) {
      List<Point2D> spawnPoints = new ArrayList<>();
      int length = map.size();
      int width = map.get(0).size();
      for (int x = 0; x < length; x++) {
        for (int y = 0; y < width; y++) {
          if (map.get(x).get(y) == -1) {
            spawnPoints.add(new Point2D(x, y));
            map.get(x).remove(y);
            map.get(x).add(y, 0);
          }
        }
      }
      return spawnPoints;
    }
  }
}
