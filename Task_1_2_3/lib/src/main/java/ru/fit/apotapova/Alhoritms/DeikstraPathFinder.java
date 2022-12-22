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

public class DeikstraPathFinder<T> implements PathFinder<T> {

  Graph<T> graph;
  List<Vertex<T>> newVertexList;
  Queue<Pair<Double, Vertex<T>>> frontier;

  public DeikstraPathFinder(Graph<T> graph) {
    this.graph = graph;
  }

  /**
   * Implements  {@link PathFinder#sortVertexes(Vertex)} method.
   *
   * @param vertex - starting vertex
   * @return - sorted list of vertexes
   */
  @Override
  public List<Vertex<T>> sortVertexes(Vertex<T> vertex) {
    newVertexList = new ArrayList<>();
    frontier = new PriorityQueue<>(Comparator.comparingDouble(Pair::getFirst));
    Pair<Double, Vertex<T>> pair = new Pair<>(0.0, vertex);
    frontier.add(pair);
    while (!frontier.isEmpty()) {
      Double k = frontier.peek().getFirst();
      Vertex<T> v = frontier.poll().getSecond();
      newVertexList.add(v);
      List<Edge<T>> edges = graph.getExitingEdges(v);
      for (Edge<T> edge : edges) {
        if (!newVertexList.contains(edge.secondVertex)) {
          Double sum = k + edge.length;
          Iterator<Pair<Double, Vertex<T>>> iterator = frontier.iterator();
          boolean hasVertex = false;
          while (iterator.hasNext()) {
            Pair<Double, Vertex<T>> p = iterator.next();
            if (p.getFirst() > sum && p.getSecond() == edge.secondVertex) {
              frontier.remove(p);
              break;
            } else if (p.getFirst() <= sum && p.getSecond() == edge.secondVertex) {
              hasVertex = true;
              break;
            }
          }
          if (!hasVertex) {
            frontier.add(new Pair<>(sum, edge.secondVertex));
          }
        }
      }
    }
    return newVertexList;
  }
}
