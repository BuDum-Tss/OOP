package ru.nsu.fit.apotapova;

import static ru.nsu.fit.apotapova.OptionScripts.OptionType.ADD;
import static ru.nsu.fit.apotapova.OptionScripts.OptionType.RM;
import static ru.nsu.fit.apotapova.OptionScripts.OptionType.SHOW;

import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import ru.nsu.fit.apotapova.OptionScripts.OptionFactory;
import ru.nsu.fit.apotapova.OptionScripts.OptionScript;

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
    List<OptionScript> optionList = List.of(
        OptionFactory.createOption(ADD),
        OptionFactory.createOption(RM),
        OptionFactory.createOption(SHOW));

    NotesManager manager = new NotesManager(filename);
    manager.load();

    Options options = ArgsHandler.getOptions(optionList,args.length-1);
    CommandLine commandLine = ArgsHandler.parseArgs(args, options);
    ArgsHandler.doOption(manager, commandLine,optionList);

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