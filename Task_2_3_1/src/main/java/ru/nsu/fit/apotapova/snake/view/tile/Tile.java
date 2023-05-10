package ru.nsu.fit.apotapova.snake.view.tile;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import ru.nsu.fit.apotapova.snake.data.Configuration;

/**
 * Класс,
 */
public class Tile {
  private List<Tile> neighbors;
  private ImageView view;
  private TileType tileType;
  private TileState state;
  public Tile(TileType tileType,TileState state){
    view = new ImageView(Configuration.SPRITE_SHEET);
    this.tileType=tileType;
    state = state;
    tileType.getPlacementSpecs(state).applyTo(view);
    neighbors=new ArrayList<>();
  }
  public boolean connectedWith(Tile tile){
    return neighbors.contains(tile);
  }
  private void updateNeighbors() {
    List<Boolean> isConnected = neighbors.stream().map(tile -> (tile.getTileType()==tileType && tile.connectedWith(this))).toList();
    TileState currState = new TileState(isConnected);
    if (currState.equals(state)) return;
    state=currState;
    tileType.getPlacementSpecs(state).applyTo(view);
    neighbors.forEach(Tile::updateNeighbors);
  }
  public List<Tile> getNeighbours() {
    return neighbors;
  }
  public TileType getTileType() {
    return tileType;
  }

  public ImageView getView() {
    return view;
  }
}
