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
    Assertions.assertEquals(0,option3.numberArgs(0));
    Assertions.assertEquals(2,option3.numberArgs(2));
    Assertions.assertEquals(2,option3.numberArgs(1));
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
    OptionScript option3 = new Show();
    String[] args1 = new String[]{"123","321","321"};
    NotesManager manager1 = new NotesManager("");
    Assertions.assertThrows(Exception.class,()->option3.run(manager1,args1));
    String[] args2 = new String[]{};
    NotesManager manager2 = new NotesManager("");
    Assertions.assertDoesNotThrow(()->option3.run(manager2,args2));
    String[] args3 = new String[]{"10.10.1010 10:10", "11.11.1111 11:11"};
    NotesManager manager3 = new NotesManager("");
    Assertions.assertDoesNotThrow(()->option3.run(manager3,args3));

    OptionScript option4 = new Rm();
    String[] args4 = new String[]{"123","321","321"};
    NotesManager manager4 = new NotesManager("");
    Assertions.assertDoesNotThrow(()->option4.run(manager4,args4));

    OptionScript option5 = new Add();
    String[] args5 = new String[]{"123","321","321"};
    NotesManager manager5 = new NotesManager("");
    Assertions.assertDoesNotThrow(()->option5.run(manager5,args5));
  }
}