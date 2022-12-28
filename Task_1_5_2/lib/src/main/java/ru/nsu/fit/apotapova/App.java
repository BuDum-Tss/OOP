package ru.nsu.fit.apotapova;

/**
 * The main class of notebook.
 */
public class App {

  private static String filename = "lib/src/main/resources/data.json";

  /**
   * Main method. It sets options, loads data from data file to NotesManager, adds/removes/shows
   * note(s) and uploads JSON to data file.
   *
   * @param args - arguments of command line
   */
  public static void main(String[] args) {

    NotesManager manager = new NotesManager(filename);
    manager.load();
    ArgsHandler argsHandler = new ArgsHandler(args);
    argsHandler.doOption(manager);
    manager.upload();
  }

  /**
   * Gets file name.
   *
   * @return file name
   */
  public String getFilename() {
    return filename;
  }
}