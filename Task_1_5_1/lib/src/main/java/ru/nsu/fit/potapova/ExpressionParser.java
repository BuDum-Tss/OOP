package ru.nsu.fit.potapova;

import java.util.HashMap;
import java.util.Map;
import ru.nsu.fit.potapova.operations.Cos;
import ru.nsu.fit.potapova.operations.Divide;
import ru.nsu.fit.potapova.operations.Logarithm;
import ru.nsu.fit.potapova.operations.Minus;
import ru.nsu.fit.potapova.operations.Multiply;
import ru.nsu.fit.potapova.operations.Plus;
import ru.nsu.fit.potapova.operations.Power;
import ru.nsu.fit.potapova.operations.Sin;
import ru.nsu.fit.potapova.operations.Sqrt;

/**
 * Parses the expression and launches operations in the correct order.
 */
public class ExpressionParser {

  Map<String, Operation> ops = Map.of(
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
  private String stringExpression;
  private HashMap<String, Operation> operations = new HashMap<>(ops);

  public ExpressionParser(HashMap<String, Operation> operations) {
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

  public void addOperation(String name, Operation operation) {
    operations.put(name, operation);
  }
}
