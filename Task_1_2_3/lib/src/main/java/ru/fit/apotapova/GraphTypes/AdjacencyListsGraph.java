package ru.fit.apotapova.GraphTypes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import ru.fit.apotapova.Graph;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * A class that implements a {@link Graph} using an adjacency list.
 *
 * @param <T> - vertex type
 */
public class AdjacencyListsGraph<T> implements Graph<T> {

  public List<Vertex<T>> vertexList;
  List<List<Edge<T>>> edgeList;

  /**
   * Constructor of a class that defines vertexList and edgeList.
   */
  public AdjacencyListsGraph() {
    this.vertexList = new ArrayList<>();
    this.edgeList = new ArrayList<>();
  }

  private int getIndex(Vertex<T> vertex) throws Exception {
    if (vertexList.contains(vertex)) {
      return vertexList.indexOf(vertex);
    } else {
      throw new Exception("No vertex from graph");
    }
  }

  private Point getIndex(Edge<T> edge) {
    Point p = new Point();
    try {
      p.x = getIndex(edge.firstVertex);
      if (edgeList.get(p.x).contains(edge)) {
        p.y = edgeList.get(p.x).indexOf(edge);
        return p;
      } else {
        throw new Exception("No edge from graph");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Implements {@link Graph#addVertex(Vertex)} method.
   *
   * @param vertex - added vertex
   * @return - added vertex
   */
  @Override
  public Vertex<T> addVertex(Vertex<T> vertex) {
    vertexList.add(vertex);
    int k = vertexList.indexOf(vertex);
    edgeList.add(k, new ArrayList<>());
    return vertex;
  }

  /**
   * Implements {@link Graph#deleteVertex(Vertex)} method.
   *
   * @param vertex - deleted vertex
   */
  @Override
  public void deleteVertex(Vertex<T> vertex) {
    int k = vertexList.indexOf(vertex);
    vertexList.remove(vertex);
    edgeList.remove(k);
  }

  /**
   * Implements {@link Graph#getVertex(T)} method.
   *
   * @param value - value of vertex
   * @return - received vertex
   */
  @Override
  public Vertex<T> getVertex(T value) {
    for (Vertex<T> vertex : vertexList) {
      if (vertex.value.equals(value)) {
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
  public void changeVertex(Vertex<T> changeableVertex, Vertex<T> newVertex) {
    try {
      int v = getIndex(changeableVertex);
      vertexList.remove(v);
      vertexList.add(v, newVertex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Implements {@link Graph#addEdge(Edge)} method.
   *
   * @param edge - added edge
   * @return - added edge
   */
  @Override
  public Edge<T> addEdge(Edge<T> edge) {
    try {
      int k = getIndex(edge.firstVertex);
      edgeList.get(k).add(edge);
      return edge;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Implements {@link Graph#deleteEdge(Edge)} method.
   *
   * @param edge - deleted edge
   */
  @Override
  public void deleteEdge(Edge<T> edge) {
    Point p = getIndex(edge);
    edgeList.get(p.x).remove(edge);
  }

  /**
   * Implements {@link Graph#getEdge(Integer)} method.
   *
   * @param length - edge length
   * @return - received edge
   */
  @Override
  public Edge<T> getEdge(Integer length) {
    for (List<Edge<T>> edgeList : edgeList) {
      for (Edge<T> edge : edgeList) {
        if (edge.length.equals(length)) {
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
  public void changeEdge(Edge<T> changeableEdge, Edge<T> newEdge) {
    try {
      Point e = getIndex(changeableEdge);
      edgeList.get(e.x).remove(changeableEdge);
      edgeList.get(e.x).add(e.y, newEdge);
      newEdge.firstVertex = changeableEdge.firstVertex;
      newEdge.secondVertex = changeableEdge.secondVertex;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Implements {@link Graph#getExitingEdges(Vertex)} method.
   *
   * @param vertex - vertex
   * @return - list of exiting edges
   */
  @Override
  public List<Edge<T>> getExitingEdges(Vertex<T> vertex) {
    try {
      return edgeList.get(getIndex(vertex));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}