package ru.nsu.fit.apotapova.snake.view.tile;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.geometry.Point2D;

/**
 * Объект поля на карте.
 * По состоянию тайла(TileState) определяет параметры(TilePlacementSpecs) для ImageView.
 */
public enum TileType {
  EMPTY(0,
      Map.of(new TileState(false, false, false, false), new TilePlacementSpecs(new Point2D(1, 3), 0d))),
  FOOD(1,
      Map.of(new TileState(false, false, false, false), new TilePlacementSpecs(new Point2D(2, 1), 0d))),
  WALL(2,
      Map.of(new TileState(false, false, false, false), new TilePlacementSpecs(new Point2D(1, 1), 0d))),
  SNAKE_HEAD(3,Map.of(
      new TileState(true, false, false, false), new TilePlacementSpecs(new Point2D(0, 2), 0d),
      new TileState(false, true, false, false), new TilePlacementSpecs(new Point2D(0, 2), 270d),
      new TileState(false, false, true, false), new TilePlacementSpecs(new Point2D(0, 2), 180d),
      new TileState(false, false, false, true), new TilePlacementSpecs(new Point2D(0, 2), 90d)
  )),
  SNAKE_BODY(4,Map.of(
      new TileState(true, false, true, false), new TilePlacementSpecs(new Point2D(0, 1), 0d),
      new TileState(false, true, false, true), new TilePlacementSpecs(new Point2D(0, 1), 90d),
      new TileState(false, false, true, true), new TilePlacementSpecs(new Point2D(0, 0), 0d),
      new TileState(true, false, false, true), new TilePlacementSpecs(new Point2D(0, 0), 90d),
      new TileState(true, true, false, false), new TilePlacementSpecs(new Point2D(0, 0), 180d),
      new TileState(false, true, true, false), new TilePlacementSpecs(new Point2D(0, 0), 270d)
  )),
  SNAKE_TAIL(5,Map.of(
      new TileState(false, true, false, false), new TilePlacementSpecs(new Point2D(1, 0), 0d),
      new TileState(false, false, true, false), new TilePlacementSpecs(new Point2D(1, 0), 270d),
      new TileState(false, false, false, true), new TilePlacementSpecs(new Point2D(1, 0), 180d),
      new TileState(true, false, false, false), new TilePlacementSpecs(new Point2D(1, 0), 90d)
  ));
  private final Map<TileState, TilePlacementSpecs> stateMap;

  private final Integer id;
  private static final Map<Integer, TileType> idMap = Arrays.stream(values()).collect(Collectors.toMap(tileType -> tileType.id, tileType -> tileType));
  TileType(int id, Map<TileState, TilePlacementSpecs> stateMap) {
    this.stateMap = stateMap;
    this.id=id;
  }
  public TilePlacementSpecs getPlacementSpecs(TileState state) {
    return stateMap.get(state);
  }
  public Integer getId() {
    return id;
  }
  public static TileType getByID(int id){
    return idMap.get(id);
  }
}