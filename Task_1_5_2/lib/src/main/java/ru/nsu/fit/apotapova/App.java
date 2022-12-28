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
 * The main class of notebook.
 */
public class App {
  private static final String filename = "F:\\GitHubProjects\\OOP\\Task_1_5_2\\lib\\src\\main\\resources\\data.json";

  /**
   * Gets file name
   *
   * @return file name
   */
  public String getFilename() {
    return filename;
  }
  /**
   * Main method. It sets options, loads data from data file to NotesManager, adds/removes/shows
   * note(s) and uploads JSON to data file
   *
   * @param args - arguments of command line
   */
  public static void main(String[] args) {
    Options options = setOptions(args.length);
    NotesManager manager = new NotesManager(filename);
    manager.load();
    CommandLineParser parser = new DefaultParser();
    CommandLine commandLine = null;
    HelpFormatter formatter = new HelpFormatter();
    try {
      commandLine = parser.parse(options, args);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      formatter.printHelp("utility-name", options);
      System.exit(1);
    }
    if (commandLine.hasOption("add")) {
      String[] arguments = commandLine.getOptionValues("add");
      Date date = new Date();
      manager.add(date, arguments);
    } else if (commandLine.hasOption("rm")) {
      String[] arguments = commandLine.getOptionValues("rm");
      manager.remove(arguments);
    } else if (commandLine.hasOption("show")) {
      String[] arguments = commandLine.getOptionValues("show");
      if (args.length - 1 == 0) {
        manager.show();
      } else {
        manager.show(toDate(arguments[0]), toDate(arguments[1]));
      }
    } else {
      System.out.println("Option not found");
      formatter.printHelp("notebook", options);
      System.exit(1);
    }
    manager.upload();
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
}