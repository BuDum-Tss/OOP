package ru.nsu.fit.apotapova.snake.view.tile;

import java.util.List;
import javafx.util.Pair;
import ru.nsu.fit.apotapova.snake.model.entity.EntityType;

/**
 * Tile type.
 */
public enum TileType {
  EMPTY(new Pair<>(0, 1), null),
  WALL(new Pair<>(1, 2), EntityType.WALL),
  FOOD(new Pair<>(2, 1000), EntityType.FOOD),
  SNAKE(new Pair<>(1000, 2000), EntityType.SNAKE);
  private static final List<TileType> list = List.of(EMPTY, WALL, FOOD, SNAKE);

  public Pair<Integer, Integer> getIdRange() {
    return idRange;
  }

  private final Pair<Integer, Integer> idRange;
  private final EntityType entityType;

  TileType(Pair<Integer, Integer> idRange, EntityType entityType) {
    this.idRange = idRange;
    this.entityType = entityType;
  }

  public EntityType getEntityType() {
    return entityType;
  }

  /**
   * Returns TileType by id.
   *
   * @param id id
   * @return tile type
   */
  public static TileType getById(int id) {
    return list.stream().filter(
            type -> type.idRange.getKey() <= Math.abs(id) && type.idRange.getValue() > Math.abs(id))
        .findFirst().orElse(null);
  }
}
