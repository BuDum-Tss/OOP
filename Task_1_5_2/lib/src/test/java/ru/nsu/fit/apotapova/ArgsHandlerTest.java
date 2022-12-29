package ru.nsu.fit.apotapova;

import static ru.nsu.fit.apotapova.ArgsHandler.getOptions;
import static ru.nsu.fit.apotapova.ArgsHandler.parseArgs;
import static ru.nsu.fit.apotapova.ArgsHandler.toDate;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.apotapova.OptionScripts.Add;
import ru.nsu.fit.apotapova.OptionScripts.Rm;
import ru.nsu.fit.apotapova.OptionScripts.Show;

class ArgsHandlerTest {

  @Test
  void parseArgsTestTest() {
    String[] args=new String[]{"-add", "qwerty"};
    List<OptionScript> optionList = List.of(new Add(), new Rm(), new Show());
    Options options = getOptions(optionList,1);
    CommandLine cl = parseArgs(args, options);
    Assertions.assertTrue(cl.hasOption("add"));
    Assertions.assertFalse(cl.hasOption("rm"));
    Assertions.assertFalse(cl.hasOption("show"));
    Assertions.assertEquals(1,cl.getOptionValues("add").length);
    Assertions.assertEquals("qwerty",cl.getOptionValues("add")[0]);
  }

  @Test
  void toDateTest() {
    String strDate = "1.01.1970 00:00";
    Date date = toDate(strDate);
    Assertions.assertEquals(-TimeZone.getDefault().getRawOffset(),date.getTime());
    strDate = "qweqwe";
    date = toDate(strDate);
    Assertions.assertNull(date);
  }

  @Test
  void getOptionsTest() {
    List<OptionScript> optionList = List.of(new Add(), new Rm(), new Show());
    Options options = getOptions(optionList,1);
    Assertions.assertTrue(options.hasOption("add"));
    Assertions.assertTrue(options.hasOption("rm"));
    Assertions.assertTrue(options.hasOption("show"));
    Assertions.assertFalse(options.hasOption("qwerty"));
  }

  @Test
  void doOptionTest() {
    String[] args=new String[]{"-add", "qwerty"};
    List<OptionScript> optionList = List.of(new Add(), new Rm(), new Show());
    NotesManager manager = new NotesManager("");
    Options options = ArgsHandler.getOptions(optionList, 1);
    CommandLine commandLine = ArgsHandler.parseArgs(args, options);
    ArgsHandler.doOption(manager, commandLine, optionList, options);
    Assertions.assertEquals("qwerty",manager.getNotes().get("qwerty").getNote());
    Assertions.assertTrue(manager.getNotes().get("qwerty").getDate().getTime()<=(new Date().getTime()));
  }
}