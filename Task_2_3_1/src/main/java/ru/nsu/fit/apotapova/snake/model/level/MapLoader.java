package ru.nsu.fit.apotapova.snake.model.level;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import ru.nsu.fit.apotapova.snake.view.tile.Tile;

public class MapLoader {

  public static List<List<Tile>> loadMap(String path) {
    List<List<Integer>> map;
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      map = new Gson().fromJson(reader, new TypeToken<List<List<Integer>>>() {
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return convertIdsToTiles(map);
  }

  private static List<List<Tile>> convertIdsToTiles(List<List<Integer>> map) {
    return map.stream().map(list -> list.stream().map(Tile::new).toList()).toList();
  }
}
