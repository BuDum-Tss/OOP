package ru.nsu.fit.apotapova.snake.model.playingarea;

import javafx.scene.transform.MatrixType;

public class Area {
  Tile[][] area;
  public Area(int width, int height) {
    area = new Tile[width][height];
    for (int x = 0; x <= area.length; x++) {
      for (int y = 0; y <= area.length; y++) {
        area[x][y]=Tile.EMPTY;
      }
    }

  }
}
