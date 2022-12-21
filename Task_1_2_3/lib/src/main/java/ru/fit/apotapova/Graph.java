package ru.fit.apotapova;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import org.apache.commons.math3.util.Pair;
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
  default Edge<T> addEdge(Vertex<T> firstVertex, Integer length, Vertex<T> secondVertex) {
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
  Edge<T> getEdge(Integer length);

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

  /**
   * Sorts vertices by distance from the specified one using Dijkstra's algorithm.
   *
   * @param vertex - starting vertex
   * @return - sorted list of vertexes
   */
  default List<Vertex<T>> sortVertexes(Vertex<T> vertex) {
    List<Vertex<T>> newVertexList = new ArrayList<>();
    Queue<Pair<Integer, Vertex<T>>> frontier = new PriorityQueue<>(
        Comparator.comparingInt(Pair::getFirst));
    Pair<Integer, Vertex<T>> pair = new Pair<>(0, vertex);
    frontier.add(pair);
    while (!frontier.isEmpty()) {
      Integer k = frontier.peek().getFirst();
      Vertex<T> v = frontier.poll().getSecond();
      newVertexList.add(v);
      List<Edge<T>> edges = getExitingEdges(v);
      for (Edge<T> edge : edges) {
        if (!newVertexList.contains(edge.secondVertex)) {
          Integer sum = k + edge.length;
          Iterator<Pair<Integer, Vertex<T>>> iterator = frontier.iterator();
          boolean hasVertex = false;
          while (iterator.hasNext()) {
            Pair<Integer, Vertex<T>> p = iterator.next();
            if (p.getFirst() > sum && p.getSecond() == edge.secondVertex) {
              frontier.remove(p);
              break;
            } else if (p.getFirst() <= sum && p.getSecond() == edge.secondVertex) {
              hasVertex = true;
              break;
            }
          }
          if (!hasVertex) {
            frontier.add(new Pair<>(sum, edge.secondVertex));
          }
        }
      }
    }
    return newVertexList;
  }
}
