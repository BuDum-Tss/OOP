package ru.nsu.fit.apotapova;

import java.util.List;
import ru.nsu.fit.apotapova.GraphParts.Edge;
import ru.nsu.fit.apotapova.GraphParts.Vertex;

public interface Graph<T, Number> {

  Vertex<T> addVertex(Vertex<T> vertex);

  void deleteVertex(Vertex<T> vertex);

  Vertex<T> getVertex(T value);

  void changeVertex(Vertex<T> changeableVertex, Vertex<T> newVertex);

  Edge<Number> addEdge(Edge<Number> edge);

  void deleteEdge(Edge<Number> edge);

  Edge<Number> getEdge(Number length);

  void changeEdge(Edge<Number> changeableEdge, Edge<Number> newEdge);


  default Vertex<T> addVertex(T value) {
    return addVertex(new Vertex<>(value));
  }

  default Edge<Number> addEdge(Vertex<T> firstVertex, Number length, Vertex<T> secondVertex) {
    return addEdge(new Edge<>(firstVertex, length, secondVertex));
  }

  default List<Vertex<T>> sortVertexes(Vertex<T> vertex) {
    return null;
  }
}
