package ru.nsu.fit.apotapova;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * A class that processes command line arguments.
 */
public class ArgsHandler {

  /**
   * Parses command line arguments.
   *
   * @param args    - command line arguments
   * @param options - options
   * @return - command line
   */
  public static CommandLine parseArgs(String[] args, Options options) {
    CommandLineParser parser = new DefaultParser();
    CommandLine commandLine = null;
    try {
      commandLine = parser.parse(options, args);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      printHelp(options);
    }
    return commandLine;
  }

  /**
   * Converts a string to date.
   *
   * @param string - string with date
   * @return - date
   */
  public static Date toDate(String string) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy hh:mm");
    Date date;
    try {
      date = sdf.parse(string);
    } catch (java.text.ParseException e) {
      return null;
    }
    return date;
  }

  /**
   * Gets options.
   *
   * @param argsNumber - number of command line arguments
   * @return - options
   */
  public static Options getOptions(List<OptionScript> optionList, int argsNumber) {
    OptionGroup optionGroup = new OptionGroup();
    optionList.forEach(optionScript -> {
      Option option = new Option(optionScript.option(), optionScript.longOption(),
          optionScript.hasArgs(), optionScript.description());
      option.setArgs(optionScript.numberArgs(argsNumber));
      optionGroup.addOption(option);
    });
    Options options = new Options();
    options.addOptionGroup(optionGroup);
    return options;
  }

  /**
   * Does something option.
   *
   * @param manager - notes manager
   */
  public static void doOption(NotesManager manager, CommandLine commandLine,
      List<OptionScript> optionList, Options options) {
    optionList.forEach(optionScript -> {
      if (commandLine.hasOption(optionScript.option())) {
        try {
          optionScript.run(manager, commandLine.getOptionValues(optionScript.option()));
        } catch (Exception e) {
          System.out.println(e.getMessage());
          printHelp(options);
        }
      }
    });
  }

  private static void printHelp(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("notebook", options);
    System.exit(1);
  }
}
