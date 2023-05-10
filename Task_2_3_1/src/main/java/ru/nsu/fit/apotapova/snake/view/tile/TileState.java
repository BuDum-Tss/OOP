package ru.nsu.fit.apotapova.snake.view.tile;

import java.util.List;
import java.util.Objects;

/**
 * Состояние тайла, определяющееся соседями.
 */
public class TileState {
  private final List<Boolean> list;
  public TileState(
      boolean upperIsConnected,
      boolean leftIsConnected,
      boolean lowerIsConnected,
      boolean rightIsConnected) {
    list=List.of(upperIsConnected,leftIsConnected,lowerIsConnected,rightIsConnected);
  }
  public TileState(List<Boolean> states) {
    if (states.size()!=4) throw new IllegalArgumentException("Wrong number of elements at NeighborsState");
    list=states;
  }
  @Override
  public boolean equals(Object object) {
    TileState state = (TileState) object;
    for (int i =0; i<4;i++) {
      if (list.get(i)!=state.list.get(i)) return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(list.get(0),list.get(1),list.get(2),list.get(3));
  }
  // TODO: 21.04.2023 переопределить hashcode
}
