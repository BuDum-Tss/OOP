package ru.nsu.fit.apotapova.snake.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.layout.GridPane;
import ru.nsu.fit.apotapova.snake.Utils;
import ru.nsu.fit.apotapova.snake.data.Configuration;
import ru.nsu.fit.apotapova.snake.model.playingarea.Map;
import ru.nsu.fit.apotapova.snake.view.tile.TileState;
import ru.nsu.fit.apotapova.snake.view.tile.TileType;
import ru.nsu.fit.apotapova.snake.view.tile.Tile;

public class MapView {

  List<List<Tile>> area;

  public MapView(Map map) {
    area = map.getMap().stream().map(list -> list.stream().map(
            id -> new Tile(TileType.getByID(id), new TileState(List.of(false, false, false, false))))
        .toList()).toList();
    /*
    area = new ArrayList<>(map.getLength());
    for (int x = 0; x < map.getLength(); x++) {
      area.add(new ArrayList<>(map.getWidth()));
      for (int y = 0; y < map.getWidth(); y++) {
        Tile view = new Tile(TileType.EMPTY, new TileState(false, false, false, false));
        area.get(x).add(view);
      }
    }*/
    for (int x = 0; x < map.getLength(); x++) {
      for (int y = 0; y < map.getWidth(); y++) {
        area.get(x).get(y).getNeighbours().addAll(Utils.getNeighbors(new Point2D(x, y), area));
      }
    }
  }

  public Tile getTileView(int x, int y) {
    return area.get(x).get(y);
  }

  public void addTilesTo(GridPane gameArea) {
    for (int x = 0; x < Configuration.AREA_LENGTH; x++) {
      for (int y = 0; y < Configuration.AREA_WIDTH; y++) {
        //imageView.setViewport(TileType.EMPTY);
        //imageView.setScaleX(2);
        //imageView.setScaleY(2);
        gameArea.addColumn(x, area.get(x).get(y).getView());
      }
    }
  }
}
