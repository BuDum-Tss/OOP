package ru.fit.apotapova.GraphTypes;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
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
public class AdjacencyMatrixGraph<K, V> implements Graph<K, V> {

  private final List<Vertex<K, V>> vertexList;
  private final List<List<Edge<K, V>>> matrix;

  /**
   * Constructor of a class that defines vertexList, edgeList and adjacency matrix.
   */
  public AdjacencyMatrixGraph() {
    vertexList = new ArrayList<>();
    matrix = new ArrayList<>();
  }

  private int getIndex(Vertex<K, V> vertex) {
    if (vertexList.contains(vertex)) {
      return vertexList.indexOf(vertex);
    }
    throw new NoSuchElementException("No vertex from graph");
  }

  private Point getIndex(Edge<K, V> edge) {
    if (getMatrixLineList(edge.getFirstVertex()).contains(edge)) {
      return new Point(getIndex(edge.getFirstVertex()),
          getMatrixLineList(edge.getFirstVertex()).indexOf(edge));
    }
    throw new NoSuchElementException("No edge from graph");
  }

  private List<Edge<K, V>> getMatrixLineList(Vertex<K, V> vertex) {
    return matrix.get(getIndex(vertex));
  }

  private void initializeVertexInMatrix(Vertex<K, V> vertex) {
    int k = getIndex(vertex);
    for (int i = 0; i < vertexList.size(); i++) {
      if (k != i && matrix.get(i) != null) {
        matrix.get(i).add(k, null);
      }
    }
    matrix.add(k, new ArrayList<>());
    for (int i = 0; i < vertexList.size(); i++) {
      matrix.get(k).add(i, null);
    }
  }

  private void deleteVertexFromMatrix(Vertex<K, V> vertex) {
    int k = getIndex(vertex);
    for (int i = 0; i < vertexList.size(); i++) {
      if (k != i && matrix.get(i) != null) {
        matrix.get(i).remove(k);
      }
    }
    matrix.add(k, new ArrayList<>());
    matrix.remove(k);
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
    initializeVertexInMatrix(vertex);
    return vertex;
  }

  /**
   * Implements {@link Graph#deleteVertex(Vertex)} method.
   *
   * @param vertex - deleted vertex
   */
  @Override
  public void deleteVertex(Vertex<K, V> vertex) {
    deleteVertexFromMatrix(vertex);
    vertexList.remove(vertex);
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
      if (vertex.getKey().equals(key)) {
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
    int index = getIndex(changeableVertex);
    vertexList.remove(changeableVertex);
    vertexList.remove(newVertex);
    vertexList.add(index, newVertex);
  }

  /**
   * Implements {@link Graph#addEdge(Edge)} method.
   *
   * @param edge - added edge
   * @return - added edge
   */
  @Override
  public Edge<K, V> addEdge(Edge<K, V> edge) {
    if (matrix.get(vertexList.indexOf(edge.getFirstVertex())).contains(edge)) {
      throw new RuntimeException("The edge is already in the graph");
    }
    getMatrixLineList(edge.getFirstVertex()).remove(getIndex(edge.getSecondVertex()));
    getMatrixLineList(edge.getFirstVertex()).add(getIndex(edge.getSecondVertex()), edge);
    return edge;
  }

  /**
   * Implements {@link Graph#deleteEdge(Edge)} method.
   *
   * @param edge - deleted edge
   */
  @Override
  public void deleteEdge(Edge<K, V> edge) {
    Point p = getIndex(edge);
    matrix.get(p.x).add(p.y, null);
    matrix.get(p.x).remove(edge);
  }

  /**
   * Implements {@link Graph#getEdge(Double)} method.
   *
   * @param length - edge length
   * @return - received edge
   */
  @Override
  public Edge<K, V> getEdge(Double length) {
    for (List<Edge<K, V>> edges : matrix) {
      for (Edge<K, V> edge : edges) {
        if (edge != null && edge.getLength().equals(length)) {
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
  public void changeEdge(Edge<K, V> changeableEdge, Edge<K, V> newEdge) {
    Point p = getIndex(changeableEdge);
    matrix.get(p.x).remove(changeableEdge);
    matrix.get(p.x).add(p.y, newEdge);
    newEdge.setFirstVertex(changeableEdge.getFirstVertex());
    newEdge.setSecondVertex(changeableEdge.getSecondVertex());
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
    List<Edge<K, V>> edges = new ArrayList<>(matrix.get(i));
    edges.removeIf(Objects::isNull);
    return edges;
  }
}
