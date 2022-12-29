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
    HashMap<String, Operation> ops = new HashMap<>(operations);
    String expression = System.console().readLine();
    ExpressionParser parser = new ExpressionParser(ops);
    Double value = parser.calculate(expression);
    System.out.println(value);
  }
}
