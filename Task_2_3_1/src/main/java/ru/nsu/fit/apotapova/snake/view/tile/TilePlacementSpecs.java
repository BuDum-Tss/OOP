package ru.nsu.fit.apotapova.snake.view.tile;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 * Настройки ImageView тайла.
 */
public class TilePlacementSpecs {
  private final Point2D viewport;
  private final Double rotation;

  public TilePlacementSpecs(Point2D view, Double rotation) {
    this.viewport = view;
    this.rotation = rotation;
  }
  public static int TILE_IMG_SIZE = 80;
  private Rectangle2D getViewport() {
    return new Rectangle2D(viewport.getX()*TILE_IMG_SIZE,viewport.getY()*TILE_IMG_SIZE,TILE_IMG_SIZE,TILE_IMG_SIZE);
  }
  public void applyTo(ImageView view) {
    view.setViewport(getViewport());
    view.setRotate(rotation);
  }
}
