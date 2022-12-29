package ru.nsu.fit.apotapova;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
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

  @Test
  void showAllTest() {
  }

  @Test
  void showTest() {
  }
}