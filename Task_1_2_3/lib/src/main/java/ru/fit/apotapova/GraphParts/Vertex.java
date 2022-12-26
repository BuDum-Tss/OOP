package ru.fit.apotapova.GraphParts;

/**
 * Vertex class containing value of vertex.
 *
 * @param <K> - type of key of vertex
 * @param <V> - type of value of vertex
 */
public class Vertex<K, V> {

  private final K key;
  private V value;

  /**
   * Constructor of a class that defines value of vertex.
   *
   * @param key   - value of key
   * @param value - value of vertex
   */
  public Vertex(K key, V value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Gets value of vertex.
   *
   * @return value
   */
  public V getValue() {
    return value;
  }

  /**
   * Sets value of vertex.
   *
   * @param value - value
   */
  public void setValue(V value) {
    this.value = value;
  }

  /**
   * Gets key of vertex.
   *
   * @return - key
   */
  public K getKey() {
    return key;
  }
}