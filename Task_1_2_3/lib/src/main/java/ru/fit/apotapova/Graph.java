package ru.fit.apotapova;

import java.util.List;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * Interface for classes implementing the graph.
 *
 * @param <K> - type of key of vertex
 * @param <V> - type of value of vertex
 */
public interface Graph<E, K, V> {

  /**
   * Adds vertex.
   *
   * @param key   - key
   * @param value - value of vertex
   * @return - vertex
   */
  default Vertex<K, V> addVertex(K key, V value) {
    return addVertex(new Vertex<>(key, value));
  }

  /**
   * Adds a vertex.
   *
   * @param vertex - added vertex
   * @return - added vertex
   */
  Vertex<K, V> addVertex(Vertex<K, V> vertex);

  /**
   * Deletes a vertex.
   *
   * @param vertex - deleted vertex
   */
  void deleteVertex(Vertex<K, V> vertex);

  /**
   * Gets vertex by value.
   *
   * @param key - key of vertex
   * @return - received vertex
   */
  Vertex<K, V> getVertex(K key);

  /**
   * Replaces a vertex with another one.
   *
   * @param changeableVertex - changeable vertex
   * @param newVertex        - new vertex
   */
  void changeVertex(Vertex<K, V> changeableVertex, Vertex<K, V> newVertex);

  /**
   * Adds edge.
   *
   * @param firstVertex  - vertex from which the edge comes
   * @param key          - edge key
   * @param length       - edge length
   * @param secondVertex - vertex to which the edge goes
   * @return - edge object
   */
  default Edge<E, K, V> addEdge(Vertex<K, V> firstVertex, E key, Double length,
      Vertex<K, V> secondVertex) {
    return addEdge(new Edge<>(firstVertex, key, length, secondVertex));
  }

  /**
   * Adds edge.
   *
   * @param edge - added edge
   * @return - added edge
   */
  Edge<E, K, V> addEdge(Edge<E, K, V> edge);

  /**
   * Deletes edge.
   *
   * @param edge - deleted edge
   */
  void deleteEdge(Edge<E, K, V> edge);

  /**
   * Gets edge by length.
   *
   * @param key - edge key
   * @return - received edge
   */
  Edge<E, K, V> getEdge(E key);

  /**
   * Replaces an edge with another one. The vertices of the edge remain the same.
   *
   * @param changeableEdge - changeable edge
   * @param newEdge        - new edge
   */
  void changeEdge(Edge<E, K, V> changeableEdge, Edge<E, K, V> newEdge);

  /**
   * Gets exiting edges of vertex.
   *
   * @param vertex - vertex
   * @return - list of exiting edges
   */
  List<Edge<E, K, V>> getExitingEdges(Vertex<K, V> vertex);

  /**
   * Gets number of changes of graph.
   *
   * @return - number of changes
   */
  Integer getNumberOfChanges();
}
