package ru.nsu.fit.apotapova;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotesManagerTest {
  private Boolean contains(String note,String filename) {
    try (BufferedReader reader= new BufferedReader(new FileReader(filename))) {
      String buffer = reader.readLine();
      if (buffer.contains(note)) return true;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return false;
  }
  @Test
  void uploadTest() {
    String file ="src/test/resources/uploadTest.json";
    NotesManager manager = new NotesManager(file);
    Date date = new Date();
    manager.add(date,new String[]{"some note"});
    manager.upload();
    Assertions.assertTrue(contains("some note",file));
    Assertions.assertFalse(contains("node",file));
  }

  @Test
  void loadTest() {
    String file ="src/test/resources/loadTest.json";
    NotesManager manager = new NotesManager(file);
    Date date = new Date();
    manager.load();
    Map<String,Note> nodes = manager.getNotes();
    Assertions.assertTrue(nodes.containsKey("some note"));
    Assertions.assertFalse(nodes.containsKey("node"));
  }

  @Test
  void addTest() {
    NotesManager manager = new NotesManager("");
    Date date = new Date();
    manager.add(date,new String[]{"some note"});
    Assertions.assertTrue(manager.getNotes().containsKey("some note"));
    Assertions.assertFalse(manager.getNotes().containsKey("note"));
  }

  @Test
  void removeTest() {
    NotesManager manager = new NotesManager("");
    Date date = new Date();
    manager.add(date,new String[]{"some note","12"});
    manager.remove(new String[]{"some note","1"});
    Assertions.assertTrue(manager.getNotes().containsKey("12"));
    Assertions.assertFalse(manager.getNotes().containsKey("some note"));
  }
  //================================================================================================

  @Test
  void showAllTest() {
    NotesManager manager = new NotesManager("");
    Date date = new Date();
    manager.add(date,new String[]{"some note"});

    PrintStream save_out=System.out;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    manager.show();
    Assertions.assertEquals(" \"some note\"", out.toString());
    System.setOut(save_out);
  }


  @Test
  void showTest() {
    NotesManager manager = new NotesManager("");
    Date date = new Date();
    manager.add(date,new String[]{"some note"});
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    Date date2 = new Date();
    manager.add(date2,new String[]{"some note2"});
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    date2 = new Date();
    manager.add(date2,new String[]{"some note3"});
    PrintStream save_out=System.out;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));

    manager.show(date,date2);
    Assertions.assertEquals(" \"some note2\"", out.toString());
    System.setOut(save_out);
  }
}