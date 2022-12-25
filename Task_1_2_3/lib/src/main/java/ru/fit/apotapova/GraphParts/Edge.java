package ru.fit.apotapova.GraphParts;

/**
 * Edge class containing adjacent vertices and length.
 *
 * @param <V> type of vertexes
 */
public class Edge<E, K, V> {

  private final E key;
  private Double length;
  private Vertex<K, V> firstVertex;
  private Vertex<K, V> secondVertex;

  /**
   * Constructor of a class that defines adjacent vertices and the length of an edge.
   *
   * @param firstVertex  - vertex from which the edge comes
   * @param key          - edge key
   * @param length       - edge length
   * @param secondVertex - vertex to which the edge goes
   */
  public Edge(Vertex<K, V> firstVertex, E key, Double length, Vertex<K, V> secondVertex) {
    this.firstVertex = firstVertex;
    this.length = length;
    this.secondVertex = secondVertex;
    this.key = key;
  }

  /**
   * Gets edge length.
   *
   * @return edge length
   */
  public Double getLength() {
    return length;
  }

  /**
   * Sets edge length.
   *
   * @param length - edge length
   */
  public void setLength(Double length) {
    this.length = length;
  }

  /**
   * Gets first vertex of edge.
   *
   * @return - first vertex
   */
  public Vertex<K, V> getFirstVertex() {
    return firstVertex;
  }

  /**
   * Sets first vertex of edge.
   *
   * @param firstVertex - first vertex
   */
  public void setFirstVertex(Vertex<K, V> firstVertex) {
    this.firstVertex = firstVertex;
  }

  /**
   * Gets second vertex of edge.
   *
   * @return - first vertex
   */
  public Vertex<K, V> getSecondVertex() {
    return secondVertex;
  }

  /**
   * Sets second vertex of edge.
   *
   * @param secondVertex - first vertex
   */
  public void setSecondVertex(Vertex<K, V> secondVertex) {
    this.secondVertex = secondVertex;
  }

  /**
   * Gets edge key.
   *
   * @return - key
   */
  public E getKey() {
    return key;
  }
}
