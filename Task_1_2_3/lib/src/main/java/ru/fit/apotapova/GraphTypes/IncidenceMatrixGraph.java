package ru.fit.apotapova.GraphTypes;

import java.util.ArrayList;
import java.util.List;
import ru.fit.apotapova.Graph;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * A class that implements a {@link Graph} using an incidence matrix.
 *
 * @param <T> - vertex type
 */
public class IncidenceMatrixGraph<T> implements Graph<T> {

  List<Vertex<T>> vertexList;
  List<Edge<T>> edgeList;
  List<List<Integer>> matrix;

  /**
   * Constructor of a class that defines vertexList, edgeList and incidence matrix.
   */

  public IncidenceMatrixGraph() {
    vertexList = new ArrayList<>();
    edgeList = new ArrayList<>();
    matrix = new ArrayList<>();
  }

  private int getIndex(Vertex<T> vertex) throws Exception {
    if (vertexList.contains(vertex)) {
      return vertexList.indexOf(vertex);
    } else {
      throw new Exception("No vertex from graph");
    }
  }

  private int getIndex(Edge<T> edge) throws Exception {
    if (edgeList.contains(edge)) {
      return edgeList.indexOf(edge);
    } else {
      throw new Exception("No edge from graph");
    }
  }

  private void initializeInMatrix(Vertex<T> vertex) {
    int k;
    try {
      k = getIndex(vertex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    matrix.add(k, new ArrayList<>());
    for (int i = 0; i < edgeList.size(); i++) {
      matrix.get(k).add(i, 0);
    }
  }

  private void initializeInMatrix(Edge<T> edge) {
    int from;
    int to;
    try {
      from = getIndex(edge.firstVertex);
      to = getIndex(edge.secondVertex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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

  private void removeFromMatrix(Vertex<T> vertex) {
    int k;
    try {
      k = getIndex(vertex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    matrix.remove(k);
  }

  private void removeFromMatrix(Edge<T> edge) {
    int k;
    try {
      k = getIndex(edge);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

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
  public Vertex<T> addVertex(Vertex<T> vertex) {
    if (vertexList.contains(vertex)) {
      try {
        throw new Exception("The vertex is already in the graph");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    vertexList.add(vertex);
    initializeInMatrix(vertex);
    return vertex;
  }

  /**
   * Implements {@link Graph#deleteVertex(Vertex)} method.
   *
   * @param vertex - deleted vertex
   */
  @Override
  public void deleteVertex(Vertex<T> vertex) {
    removeFromMatrix(vertex);
    vertexList.remove(vertex);
  }

  /**
   * Implements {@link Graph#getVertex(Object)} method.
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
      int k = getIndex(changeableVertex);
      vertexList.remove(k);
      vertexList.add(k, newVertex);
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
    if (edgeList.contains(edge)) {
      try {
        throw new Exception("The edge is already in the graph");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    edgeList.add(edge);
    initializeInMatrix(edge);
    return edge;
  }

  /**
   * Implements {@link Graph#deleteEdge(Edge)} method.
   *
   * @param edge - deleted edge
   */
  @Override
  public void deleteEdge(Edge<T> edge) {
    removeFromMatrix(edge);
    edgeList.remove(edge);
  }

  /**
   * Implements {@link Graph#getEdge(Integer)} method.
   *
   * @param length - edge length
   * @return - received edge
   */
  @Override
  public Edge<T> getEdge(Integer length) {
    for (Edge<T> edge : edgeList) {
      if (edge.length.equals(length)) {
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
  public void changeEdge(Edge<T> changeableEdge, Edge<T> newEdge) {
    try {
      int k = getIndex(changeableEdge);
      edgeList.remove(k);
      edgeList.add(k, newEdge);
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
    int i;
    try {
      i = getIndex(vertex);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    List<Edge<T>> edges = new ArrayList<>();
    List<Integer> list = matrix.get(i);
    int k = list.size();
    for (int j = 0; j < k; j++) {
      if (list.get(j) == 1) {
        edges.add(edgeList.get(j));
      }
    }
    return edges;
  }
}
