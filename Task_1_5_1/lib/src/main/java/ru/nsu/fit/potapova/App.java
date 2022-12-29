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
 * The main class of the calculator.
 */
public class App {

  /**
   * Main method of the calculator.
   *
   * @param args - argument of command line
   */
  public static void main(String[] args) {
    Map<String, Operation> operations = Map.of(
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
    String expression = System.console().readLine();
    ExpressionParser parser = new ExpressionParser(operations);
    Double value = parser.calculate(expression);
    System.out.println(value);
  }
}
