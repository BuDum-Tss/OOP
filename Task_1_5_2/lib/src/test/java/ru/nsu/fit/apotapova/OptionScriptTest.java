package ru.nsu.fit.apotapova;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.apotapova.OptionScripts.Add;
import ru.nsu.fit.apotapova.OptionScripts.Rm;
import ru.nsu.fit.apotapova.OptionScripts.Show;

class OptionScriptTest {

  @Test
  void optionTest() {
    OptionScript option1 = new Add();
    Assertions.assertEquals("add",option1.option());
    OptionScript option2 = new Rm();
    Assertions.assertEquals("rm",option2.option());
    OptionScript option3 = new Show();
    Assertions.assertEquals("show",option3.option());
  }

  @Test
  void longOptionTest() {
    OptionScript option1 = new Add();
    Assertions.assertEquals("add",option1.longOption());
    OptionScript option2 = new Rm();
    Assertions.assertEquals("remove",option2.longOption());
    OptionScript option3 = new Show();
    Assertions.assertEquals("show",option3.longOption());
  }

  @Test
  void hasArgsTest() {
    OptionScript option1 = new Add();
    Assertions.assertTrue(option1.hasArgs());
    OptionScript option2 = new Rm();
    Assertions.assertTrue(option2.hasArgs());
    OptionScript option3 = new Show();
    Assertions.assertTrue(option3.hasArgs());
  }

  @Test
  void numberArgsTest() {
    OptionScript option1 = new Add();
    Assertions.assertEquals(3,option1.numberArgs(3));
    OptionScript option2 = new Rm();
    Assertions.assertEquals(3,option2.numberArgs(3));
    OptionScript option3 = new Show();
    Assertions.assertEquals(2,option3.numberArgs(2));
  }

  @Test
  void descriptionTest() {
    OptionScript option1 = new Add();
    Assertions.assertEquals("add new note to notebook",option1.description());
    OptionScript option2 = new Rm();
    Assertions.assertEquals("remove note from notebook",option2.description());
    OptionScript option3 = new Show();
    Assertions.assertEquals("shows notes from first to second date",option3.description());
  }

  @Test
  void runTest() {
  }
}