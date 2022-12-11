package ru.nsu.fit.apotapova.GraphTypes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.fit.apotapova.Graph;
import ru.nsu.fit.apotapova.GraphParts.Edge;
import ru.nsu.fit.apotapova.GraphParts.Vertex;

public class AdjacencyListsGraph<T, Number> implements Graph<T, Number> {

  List<Vertex<T>> vertexList;
  List<List<Edge<Number>>> edgeList;

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

  private Point getIndex(Edge<Number> edge) {
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

  @Override
  public Vertex<T> addVertex(Vertex<T> vertex) {
    vertexList.add(vertex);
    int k = vertexList.indexOf(vertex);
    edgeList.add(k, new ArrayList<>());
    return vertex;
  }

  @Override
  public void deleteVertex(Vertex<T> vertex) {
    int k = vertexList.indexOf(vertex);
    vertexList.remove(vertex);
    edgeList.remove(k);
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
    try {
      int v = getIndex(changeableVertex);
      vertexList.remove(v);
      vertexList.add(v, newVertex);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Edge<Number> addEdge(Edge<Number> edge) {
    try {
      int k = getIndex(edge.firstVertex);
      edgeList.get(k).add(edge);
      return edge;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void deleteEdge(Edge<Number> edge) {
    Point p = getIndex(edge);
    edgeList.get(p.x).remove(edge);
  }

  @Override
  public Edge<Number> getEdge(Number length) {
    for (List<Edge<Number>> nEdgeList : edgeList) {
      for (Edge<Number> edge : nEdgeList) {
        if (edge.length == length) {
          return edge;
        }
      }
    }
    return null;
  }

  @Override
  public void changeEdge(Edge<Number> changeableEdge, Edge<Number> newEdge) {
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
}
