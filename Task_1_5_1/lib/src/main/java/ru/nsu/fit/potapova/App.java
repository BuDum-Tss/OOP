/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.potapova;

public class App {

  public static void main(String[] args) {
    String expression = System.console().readLine();
    ExpressionParser parser = new ExpressionParser();
    Double value = parser.parse(expression);
    System.out.println(value);
  }
}
