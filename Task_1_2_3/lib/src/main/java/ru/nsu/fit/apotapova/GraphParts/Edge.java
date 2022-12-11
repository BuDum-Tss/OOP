package ru.nsu.fit.apotapova.GraphParts;


public class Edge<Number> {

  public Number length;
  public Vertex firstVertex;
  public Vertex secondVertex;

  public Edge(Vertex firstVertex, Number length, Vertex secondVertex) {
    this.firstVertex = firstVertex;
    this.length = length;
    this.secondVertex = secondVertex;
  }
}
