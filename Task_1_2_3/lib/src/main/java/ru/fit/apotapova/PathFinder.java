package ru.fit.apotapova;

import java.util.List;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * Interface for the pathfinding algorithm in {@link Graph}.
 *
 * @param <T> - type of vertexes in {@link Graph}
 */
public interface PathFinder<T> {

  /**
   * Sorts vertices by distance from the specified one using Dijkstra's algorithm.
   *
   * @param vertex - starting vertex
   * @return - sorted list of vertexes
   */
  List<Vertex<T>> sortVertexes(Vertex<T> vertex);
}
