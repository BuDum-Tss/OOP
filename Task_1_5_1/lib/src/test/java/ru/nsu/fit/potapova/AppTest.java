/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.potapova;

import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.potapova.operations.Minus;
import ru.nsu.fit.potapova.operations.Multiply;
import ru.nsu.fit.potapova.operations.Plus;

class AppTest {

  @Test
  void testOperations() {
    ExpressionParser parser = new ExpressionParser();
    Assertions.assertEquals(1.0, parser.calculate("- + 2.0 2.0 3.0"));
    Assertions.assertThrows(RuntimeException.class, () -> parser.calculate("/ 1.0 0.0"));
    Assertions.assertEquals(1.0, parser.calculate("cos 0.0"));
    Assertions.assertEquals(0.0, parser.calculate("sin 0.0"));
    Assertions.assertEquals(5.0, parser.calculate("log 2.0 32.0"));
    Assertions.assertThrows(RuntimeException.class, () -> parser.calculate("log 1.0 32.0"));
    Assertions.assertEquals(64.0, parser.calculate("* 2.0 32.0"));
    Assertions.assertEquals(16.0, parser.calculate("/ 32.0 2.0"));
    Assertions.assertEquals(1024.0, parser.calculate("pow 2.0 10.0"));
    Assertions.assertEquals(4.0, parser.calculate("sqrt 16.0"));
    Assertions.assertThrows(RuntimeException.class, () -> parser.calculate("sqrt -16.0"));
    Assertions.assertThrows(NumberFormatException.class, () -> parser.calculate("myfunc -16.0"));
  }

  @Test
  void testExpressionParser() {
    Operation plus = new Plus();
    Operation minus = new Minus();
    HashMap<String, Operation> operations = new HashMap<>();
    operations.put("+", plus);
    ExpressionParser parser = new ExpressionParser(operations);
    Assertions.assertEquals(plus, parser.getOperation("+"));
    Assertions.assertNull(parser.getOperation("*"));

    Operation mult = new Multiply();
    parser.addOperation("*", mult);
    Assertions.assertEquals(mult, parser.getOperation("*"));
  }
}
