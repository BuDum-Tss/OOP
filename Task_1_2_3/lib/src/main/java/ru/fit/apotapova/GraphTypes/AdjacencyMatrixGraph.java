package ru.fit.apotapova.GraphTypes;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ru.fit.apotapova.Graph;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;

/**
 * A class that implements a {@link Graph} using an adjacency matrix.
 *
 * @param <T> - vertex type
 */
public class AdjacencyMatrixGraph<T> implements Graph<T> {

  private final List<Vertex<T>> vertexList;
  private final List<List<Edge<T>>> matrix;

  /**
   * Constructor of a class that defines vertexList, edgeList and adjacency matrix.
   */
  public AdjacencyMatrixGraph() {
    vertexList = new ArrayList<>();
    matrix = new ArrayList<>();
  }

  private int getIndex(Vertex<T> vertex) throws Exception {
    if (vertexList.contains(vertex)) {
      return vertexList.indexOf(vertex);
    } else {
      throw new Exception("No vertex from graph");
    }
  }

  public Point getIndex(Edge<T> edge) throws Exception {
    return new Point(getIndex(edge.firstVertex),
        getMatrixLineList(edge.firstVertex).indexOf(edge));
  }

  private List<Edge<T>> getMatrixLineList(Vertex<T> vertex) throws Exception {
    return matrix.get(getIndex(vertex));
  }

  private void initializeVertexInMatrix(Vertex<T> vertex) {
    int k;
    try {
      k = getIndex(vertex);
      for (int i = 0; i < vertexList.size(); i++) {
        if (k != i && matrix.get(i) != null) {
          matrix.get(i).add(k, null);
        }
      }
      matrix.add(k, new ArrayList<>());
      for (int i = 0; i < vertexList.size(); i++) {
        matrix.get(k).add(i, null);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void deleteVertexFromMatrix(Vertex<T> vertex) throws Exception {
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
  public Vertex<T> addVertex(Vertex<T> vertex) {
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
  public void deleteVertex(Vertex<T> vertex) {
    try {
      deleteVertexFromMatrix(vertex);
      vertexList.remove(vertex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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
    int index;
    try {
      index = getIndex(changeableVertex);
      vertexList.remove(changeableVertex);
      vertexList.remove(newVertex);
      vertexList.add(index, newVertex);
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
      getMatrixLineList(edge.firstVertex).remove(getIndex(edge.secondVertex));
      getMatrixLineList(edge.firstVertex).add(getIndex(edge.secondVertex), edge);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return edge;
  }

  /**
   * Implements {@link Graph#deleteEdge(Edge)} method.
   *
   * @param edge - deleted edge
   */
  @Override
  public void deleteEdge(Edge<T> edge) {
    Point p;
    try {
      p = getIndex(edge);
      matrix.get(p.x).add(p.y, null);
      matrix.get(p.x).remove(edge);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Implements {@link Graph#getEdge(Integer)} method.
   *
   * @param length - edge length
   * @return - received edge
   */
  @Override
  public Edge<T> getEdge(Integer length) {
    for (List<Edge<T>> edges : matrix) {
      for (Edge<T> edge : edges) {
        if (edge != null && edge.length.equals(length)) {
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
    Point p;
    try {
      p = getIndex(changeableEdge);
      matrix.get(p.x).remove(changeableEdge);
      matrix.get(p.x).add(p.y, newEdge);
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
    int i;
    try {
      i = getIndex(vertex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    List<Edge<T>> edges = new ArrayList<>(matrix.get(i));
    edges.removeIf(Objects::isNull);
    return edges;
  }
}
