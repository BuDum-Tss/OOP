package ru.fit.apotapova.GraphParts;

/**
 * Edge class containing adjacent vertices and length.
 *
 * @param <T> type of vertexes
 */
public class Edge<T> {

  public Integer length;
  public Vertex<T> firstVertex;
  public Vertex<T> secondVertex;

  /**
   * Constructor of a class that defines adjacent vertices and the length of an edge.
   *
   * @param firstVertex  - vertex from which the edge comes
   * @param length       - edge length
   * @param secondVertex - vertex to which the edge goes
   */
  public Edge(Vertex<T> firstVertex, Integer length, Vertex<T> secondVertex) {
    this.firstVertex = firstVertex;
    this.length = length;
    this.secondVertex = secondVertex;
  }
}
