package ru.nsu.fit.apotapova.GraphTypes;

import java.util.ArrayList;
import java.util.List;
import ru.nsu.fit.apotapova.Graph;
import ru.nsu.fit.apotapova.GraphParts.Edge;
import ru.nsu.fit.apotapova.GraphParts.Vertex;

public class IncidenceMatrixGraph<T, Number> implements Graph<T, Number> {

  List<Vertex<T>> vertexList;
  List<Edge<Number>> edgeList;
  List<List<Integer>> matrix;

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

  private int getIndex(Edge<Number> edge) throws Exception {
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
      matrix.add(k, new ArrayList<>());
      for (int i = 0; i < vertexList.size(); i++) {
        matrix.get(k).add(i, null);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void initializeInMatrix(Edge<Number> edge) {
    try {
      int k = getIndex(edge);
      int from = getIndex(edge.firstVertex);
      int to = getIndex(edge.secondVertex);
      for (int i = 0; i < vertexList.size(); i++) {
        if (matrix.get(i) != null) {
          if (i == from) {
            matrix.get(i).add(k, 1);
          } else if (i == to) {
            matrix.get(i).add(k, -1);
          } else {
            matrix.get(i).add(k, 0);
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void removeFromMatrix(Vertex<T> vertex) {
    int k;
    try {
      k = getIndex(vertex);
      matrix.remove(k);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void removeFromMatrix(Edge<Number> edge) {
    int k;
    try {
      k = getIndex(edge);
      for (int i = 0; i < vertexList.size(); i++) {
        if (matrix.get(i) != null) {
          matrix.get(i).remove(k);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Vertex<T> addVertex(Vertex<T> vertex) {
    vertexList.add(vertex);
    initializeInMatrix(vertex);
    return vertex;
  }

  @Override
  public void deleteVertex(Vertex<T> vertex) {
    removeFromMatrix(vertex);
    vertexList.remove(vertex);
  }

  @Override
  public Vertex<T> getVertex(T value) {
    for (int i = 0; i < vertexList.size(); i++) {
      if (vertexList.get(i).value.equals(value)) {
        return vertexList.get(i);
      }
    }
    return null;
  }

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

  @Override
  public Edge<Number> addEdge(Edge<Number> edge) {
    edgeList.add(edge);
    initializeInMatrix(edge);
    return edge;
  }

  @Override
  public void deleteEdge(Edge<Number> edge) {
    removeFromMatrix(edge);
    edgeList.remove(edge);
  }

  @Override
  public Edge<Number> getEdge(Number length) {
    for (int i = 0; i < edgeList.size(); i++) {
      if (edgeList.get(i).length == length) {
        return edgeList.get(i);
      }
    }
    return null;
  }

  @Override
  public void changeEdge(Edge<Number> changeableEdge, Edge<Number> newEdge) {
    try {
      int k = getIndex(changeableEdge);
      edgeList.remove(k);
      edgeList.add(k, newEdge);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
