package ru.nsu.fit.apotapova.snake.model.entities;

public abstract class Entity {

  protected EntityStatus status;
  private final EntityType type;

  protected Entity(EntityType type) {
    this.type = type;
    status = EntityStatus.ALIVE;
  }

  public EntityStatus getStatus() {
    return status;
  }

  public EntityType getType() {
    return type;
  }

  public abstract void interactWith(Entity entity);
}
