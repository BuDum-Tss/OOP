package ru.nsu.fit.apotapova.snake.model.entity;

import java.util.List;
import javafx.geometry.Point2D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Entity {

  protected static final Logger entityLogger = LogManager.getLogger("Entity");
  protected int id;
  protected static EntityType type;

  public abstract void interactWith(EntityType type);

  public int getId() {
    return id;
  }

  public Entity(int id) {
    this.id = id;
  }

  public EntityType getType() {
    return type;
  }

  public abstract List<Point2D> getPosition();
}
