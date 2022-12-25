package ru.fit.apotapova.GraphTypes;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import ru.fit.apotapova.Graph;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * A class that implements a {@link Graph} using an adjacency matrix.
 *
 * @param <K> - type of key of vertex
 * @param <V> - type of value of vertex
 */
public class AdjacencyMatrixGraph<E, K, V> implements Graph<E, K, V> {

  private final Map<K, Vertex<K, V>> vertexList;
  private final Map<K, Map<K, Edge<E, K, V>>> matrix;
  private int numberOfChanges;

  /**
   * Constructor of a class that defines vertexList, edgeList and adjacency matrix.
   */
  public AdjacencyMatrixGraph() {
    numberOfChanges = 0;
    vertexList = new HashMap<>();
    matrix = new HashMap<>();
  }

  /**
   * Implements {@link Graph#addVertex(Vertex)} method.
   *
   * @param vertex - added vertex
   * @return - added vertex
   */
  @Override
  public Vertex<K, V> addVertex(Vertex<K, V> vertex) {
    if (vertexList.containsValue(vertex)) {
      throw new RuntimeException("The vertex is already in the graph");
    }
    vertexList.put(vertex.getKey(), vertex);
    matrix.put(vertex.getKey(), new HashMap<>());
    numberOfChanges++;
    return vertex;
  }

  /**
   * Implements {@link Graph#deleteVertex(Vertex)} method.
   *
   * @param vertex - deleted vertex
   */
  @Override
  public void deleteVertex(Vertex<K, V> vertex) {
    if (!vertexList.containsValue(vertex)) {
      throw new NoSuchElementException("No vertex from graph");
    }
    vertexList.remove(vertex.getKey());
    matrix.remove(vertex.getKey());
    matrix.forEach((v, map) -> {
      map.remove(vertex.getKey());
    });
    numberOfChanges++;
  }

  /**
   * Implements {@link Graph#getVertex(Object)} method.
   *
   * @param key - key of vertex
   * @return - received vertex
   */
  @Override
  public Vertex<K, V> getVertex(K key) {
    return vertexList.get(key);
  }

  /**
   * Implements {@link Graph#changeVertex(Vertex, Vertex)} method.
   *
   * @param changeableVertex - changeable vertex
   * @param newVertex        - new vertex
   */
  @Override
  public void changeVertex(Vertex<K, V> changeableVertex, Vertex<K, V> newVertex) {
    vertexList.remove(changeableVertex.getKey());
    vertexList.put(newVertex.getKey(), newVertex);
    numberOfChanges++;
  }

  /**
   * Implements {@link Graph#addEdge(Edge)} method.
   *
   * @param edge - added edge
   * @return - added edge
   */
  @Override
  public Edge<E, K, V> addEdge(Edge<E, K, V> edge) {
    if (matrix.get(edge.getFirstVertex().getKey()) != null
        && matrix.get(edge.getFirstVertex().getKey()).get(edge.getSecondVertex().getKey()) != null
        && !matrix.get(edge.getFirstVertex().getKey()).get(edge.getSecondVertex().getKey())
        .equals(edge)) {
      throw new RuntimeException("The edge is already in the graph");
    }
    matrix.get(edge.getFirstVertex().getKey()).put(edge.getSecondVertex().getKey(), edge);
    numberOfChanges++;
    return edge;
  }

  /**
   * Implements {@link Graph#deleteEdge(Edge)} method.
   *
   * @param edge - deleted edge
   */
  @Override
  public void deleteEdge(Edge<E, K, V> edge) {
    if (matrix.get(edge.getFirstVertex().getKey()) == null
        || matrix.get(edge.getFirstVertex().getKey()).get(edge.getSecondVertex().getKey()) == null
        || !matrix.get(edge.getFirstVertex().getKey()).get(edge.getSecondVertex().getKey())
        .equals(edge)) {
      throw new NoSuchElementException("No edge from graph");
    }
    matrix.get(edge.getFirstVertex().getKey()).remove(edge.getSecondVertex().getKey());
    numberOfChanges++;
  }

  /**
   * Implements {@link Graph#getEdge(E)} method.
   *
   * @param key - edge key
   * @return - received edge
   */
  @Override
  public Edge<E, K, V> getEdge(E key) {
    for (Map<K, Edge<E, K, V>> edges : matrix.values()) {
      for (Edge<E, K, V> edge : edges.values()) {
        if (edge != null && edge.getKey().equals(key)) {
          return edge;
        }
      }
    }
    return null;
  }

  /**
   * Implements {@link Graph#changeEdge(Edge, Edge)} method.
   *
   * @param changeableEdge - changeable edge
   * @param newEdge        - new edge
   */
  @Override
  public void changeEdge(Edge<E, K, V> changeableEdge, Edge<E, K, V> newEdge) {
    if (changeableEdge.getKey() != changeableEdge.getKey()) {
      throw new RuntimeException("Keys are not equal");
    }
    matrix.get(changeableEdge.getFirstVertex().getKey())
        .remove(changeableEdge.getSecondVertex().getKey());
    matrix.get(changeableEdge.getFirstVertex().getKey())
        .put(changeableEdge.getSecondVertex().getKey(), newEdge);
    newEdge.setFirstVertex(changeableEdge.getFirstVertex());
    newEdge.setSecondVertex(changeableEdge.getSecondVertex());
    numberOfChanges++;
  }

  /**
   * Implements {@link Graph#getExitingEdges(Vertex)} method.
   *
   * @param vertex - vertex
   * @return - list of exiting edges
   */
  @Override
  public List<Edge<E, K, V>> getExitingEdges(Vertex<K, V> vertex) {
    List<Edge<E, K, V>> edges = new ArrayList<>(matrix.get(vertex.getKey()).values());
    edges.removeIf(Objects::isNull);
    return edges;
  }

  /**
   * Implements {@link Graph#getNumberOfChanges()} method.
   *
   * @return - number of changes
   */
  @Override
  public Integer getNumberOfChanges() {
    return numberOfChanges;
  }
}
