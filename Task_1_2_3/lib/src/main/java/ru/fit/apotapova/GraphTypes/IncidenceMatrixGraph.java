package ru.fit.apotapova.GraphTypes;

import java.util.ArrayList;
import java.util.List;
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
public class IncidenceMatrixGraph<K, V> implements Graph<K, V> {

  List<Vertex<K, V>> vertexList;
  List<Edge<K, V>> edgeList;
  List<List<Integer>> matrix;
  private int numberOfChanges;

  /**
   * Constructor of a class that defines vertexList, edgeList and incidence matrix.
   */

  public IncidenceMatrixGraph() {
    numberOfChanges = 0;
    vertexList = new ArrayList<>();
    edgeList = new ArrayList<>();
    matrix = new ArrayList<>();
  }

  private int getIndex(Vertex<K, V> vertex) {
    if (vertexList.contains(vertex)) {
      return vertexList.indexOf(vertex);
    }
    throw new NoSuchElementException("No vertex from graph");
  }

  private int getIndex(Edge<K, V> edge) {
    if (edgeList.contains(edge)) {
      return edgeList.indexOf(edge);
    }
    throw new NoSuchElementException("No edge from graph");
  }

  private void initializeInMatrix(Vertex<K, V> vertex) {
    int k = getIndex(vertex);
    matrix.add(k, new ArrayList<>());
    for (int i = 0; i < edgeList.size(); i++) {
      matrix.get(k).add(i, 0);
    }
  }

  private void initializeInMatrix(Edge<K, V> edge) {
    int from = getIndex(edge.getFirstVertex());
    int to = getIndex(edge.getSecondVertex());
    for (int i = 0; i < vertexList.size(); i++) {
      if (i == from) {
        matrix.get(i).add(1);
      } else if (i == to) {
        matrix.get(i).add(-1);
      } else {
        matrix.get(i).add(0);
      }
    }
  }

  private void removeFromMatrix(Vertex<K, V> vertex) {
    int k = getIndex(vertex);
    matrix.remove(k);
  }

  private void removeFromMatrix(Edge<K, V> edge) {
    int k = getIndex(edge);
    for (int i = 0; i < vertexList.size(); i++) {
      matrix.get(i).remove(k);
    }
  }

  /**
   * Implements {@link Graph#addVertex(Vertex)} method.
   *
   * @param vertex - added vertex
   * @return - added vertex
   */
  @Override
  public Vertex<K, V> addVertex(Vertex<K, V> vertex) {
    if (vertexList.contains(vertex)) {
      throw new RuntimeException("The vertex is already in the graph");
    }
    vertexList.add(vertex);
    initializeInMatrix(vertex);
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
    removeFromMatrix(vertex);
    vertexList.remove(vertex);
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
    for (Vertex<K, V> vertex : vertexList) {
      if (vertex.getValue().equals(key)) {
        return vertex;
      }
    }
    return null;
  }

  /**
   * Implements {@link Graph#changeVertex(Vertex, Vertex)} method.
   *
   * @param changeableVertex - changeable vertex
   * @param newVertex        - new vertex
   */
  @Override
  public void changeVertex(Vertex<K, V> changeableVertex, Vertex<K, V> newVertex) {
    int k = getIndex(changeableVertex);
    vertexList.remove(k);
    vertexList.add(k, newVertex);
    numberOfChanges++;
  }

  /**
   * Implements {@link Graph#addEdge(Edge)} method.
   *
   * @param edge - added edge
   * @return - added edge
   */
  @Override
  public Edge<K, V> addEdge(Edge<K, V> edge) {
    if (edgeList.contains(edge)) {
      throw new RuntimeException("The edge is already in the graph");
    }
    edgeList.add(edge);
    initializeInMatrix(edge);
    numberOfChanges++;
    return edge;
  }

  /**
   * Implements {@link Graph#deleteEdge(Edge)} method.
   *
   * @param edge - deleted edge
   */
  @Override
  public void deleteEdge(Edge<K, V> edge) {
    removeFromMatrix(edge);
    edgeList.remove(edge);
    numberOfChanges++;
  }

  /**
   * Implements {@link Graph#getEdge(Double)} method.
   *
   * @param length - edge length
   * @return - received edge
   */
  @Override
  public Edge<K, V> getEdge(Double length) {
    for (Edge<K, V> edge : edgeList) {
      if (edge.getLength().equals(length)) {
        return edge;
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
  public void changeEdge(Edge<K, V> changeableEdge, Edge<K, V> newEdge) {
    int k = getIndex(changeableEdge);
    edgeList.remove(k);
    edgeList.add(k, newEdge);
    numberOfChanges++;
  }

  /**
   * Implements {@link Graph#getExitingEdges(Vertex)} method.
   *
   * @param vertex - vertex
   * @return - list of exiting edges
   */
  @Override
  public List<Edge<K, V>> getExitingEdges(Vertex<K, V> vertex) {
    int i = getIndex(vertex);
    List<Edge<K, V>> edges = new ArrayList<>();
    List<Integer> list = matrix.get(i);
    int k = list.size();
    for (int j = 0; j < k; j++) {
      if (list.get(j) == 1) {
        edges.add(edgeList.get(j));
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
