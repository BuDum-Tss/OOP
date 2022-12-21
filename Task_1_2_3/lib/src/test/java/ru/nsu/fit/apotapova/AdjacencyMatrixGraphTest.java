/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.apotapova;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.fit.apotapova.Graph;
import ru.fit.apotapova.GraphParts.Edge;
import ru.fit.apotapova.GraphParts.Vertex;
import ru.fit.apotapova.GraphTypes.AdjacencyMatrixGraph;

class AdjacencyMatrixGraphTest {

  void initGraph(Graph<String> graph, File filename) throws Exception {
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String str = reader.readLine();
    String[] strList = str.split("\\s+");
    int numberOfVertexes = parseInt(strList[0]);
    int numberOfEdges = parseInt(strList[1]);
    for (int i = 0; i < numberOfVertexes; i++) {
      str = reader.readLine();
      graph.addVertex(str);
    }
    for (int i = 0; i < numberOfEdges; i++) {
      strList = reader.readLine().split("\\s+");
      graph.addEdge(graph.getVertex(strList[0]), parseInt(strList[1]), graph.getVertex(strList[2]));
    }
  }

  @Test
  void addVertex() {
    Graph<String> graph = new AdjacencyMatrixGraph<>();
    Vertex<String> v1 = graph.addVertex("A");
    Vertex<String> v2 = graph.addVertex("B");
    Vertex<String> v3 = graph.addVertex("C");
    Assertions.assertEquals(v1, graph.getVertex("A"));
    Assertions.assertEquals(v2, graph.getVertex("B"));
    Assertions.assertEquals(v3, graph.getVertex("C"));
    Assertions.assertNull(graph.getVertex("D"));
  }

  @Test
  void deleteVertex() {
    Graph<String> graph = new AdjacencyMatrixGraph<>();
    Vertex<String> v1 = graph.addVertex("A");
    Vertex<String> v2 = graph.addVertex("B");
    Vertex<String> v3 = graph.addVertex("C");
    graph.deleteVertex(v1);
    graph.deleteVertex(v2);
    graph.deleteVertex(v3);
    Assertions.assertNull(graph.getVertex("A"));
    Assertions.assertNull(graph.getVertex("B"));
    Assertions.assertNull(graph.getVertex("C"));
  }

  @Test
  void getVertex() {
    Graph<String> graph = new AdjacencyMatrixGraph<>();
    Vertex<String> v1 = graph.addVertex("A");
    Vertex<String> v2 = graph.addVertex("B");
    Vertex<String> v3 = graph.addVertex("C");
    Assertions.assertEquals(v1, graph.getVertex("A"));
    Assertions.assertEquals(v2, graph.getVertex("B"));
    Assertions.assertEquals(v3, graph.getVertex("C"));
    Assertions.assertNull(graph.getVertex("D"));
  }

  @Test
  void changeVertex() {
    Graph<String> graph = new AdjacencyMatrixGraph<>();
    Vertex<String> v0 = graph.addVertex("A");
    Vertex<String> v1 = graph.addVertex("B");
    Vertex<String> v2 = graph.addVertex("C");
    Vertex<String> v3 = new Vertex<>("D");
    graph.changeVertex(v0, v2);
    graph.changeVertex(v1, v3);
    Assertions.assertNull(graph.getVertex("A"));
    Assertions.assertNull(graph.getVertex("B"));
    Assertions.assertNotEquals(v0, graph.getVertex("C"));
    Assertions.assertNotNull(graph.getVertex("D"));
  }

  @Test
  void addEdge() {
    Graph<String> graph = new AdjacencyMatrixGraph<>();
    Vertex<String> v0 = graph.addVertex("A");
    Vertex<String> v1 = graph.addVertex("B");
    Vertex<String> v2 = graph.addVertex("C");
    Edge<String> e0 = graph.addEdge(v0, 1, v1);
    Edge<String> e1 = graph.addEdge(v1, 2, v2);
    Edge<String> e2 = graph.addEdge(v2, 3, v1);
    Assertions.assertEquals(e0, graph.getEdge(1));
    Assertions.assertEquals(e1, graph.getEdge(2));
    Assertions.assertEquals(e2, graph.getEdge(3));
    Assertions.assertNull(graph.getEdge(0));
  }

  @Test
  void deleteEdge() {
    Graph<String> graph = new AdjacencyMatrixGraph<>();
    Vertex<String> v0 = graph.addVertex("A");
    Vertex<String> v1 = graph.addVertex("B");
    Vertex<String> v2 = graph.addVertex("C");
    Edge<String> e0 = graph.addEdge(v0, 1, v1);
    Edge<String> e1 = graph.addEdge(v1, 2, v2);
    Edge<String> e2 = graph.addEdge(v2, 3, v1);
    graph.deleteEdge(e0);
    graph.deleteEdge(e1);
    graph.deleteEdge(e2);
    Assertions.assertNull(graph.getEdge(1));
    Assertions.assertNull(graph.getEdge(2));
    Assertions.assertNull(graph.getEdge(3));
  }

  @Test
  void getEdge() {
    Graph<String> graph = new AdjacencyMatrixGraph<>();
    Vertex<String> v0 = graph.addVertex("A");
    Vertex<String> v1 = graph.addVertex("B");
    Vertex<String> v2 = graph.addVertex("C");
    Edge<String> e0 = graph.addEdge(v0, 1, v1);
    Edge<String> e1 = graph.addEdge(v1, 2, v2);
    Edge<String> e2 = graph.addEdge(v2, 3, v1);
    Assertions.assertEquals(e0, graph.getEdge(1));
    Assertions.assertEquals(e1, graph.getEdge(2));
    Assertions.assertEquals(e2, graph.getEdge(3));
    Assertions.assertNull(graph.getEdge(0));
  }

  @Test
  void changeEdge() {
    Graph<String> graph = new AdjacencyMatrixGraph<>();
    Vertex<String> v0 = graph.addVertex("A");
    Vertex<String> v1 = graph.addVertex("B");
    Vertex<String> v2 = graph.addVertex("C");
    Edge<String> e0 = graph.addEdge(v0, 1, v1);
    Edge<String> e1 = graph.addEdge(v1, 2, v2);
    Edge<String> e2 = graph.addEdge(v2, 3, v1);
    Edge<String> e4 = new Edge<>(v1, 4, v2);
    graph.changeEdge(e0, e2);
    graph.changeEdge(e1, e4);
    Assertions.assertNull(graph.getEdge(1));
    Assertions.assertNull(graph.getEdge(2));
    Assertions.assertEquals(v0, graph.getEdge(3).firstVertex);
    Assertions.assertEquals(v1, graph.getEdge(3).secondVertex);
    Assertions.assertEquals(v1, graph.getEdge(4).firstVertex);
    Assertions.assertEquals(v2, graph.getEdge(4).secondVertex);
  }

  @Test
  void sortVertexes() {
    Graph<String> graph = new AdjacencyMatrixGraph<>();
    try {
      initGraph(graph,
          new File("src/main/resources/test.txt"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    List<Vertex<String>> list = graph.sortVertexes(graph.getVertex("A"));
    Assertions.assertEquals("A", list.get(0).value);
    Assertions.assertEquals("B", list.get(1).value);
    Assertions.assertEquals("D", list.get(2).value);
    Assertions.assertEquals("C", list.get(3).value);
    Assertions.assertEquals("E", list.get(4).value);
    Assertions.assertEquals("F", list.get(5).value);
    Assertions.assertEquals("G", list.get(6).value);
  }
}
