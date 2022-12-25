package ru.fit.apotapova.GraphTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import ru.fit.apotapova.Graph;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * A class that implements a {@link Graph} using an incidence matrix.
 *
 * @param <K> - type of key of vertex
 * @param <V> - type of value of vertex
 */
public class IncidenceMatrixGraph<E, K, V> implements Graph<E, K, V> {

  private Map<K, Vertex<K, V>> vertexList;
  private Map<E, Edge<E, K, V>> edgeList;
  private Map<K, Map<E, Integer>> matrix;
  private int numberOfChanges;

  /**
   * Constructor of a class that defines vertexList, edgeList and incidence matrix.
   */

  public IncidenceMatrixGraph() {
    numberOfChanges = 0;
    vertexList = new HashMap<>();
    edgeList = new HashMap<>();
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
    matrix.get(vertex.getKey()).forEach((edge, value) -> {
      if (value.equals(1) || value.equals(-1)) {
        deleteEdge(edgeList.get(edge));
      }
    });
    matrix.remove(vertex.getKey());
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
    if (edgeList.containsValue(edge)) {
      throw new RuntimeException("The edge is already in the graph");
    }
    edgeList.put(edge.getKey(), edge);
    matrix.get(edge.getFirstVertex().getKey()).put(edge.getKey(), 1);
    matrix.get(edge.getSecondVertex().getKey()).put(edge.getKey(), -1);
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
    if (!edgeList.containsValue(edge)) {
      throw new NoSuchElementException("No vertex from graph");
    }
    matrix.get(edge.getFirstVertex().getKey()).remove(edge.getKey());
    matrix.get(edge.getSecondVertex().getKey()).remove(edge.getKey());
    edgeList.remove(edge.getKey());
    numberOfChanges++;
  }

  /**
   * Implements {@link Graph#getEdge(Object)} method.
   *
   * @param key - edge key
   * @return - received edge
   */
  @Override
  public Edge<E, K, V> getEdge(E key) {
    return edgeList.get(key);
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
    edgeList.remove(changeableEdge.getKey());
    edgeList.put(changeableEdge.getKey(), newEdge);
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
    List<Edge<E, K, V>> edges = new ArrayList<>();
    for (E key : matrix.get(vertex.getKey()).keySet()) {
      if (matrix.get(vertex.getKey()).get(key).equals(1)) {
        edges.add(edgeList.get(key));
      }
    }
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
