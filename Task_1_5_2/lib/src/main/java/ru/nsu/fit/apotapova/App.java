package ru.nsu.fit.apotapova;

import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import ru.nsu.fit.apotapova.OptionScripts.Add;
import ru.nsu.fit.apotapova.OptionScripts.Rm;
import ru.nsu.fit.apotapova.OptionScripts.Show;

/**
 * The main class of notebook.
 */
public class App {

  private static String filename = "lib/src/main/resources/data.json";

  /**w
   * Main method. It sets options, loads data from data file to NotesManager, adds/removes/shows
   * note(s) and uploads JSON to data file.
   *
   * @param args - arguments of command line
   */
  public static void main(String[] args) {
    List<OptionScript> optionList = List.of(new Add(), new Rm(), new Show());
    NotesManager manager = new NotesManager(filename);
    manager.load();

    Options options = ArgsHandler.getOptions(optionList, args.length - 1);
    CommandLine commandLine = ArgsHandler.parseArgs(args, options);
    ArgsHandler.doOption(manager, commandLine, optionList, options);

    manager.upload();
  }

  /**
   * Gets file name.
   *
   * @return - file name
   */
  public String getFilename() {
    return filename;
  }

  /**
   * Sets file name.
   *
   * @param filename - file name
   */
  public void setFilename(String filename) {
    App.filename = filename;
  }
}