package ru.nsu.fit.potapova;

import java.util.Map;
import ru.nsu.fit.potapova.Operations.Cos;
import ru.nsu.fit.potapova.Operations.Divide;
import ru.nsu.fit.potapova.Operations.Logarithm;
import ru.nsu.fit.potapova.Operations.Minus;
import ru.nsu.fit.potapova.Operations.Multiply;
import ru.nsu.fit.potapova.Operations.Plus;
import ru.nsu.fit.potapova.Operations.Power;
import ru.nsu.fit.potapova.Operations.Sin;
import ru.nsu.fit.potapova.Operations.Sqrt;

/**
 * Parses the expression and launches operations in the correct order.
 */
public class ExpressionParser {

  private String stringExpression;
  private Map<String, Operation> operations = Map.of(
      "+", new Plus(),
      "-", new Minus(),
      "*", new Multiply(),
      "/", new Divide(),
      "sin", new Sin(),
      "cos", new Cos(),
      "log", new Logarithm(),
      "pow", new Power(),
      "sqrt", new Sqrt()
  );

  public ExpressionParser(Map<String, Operation> operations) {
    this.operations = operations;
  }

  public ExpressionParser() {
  }

  /**
   * Calculates the value of the expression.
   *
   * @param stringExpression - string expression
   * @return - value of the expression
   */
  public Double calculate(String stringExpression) {

    this.stringExpression = stringExpression;
    Expression expression = new Expression(this);
    return expression.getValue();
  }

  /**
   * Gets name of operation.
   *
   * @return - name of operation
   */
  public String getOperationName() {
    String[] temp = stringExpression.split(" ", 2);
    if (temp.length == 2) {
      stringExpression = temp[1];
    }
    return temp[0];
  }

  public Operation getOperation(String strExpression) {
    return operations.get(strExpression);
  }
}
