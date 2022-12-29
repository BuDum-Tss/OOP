package ru.nsu.fit.potapova;

import static java.lang.Double.parseDouble;

public class Expression {

  private final Operation operation;
  private Expression[] arguments;
  private Double[] calculatedArguments;
  private final Double value;
  private final ExpressionParser parser;

  public Expression(ExpressionParser parser) {
    this.parser = parser;
    String expression = parser.getExpression();
    operation = OperationsController.define(expression);
    if (operation == null) {
      value = parseDouble(expression);
      return;
    }
    initializeArguments();
    calculateArguments();
    value = operation.calculate(calculatedArguments);
  }

  private void initializeArguments() {
    arguments = new Expression[operation.numberOfArguments()];
    for (int i = 0; i < operation.numberOfArguments(); i++) {
      arguments[i] = new Expression(parser);
    }
  }

  private void calculateArguments() {
    calculatedArguments = new Double[operation.numberOfArguments()];
    for (int i = 0; i < operation.numberOfArguments(); i++) {
      calculatedArguments[i] = arguments[i].value;
    }
  }

  public Double getValue() {
    return value;
  }
}