package ru.nsu.fit.apotapova;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public final class ArgsHandler {

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

  private static Date toDate(String string) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy hh:mm");
    Date date;
    try {
      date = sdf.parse(string);
    } catch (java.text.ParseException e) {
      throw new RuntimeException(e);
    }
    System.out.println(date);
    return date;
  }

  /**
   * Gets options.
   *
   * @param argsNumber - number of command line arguments
   * @return - options
   */
  public static Options getOptions(int argsNumber) {
    Option add = new Option("add", "add", true, "add new note to notebook");
    add.setArgs(argsNumber);
    Option rm = new Option("rm", "remove", true, "remove note from notebook");
    rm.setArgs(argsNumber);
    Option show = new Option("show", "show", true, "shows notes from first to second date");
    if (argsNumber == 0) {
      show.setArgs(0);
    } else {
      show.setArgs(2);
    }
    OptionGroup optionGroup = new OptionGroup();
    optionGroup.addOption(add);
    optionGroup.addOption(rm);
    optionGroup.addOption(show);

    Options options = new Options();
    options.addOptionGroup(optionGroup);
    return options;
  }

  /**
   * Does something option.
   *
   * @param manager - notes manager
   */
  public static void doOption(NotesManager manager, String command, int argsNumber,
      CommandLine commandLine, Options options) {
    switch (command) {
      case "-add": {
        String[] arguments = commandLine.getOptionValues("add");
        Date date = new Date();
        manager.add(date, arguments);
        break;
      }
      case "-rm": {
        String[] arguments = commandLine.getOptionValues("rm");
        manager.remove(arguments);
        break;
      }
      case "-show": {
        String[] arguments = commandLine.getOptionValues("show");
        if (argsNumber == 0) {
          manager.show();
        } else {
          manager.show(toDate(arguments[0]), toDate(arguments[1]));
        }
        return;
      }
      default: {
        System.out.println("Option not found");
        printHelp(options);
      }
    }
  }

  private static void printHelp(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("notebook", options);
    System.exit(1);
  }
}
