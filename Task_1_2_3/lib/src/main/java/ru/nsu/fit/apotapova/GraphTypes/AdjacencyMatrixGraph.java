package ru.nsu.fit.apotapova.GraphTypes;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.fit.apotapova.Graph;
import ru.nsu.fit.apotapova.GraphParts.Edge;
import ru.nsu.fit.apotapova.GraphParts.Vertex;

public class AdjacencyMatrixGraph<T, Number> implements Graph<T, Number> {

  private final List<Vertex<T>> vertexList;
  private final List<List<Edge<Number>>> matrix;

  public AdjacencyMatrixGraph() {
    vertexList = new ArrayList<>();
    matrix = new ArrayList<>();
  }

  private int getVertexIndex(Vertex<T> vertex) throws Exception {
    if (vertexList.contains(vertex)) {
      return vertexList.indexOf(vertex);
    } else {
      throw new Exception("No vertex from graph");
    }
  }

  public Point getEdgeIndex(Edge<Number> edge) throws Exception {
    return new Point(getVertexIndex(edge.firstVertex),
        getMatrixLineList(edge.firstVertex).indexOf(edge));
  }

  private List<Edge<Number>> getMatrixLineList(Vertex<T> vertex) throws Exception {
    return matrix.get(getVertexIndex(vertex));
  }

  private void initializeVertexInMatrix(Vertex<T> vertex) {
    int k;
    try {
      k = getVertexIndex(vertex);
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
    int k = getVertexIndex(vertex);
    for (int i = 0; i < vertexList.size(); i++) {
      if (k != i && matrix.get(i) != null) {
        matrix.get(i).remove(k);
      }
    }
    matrix.add(k, new ArrayList<>());
    matrix.remove(k);
  }

  @Override
  public Vertex<T> addVertex(Vertex<T> vertex) {
    vertexList.add(vertex);
    initializeVertexInMatrix(vertex);
    return vertex;
  }

  @Override
  public void deleteVertex(Vertex<T> vertex) {
    try {
      deleteVertexFromMatrix(vertex);
      vertexList.remove(vertex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Vertex<T> getVertex(T value) {
    for (Vertex<T> vertex : vertexList) {
      if (vertex.value.equals(value)) {
        return vertex;
      }
    }
    return null;
  }

  @Override
  public void changeVertex(Vertex<T> changeableVertex, Vertex<T> newVertex) {
    int index;
    try {
      index = getVertexIndex(changeableVertex);
      vertexList.remove(changeableVertex);
      vertexList.remove(newVertex);
      vertexList.add(index, newVertex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Edge<Number> addEdge(Edge<Number> edge) {
    try {
      getMatrixLineList(edge.firstVertex).remove(getVertexIndex(edge.secondVertex));
      getMatrixLineList(edge.firstVertex).add(getVertexIndex(edge.secondVertex), edge);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return edge;
  }

  @Override
  public void deleteEdge(Edge<Number> edge) {
    Point p;
    try {
      p = getEdgeIndex(edge);
      matrix.get(p.x).add(p.y, null);
      matrix.get(p.x).remove(edge);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Edge<Number> getEdge(Number length) {
    for (List<Edge<Number>> edges : matrix) {
      for (Edge<Number> edge : edges) {
        if (edge != null && edge.length == length) {
          return edge;
        }
      }
    }
    return null;
  }

  @Override
  public void changeEdge(Edge<Number> changeableEdge, Edge<Number> newEdge) {
    Point p;
    try {
      p = getEdgeIndex(changeableEdge);
      matrix.get(p.x).remove(changeableEdge);
      matrix.get(p.x).add(p.y, newEdge);
      newEdge.firstVertex = changeableEdge.firstVertex;
      newEdge.secondVertex = changeableEdge.secondVertex;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
