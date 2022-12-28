package ru.nsu.fit.apotapova;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that manages notes.
 */
public class NotesManager {

  private final String filename;
  private Map<String, Note> notes;

  /**
   * Constructor of the class.
   */
  public NotesManager(String filename) {
    this.filename = filename;
    notes = new HashMap<>();
  }

  /**
   * Uploads data from file.
   */
  public void upload() {
    String serialized;
    PrintWriter writer;
    try {
      serialized = new ObjectMapper().writeValueAsString(notes);
      writer = new PrintWriter(filename);
    } catch (JsonProcessingException | FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    writer.println(serialized);
    writer.close();
  }

  /**
   * Loads data to file.
   */
  public void load() {
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(filename));
      ObjectMapper mapper = new ObjectMapper();
      notes = mapper.readValue(reader, new TypeReference<>() {
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Adds notes to notebook.
   *
   * @param date - current date
   * @param note - notes
   */
  public void add(Date date, String[] note) {
    for (String s : note) {
      add(date, s);
    }
  }

  private void add(Date date, String note) {
    notes.put(note, new Note(date, note));
  }

  /**
   * Removes notes from notebook.
   *
   * @param note - notes
   */
  public void remove(String[] note) {
    for (String s : note) {
      remove(s);
    }
  }

  private void remove(String note) {
    notes.remove(note);
  }

  /**
   * Shows the notes that were made from the first date to the second.
   *
   * @param from - first date
   * @param to   - second date
   */
  public void show(Date from, Date to) {
    for (Note n : notes.values()) {
      //if (n.date().after(from) && n.date().before(to)){
      if (n.date().getTime() > from.getTime() && n.date().getTime() < to.getTime()) {
        System.out.print(" " + n.note());
      }
    }
  }

  /**
   * Shows all notes.
   */
  public void show() {
    for (Note s : notes.values()) {
      System.out.print(" " + s.note());
    }
  }
}
