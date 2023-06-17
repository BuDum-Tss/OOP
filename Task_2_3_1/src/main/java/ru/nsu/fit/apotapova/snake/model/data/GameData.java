package ru.nsu.fit.apotapova.snake.model.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import javafx.geometry.Point2D;
import ru.nsu.fit.apotapova.snake.model.entity.Entity;
import ru.nsu.fit.apotapova.snake.model.entity.staticentities.Food;
import ru.nsu.fit.apotapova.snake.model.gamerules.GameRule;
import ru.nsu.fit.apotapova.snake.view.tile.Tile;

public class GameData {

  private static GameData GAME_DATA;
  private Map<GameRule, Supplier> gameRules;
  private List<List<Tile>> map;
  private final HashMap<Integer, Entity> entities;
  private GameState gameState;

  public GameData() {
    entities = new HashMap<>();
    gameRules = new HashMap<>();
  }

  public static GameData getGameData() {
    if (GAME_DATA == null) {
      GAME_DATA = new GameData();
    }
    return GAME_DATA;
  }

  public void clearGameData() {
    GAME_DATA = null;
  }

  public void setMap(List<List<Tile>> map) {
    this.map = map;
  }

  public void addRule(GameRule type, Supplier rule) {
    gameRules.put(type, rule);
  }

  public void addToGame(Entity entity) {
    entities.put(entity.getId(), entity);
  }

  public void removeFromGame(Entity entity) {
    entities.remove(entity.getId());
  }

  public Tile getTileFromPosition(Point2D position) {
    return map.get((int) position.getX()).get((int) position.getY());
  }

  public double getMapWidth() {
    return map.size();
  }

  public double getMapLength() {
    return map.get(0).size();
  }

  public List<List<Tile>> getMap() {
    return map;
  }

  public List<Entity> getEntities() {
    return entities.values().stream().toList();
  }

  public Entity getEntityById(int id) {
    return entities.get(id);
  }

  public List<Integer> getEntitiesId() {
    return entities.keySet().stream().toList();
  }

  public synchronized void setGameState(GameState gameState) {
    this.gameState = gameState;
  }

  public GameState getGameState() {
    return gameState;
  }

  public int getFoodNumber() {
    return (int) entities.values().stream().filter(entity -> entity.getClass() == Food.class)
        .count();
  }

  public GameResult getGameResult() {
    if ((boolean) gameRules.get(GameRule.VICTORY).get()) {
      return GameResult.VICTORY;
    }
    if ((boolean) gameRules.get(GameRule.LOSS).get()) {
      return GameResult.LOSS;
    }
    return GameResult.IN_PROGRESS;
  }
}