package ru.fit.apotapova.GraphParts;

/**
 * Edge class containing adjacent vertices and length.
 *
 * @param <V> type of vertexes
 */
public class Edge<K, V> {

  private Double length;
  private Vertex<K, V> firstVertex;
  private Vertex<K, V> secondVertex;

  /**
   * Constructor of a class that defines adjacent vertices and the length of an edge.
   *
   * @param firstVertex  - vertex from which the edge comes
   * @param length       - edge length
   * @param secondVertex - vertex to which the edge goes
   */
  public Edge(Vertex<K, V> firstVertex, Double length, Vertex<K, V> secondVertex) {
    this.firstVertex = firstVertex;
    this.length = length;
    this.secondVertex = secondVertex;
  }

  public Double getLength() {
    return length;
  }

  public void setLength(Double length) {
    this.length = length;
  }

  public Vertex<K, V> getFirstVertex() {
    return firstVertex;
  }

  public void setFirstVertex(Vertex<K, V> firstVertex) {
    this.firstVertex = firstVertex;
  }

  public Vertex<K, V> getSecondVertex() {
    return secondVertex;
  }

  public void setSecondVertex(Vertex<K, V> secondVertex) {
    this.secondVertex = secondVertex;
  }
}
