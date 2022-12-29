package ru.nsu.fit.potapova;

/**
 * Parses the expression and launches operations in the correct order.
 */
public class ExpressionParser {

  private String stringExpression;

  public Double parse(String stringExpression) {
    this.stringExpression = stringExpression;
    Expression expression = new Expression(this);
    return expression.getValue();
  }

  public String getExpression() {
    String[] temp = stringExpression.split(" ", 2);
    if (temp.length == 2) {
      stringExpression = temp[1];
    }
    return temp[0];
  }
}
