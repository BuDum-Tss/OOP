package ru.nsu.fit.apotapova.OptionScripts;

import java.util.Date;
import ru.nsu.fit.apotapova.NotesManager;
import ru.nsu.fit.apotapova.OptionScript;

/**
 * Implements {@link OptionScript}. Adds a note to a notebook.
 */
public class Add implements OptionScript {

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