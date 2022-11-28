package ru.nsu.fit.apotapova.Graphs;

import java.util.List;
import ru.nsu.fit.apotapova.GraphParts.Edge;
import ru.nsu.fit.apotapova.GraphParts.Vertex;

public interface Graph<T ,Number> {
  //Vertex operations
  void addVertex(T value);
  Vertex<T> getVertex(T value);
  void setVertex(Vertex<T> vertex, Vertex<T> newVertex);
  void removeVertex(T value);

  //Edge operations
  void addEdge(Number length);
  Edge<Number> getEdge(Number length);
  void setEdge(Edge<Number> edge, Edge<Number> newEdge);
  void removeEdge(Number length);

  //sorting algorithm for vertexes
  List<Vertex<T>> sortVertices();

}
