package ru.fit.apotapova.GraphParts;

/**
 * Vertex class containing value of vertex.
 *
 * @param <T> - type of vertex
 */
public class Vertex<T> {

  public T value;

  /**
   * Constructor of a class that defines value of vertex.
   *
   * @param value - value of vertex
   */
  public Vertex(T value) {
    this.value = value;
  }

}