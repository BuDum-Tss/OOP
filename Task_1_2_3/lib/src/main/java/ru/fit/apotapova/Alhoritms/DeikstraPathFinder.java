package ru.fit.apotapova.Alhoritms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import org.apache.commons.math3.util.Pair;
import ru.fit.apotapova.Graph;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;
import ru.fit.apotapova.PathFinder;

/**
 * A class that implements a {@link PathFinder} using a deikstra algorithm.
 *
 * @param <K> - type of key of vertex
 * @param <V> - type of value of vertex
 */
public class DeikstraPathFinder<K, V> implements PathFinder<K, V> {

  private final Graph<K, V> graph;
  private int numberOfChanges;
  private List<Vertex<K, V>> vertexList;
  private List<List<Vertex<K, V>>> sortedVertexes;
  private Queue<Pair<Double, Vertex<K, V>>> frontier;

  public DeikstraPathFinder(Graph<K, V> graph) {
    this.graph = graph;
    vertexList = new ArrayList<>();
    sortedVertexes = new ArrayList<>();
  }

  private boolean isChanged() {
    return numberOfChanges < graph.getNumberOfChanges();
  }

  /**
   * Implements  {@link PathFinder#sortVertexes(Vertex)} method.
   *
   * @param vertex - starting vertex
   * @return - sorted list of vertexes
   */
  @Override
  public List<Vertex<K, V>> sortVertexes(Vertex<K, V> vertex) {
    if (!isChanged() && vertexList.contains(vertex)) {
      return sortedVertexes.get(vertexList.indexOf(vertex));
    }
    List<Vertex<K, V>> sortedList;
    sortedList = new ArrayList<>();
    frontier = new PriorityQueue<>(Comparator.comparingDouble(Pair::getFirst));
    Pair<Double, Vertex<K, V>> pair = new Pair<>(0.0, vertex);
    frontier.add(pair);
    while (!frontier.isEmpty()) {
      Double k = frontier.peek().getFirst();
      Vertex<K, V> v = frontier.poll().getSecond();
      sortedList.add(v);
      List<Edge<K, V>> edges = graph.getExitingEdges(v);
      for (Edge<K, V> edge : edges) {
        if (!sortedList.contains(edge.getSecondVertex())) {
          Double sum = k + edge.getLength();
          Iterator<Pair<Double, Vertex<K, V>>> iterator = frontier.iterator();
          boolean hasVertex = false;
          while (iterator.hasNext()) {
            Pair<Double, Vertex<K, V>> p = iterator.next();
            if (p.getFirst() > sum && p.getSecond() == edge.getSecondVertex()) {
              frontier.remove(p);
              break;
            } else if (p.getFirst() <= sum && p.getSecond() == edge.getSecondVertex()) {
              hasVertex = true;
              break;
            }
          }
          if (!hasVertex) {
            frontier.add(new Pair<>(sum, edge.getSecondVertex()));
          }
        }
      }
    }
    if (!vertexList.contains(vertex)) {
      vertexList.add(vertex);
      sortedVertexes.add(vertexList);
    } else {
      sortedVertexes.remove(vertexList.indexOf(vertex));
      sortedVertexes.add(vertexList.indexOf(vertex), sortedList);
    }
    return sortedList;
  }
}
