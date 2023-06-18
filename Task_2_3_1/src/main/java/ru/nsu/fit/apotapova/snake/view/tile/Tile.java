package ru.nsu.fit.apotapova.snake.view.tile;

import javafx.scene.shape.Rectangle;
import ru.nsu.fit.apotapova.snake.model.data.GameData;
import ru.nsu.fit.apotapova.snake.model.entity.EntityType;
import ru.nsu.fit.apotapova.snake.model.entity.dynamicentities.Snake;

/**
 * Tile.
 */
public class Tile {

  int entityId;
  Rectangle view;

  public Tile(int entityId, int tileSize) {
    this.entityId = entityId;
    view = new Rectangle(10, 10);
  }

  public EntityType getEntityType() {
    return TileType.getById(entityId).getEntityType();
  }

  public TileType getType() {
    return TileType.getById(entityId);
  }

  public Rectangle getView() {
    return view;
  }

  public Integer getId() {
    return entityId;
  }

  public void updateViewById() {
    String color = pickColor();
    view.setStyle("-fx-fill:  #" + color);
  }

  private String pickColor() {
    return switch (TileType.getById(entityId)) {
      case EMPTY -> "000019";
      case WALL -> "888888";
      case FOOD -> "FF0000";
      case SNAKE -> (GameData.getGameData().getEntityById(Math.abs(entityId)) != null
          ? ((Snake) GameData.getGameData().getEntityById(Math.abs(entityId))).getColor()
          : "000019");
    };
  }

  public void setId(Integer entityId) {
    this.entityId = entityId;
  }
}
