package ru.nsu.fit.potapova;

import static java.lang.Double.parseDouble;

/**
 * The most important class of calculators. Defines an expression that is subsequently calculated.
 */
public class Expression {

  private final Operation operation;
  private final Double value;
  private final ExpressionParser parser;
  private Expression[] arguments;
  private Double[] calculatedArguments;

  /**
   * Constructor of the class. First defines the operation, then initializes the subexpressions,
   * counts the value of the subexpressions.
   *
   * @param parser - parser of expression string.
   */
  public Expression(ExpressionParser parser) {
    this.parser = parser;
    String expression = parser.getOperationName();
    operation = parser.getOperation(expression);
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