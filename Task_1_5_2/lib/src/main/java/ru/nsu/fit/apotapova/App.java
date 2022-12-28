package ru.nsu.fit.apotapova;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

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

    Options options = ArgsHandler.getOptions(args.length);
    CommandLine commandLine = ArgsHandler.parseArgs(args, options);
    ArgsHandler.doOption(manager, args[0], args.length, commandLine, options);

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