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
public class ArgsHandler {

  private final Options options;
  private final String[] args;
  private CommandLine commandLine;

  /**
   * Constructor of the class.
   *
   * @param args - command line arguments
   */
  public ArgsHandler(String[] args) {
    this.args = args;
    options = setOptions(args.length);
    CommandLineParser parser = new DefaultParser();
    commandLine = null;
    try {
      commandLine = parser.parse(options, args);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      printHelp();
    }

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

  private static Options setOptions(int number) {
    Option add = new Option("add", "add", true, "add new note to notebook");
    add.setArgs(number - 1);
    Option rm = new Option("rm", "remove", true, "remove note from notebook");
    rm.setArgs(number - 1);
    Option show = new Option("show", "show", true, "shows notes from first to second date");
    if (number - 1 == 0) {
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
  public void doOption(NotesManager manager) {
    switch (args[0]) {
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
        if (args.length - 1 == 0) {
          manager.show();
        } else {
          manager.show(toDate(arguments[0]), toDate(arguments[1]));
        }
        return;
      }
      default: {
        System.out.println("Option not found");
        printHelp();
      }
    }
  }

  private void printHelp() {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("notebook", options);
    System.exit(1);
  }
}
