package ru.fit.apotapova;

import java.util.List;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * Interface for classes implementing the graph.
 *
 * @param <T> type of vertex
 */
public interface Graph<T> {

  /**
   * Adds vertex.
   *
   * @param value - value of vertex
   * @return - vertex
   */
  default Vertex<T> addVertex(T value) {
    return addVertex(new Vertex<>(value));
  }

  /**
   * Adds a vertex.
   *
   * @param vertex - added vertex
   * @return - added vertex
   */
  Vertex<T> addVertex(Vertex<T> vertex);

  /**
   * Deletes a vertex.
   *
   * @param vertex - deleted vertex
   */
  void deleteVertex(Vertex<T> vertex);

  /**
   * Gets vertex by value.
   *
   * @param value - value of vertex
   * @return - received vertex
   */
  Vertex<T> getVertex(T value);

  /**
   * Replaces a vertex with another one.
   *
   * @param changeableVertex - changeable vertex
   * @param newVertex        - new vertex
   */
  void changeVertex(Vertex<T> changeableVertex, Vertex<T> newVertex);

  /**
   * Adds edge.
   *
   * @param firstVertex  - vertex from which the edge comes
   * @param length       - edge length
   * @param secondVertex - vertex to which the edge goes
   * @return - edge object
   */
  default Edge<T> addEdge(Vertex<T> firstVertex, Double length, Vertex<T> secondVertex) {
    return addEdge(new Edge<>(firstVertex, length, secondVertex));
  }

  /**
   * Adds edge.
   *
   * @param edge - added edge
   * @return - added edge
   */
  Edge<T> addEdge(Edge<T> edge);

  /**
   * Deletes edge.
   *
   * @param edge - deleted edge
   */
  void deleteEdge(Edge<T> edge);

  /**
   * Gets edge by length.
   *
   * @param length - edge length
   * @return - received edge
   */
  Edge<T> getEdge(Double length);

  /**
   * Replaces an edge with another one. The vertices of the edge remain the same.
   *
   * @param changeableEdge - changeable edge
   * @param newEdge        - new edge
   */
  void changeEdge(Edge<T> changeableEdge, Edge<T> newEdge);

  /**
   * Gets exiting edges of vertex.
   *
   * @param vertex - vertex
   * @return - list of exiting edges
   */
  List<Edge<T>> getExitingEdges(Vertex<T> vertex);
}
