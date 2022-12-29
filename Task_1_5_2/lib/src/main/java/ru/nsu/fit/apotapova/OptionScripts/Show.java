package ru.nsu.fit.apotapova.OptionScripts;

import ru.nsu.fit.apotapova.ArgsHandler;
import ru.nsu.fit.apotapova.NotesManager;
import ru.nsu.fit.apotapova.OptionScript;

/**
 * Implements {@link OptionScript}. Shows all notes or notes from some time to some time.
 */
public final class Show implements OptionScript {

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
  public void run(NotesManager manager, String[] arguments) throws Exception {
    if (arguments.length == 0) {
      manager.show();
    } else if (arguments.length == 2) {
      manager.show(ArgsHandler.toDate(arguments[0]), ArgsHandler.toDate(arguments[1]));
    }
    throw new Exception("Number of arguments is not 0 or 2");
  }
}