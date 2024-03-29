
package ru.nsu.fit.apotapova;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;


class AppTest {

  private Map<String, Note> loadData(String filename) {
    Map<String, Note> notes;
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(filename));
      ObjectMapper mapper = new ObjectMapper();
      notes = mapper.readValue(reader, HashMap.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return notes;
  }

  @Test
  void appAdd() {/*
    App app = new App();
    //app.setFilename("src/test/resources/testData1.json");
    String[] args = new String[]{"-add \"my note\" \"my note2\""};
    App.main(args);
    Map<String, Note> map = loadData(app.getFilename());
    Assertions.assertTrue(map.containsKey("my note"));
    Assertions.assertTrue(map.containsKey("my note2"));*/
  }
  @Test
  void appRemove() {
    /*
    App app = new App();
    String[] args = new String[]{"-add \"my note3\" \"my note4\""};
    App.main(args);
    args = new String[]{"-rm \"my note3\" \"my note4\""};
    App.main(args);
    Map<String, Note> map = loadData(app.getFilename());
    Assertions.assertFalse(map.containsKey("my note3"));
    Assertions.assertFalse(map.containsKey("my note4"));*/
  }
}
