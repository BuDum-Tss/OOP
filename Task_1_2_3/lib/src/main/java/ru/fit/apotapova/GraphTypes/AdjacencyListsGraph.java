package ru.fit.apotapova.GraphTypes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import ru.fit.apotapova.Graph;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * A class that implements a {@link Graph} using an adjacency list.
 *
 * @param <K> - type of key of vertex
 * @param <V> - type of value of vertex
 */
public class AdjacencyListsGraph<E, K, V> implements Graph<E, K, V> {

  private Map<K, Vertex<K, V>> vertexList;
  private Map<K, List<Edge<E, K, V>>> edgeList;
  private int numberOfChanges;

  /**
   * Constructor of a class that defines vertexList and edgeList.
   */
  public AdjacencyListsGraph() {
    numberOfChanges = 0;
    this.vertexList = new HashMap<>();
    this.edgeList = new HashMap<>();
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
    edgeList.put(vertex.getKey(), new ArrayList<>());
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
    edgeList.remove(vertex.getKey());
    edgeList.forEach((v, list) -> {
      list.forEach(edge -> {
        if (edge.getSecondVertex() == vertex) {
          list.remove(edge);
        }
      });
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
    if (edgeList.get(edge.getFirstVertex().getKey()).contains(edge)) {
      throw new RuntimeException("The edge is already in the graph");
    }
    edgeList.get(edge.getFirstVertex().getKey()).add(edge);
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
    if (!edgeList.get(edge.getFirstVertex().getKey()).contains(edge)) {
      throw new NoSuchElementException("No edge from graph");
    }
    edgeList.get(edge.getFirstVertex().getKey()).remove(edge);
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
    for (List<Edge<E, K, V>> edges : edgeList.values()) {
      for (Edge<E, K, V> edge : edges) {
        if (edge.getKey().equals(key)) {
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
    int i = edgeList.get(changeableEdge.getFirstVertex().getKey()).indexOf(changeableEdge);
    edgeList.get(changeableEdge.getFirstVertex().getKey()).remove(changeableEdge);
    edgeList.get(changeableEdge.getFirstVertex().getKey()).add(i, newEdge);
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
    return new ArrayList<>(edgeList.get(vertex.getKey()));
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