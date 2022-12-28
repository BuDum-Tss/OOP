package ru.nsu.fit.apotapova.OptionScripts;

import java.util.Date;
import ru.nsu.fit.apotapova.ArgsHandler;
import ru.nsu.fit.apotapova.NotesManager;

public class OptionFactory {

  public static OptionScript createOption(OptionType type) {
    OptionScript option = null;
    switch (type) {
      case RM: {
        option = new Rm();
        break;
      }
      case ADD: {
        option = new Add();
        break;
      }
      case SHOW: {
        option = new Show();
        break;
      }
    }
    return option;
  }

  public static class Add implements OptionScript {

    @Override
    public String option() {
      return "add";
    }

    @Override
    public String longOption() {
      return "add";
    }

    @Override
    public Boolean hasArgs() {
      return true;
    }

    @Override
    public int numberArgs(int numberArgs) {
      return numberArgs;
    }

    @Override
    public String description() {
      return "add new note to notebook";
    }

    @Override
    public void run(NotesManager manager, String[] arguments) {
      Date date = new Date();
      manager.add(date, arguments);
    }
  }

  public final static class Rm implements OptionScript {

    @Override
    public String option() {
      return "rm";
    }

    @Override
    public String longOption() {
      return "remove";
    }

    @Override
    public Boolean hasArgs() {
      return true;
    }

    @Override
    public int numberArgs(int numberArgs) {
      return numberArgs;
    }

    @Override
    public String description() {
      return "remove note from notebook";
    }

    @Override
    public void run(NotesManager manager, String[] arguments) {
      manager.remove(arguments);
    }
  }

  public final static class Show implements OptionScript {

    @Override
    public String option() {
      return "show";
    }

    @Override
    public String longOption() {
      return "show";
    }

    @Override
    public Boolean hasArgs() {
      return true;
    }

    @Override
    public int numberArgs(int numberArgs) {
      if (numberArgs == 0) {
        return 0;
      }
      return 2;
    }

    @Override
    public String description() {
      return "shows notes from first to second date";
    }

    @Override
    public void run(NotesManager manager, String[] arguments) {
      if (arguments.length == 0) {
        manager.show();
      } else {
        manager.show(ArgsHandler.toDate(arguments[0]), ArgsHandler.toDate(arguments[1]));
      }
    }
  }
}
