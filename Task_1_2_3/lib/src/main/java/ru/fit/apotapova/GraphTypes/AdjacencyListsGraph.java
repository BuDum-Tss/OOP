package ru.fit.apotapova.GraphTypes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
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
public class AdjacencyListsGraph<K, V> implements Graph<K, V> {

  public List<Vertex<K, V>> vertexList;
  List<List<Edge<K, V>>> edgeList;
  private int numberOfChanges;

  /**
   * Constructor of a class that defines vertexList and edgeList.
   */
  public AdjacencyListsGraph() {
    numberOfChanges = 0;
    this.vertexList = new ArrayList<>();
    this.edgeList = new ArrayList<>();
  }

  private int getIndex(Vertex<K, V> vertex) {
    if (vertexList.contains(vertex)) {
      return vertexList.indexOf(vertex);
    }
    throw new NoSuchElementException("No vertex from graph");
  }

  private Point getIndex(Edge<K, V> edge) {
    Point p = new Point();
    p.x = getIndex(edge.getFirstVertex());
    if (edgeList.get(p.x).contains(edge)) {
      p.y = edgeList.get(p.x).indexOf(edge);
      return p;
    }
    throw new NoSuchElementException("No edge from graph");
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
    int k = vertexList.indexOf(vertex);
    edgeList.add(k, new ArrayList<>());
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
    int k = getIndex(vertex);
    vertexList.remove(vertex);
    edgeList.remove(k);
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
    int v = getIndex(changeableVertex);
    vertexList.remove(v);
    vertexList.add(v, newVertex);
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
    int k = getIndex(edge.getFirstVertex());
    edgeList.get(k).add(edge);
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
    Point p = getIndex(edge);
    edgeList.get(p.x).remove(edge);
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
    for (List<Edge<K, V>> edgeList : edgeList) {
      for (Edge<K, V> edge : edgeList) {
        if (edge.getLength().equals(length)) {
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
    Point e = getIndex(changeableEdge);
    edgeList.get(e.x).remove(changeableEdge);
    edgeList.get(e.x).add(e.y, newEdge);
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
  public List<Edge<K, V>> getExitingEdges(Vertex<K, V> vertex) {
    return edgeList.get(getIndex(vertex));
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