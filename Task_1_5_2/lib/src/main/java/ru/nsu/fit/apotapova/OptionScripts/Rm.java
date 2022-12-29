package ru.nsu.fit.apotapova.OptionScripts;

import ru.nsu.fit.apotapova.NotesManager;
import ru.nsu.fit.apotapova.OptionScript;

/**
 * Implements {@link OptionScript}. Removes a note from a notebook.
 */
public final class Rm implements OptionScript {

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