package ru.fit.apotapova;

import java.util.List;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * Interface for the pathfinding algorithm in {@link Graph}.
 *
 * @param <E> - type of keys of edges in {@link Graph}
 * @param <K> - type of keys of vertexes in {@link Graph}
 * @param <V> - type of value of vertexes in {@link Graph}
 */
public interface PathFinder<E, K, V> {

  /**
   * Sorts vertices by distance from the specified one using Dijkstra's algorithm.
   *
   * @param vertex - starting vertex
   * @return - sorted list of vertexes
   */
  List<Vertex<K, V>> sortVertexes(Vertex<K, V> vertex);
}
