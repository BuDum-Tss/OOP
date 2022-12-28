package ru.nsu.fit.apotapova.OptionScripts;

import ru.nsu.fit.apotapova.NotesManager;

public interface OptionScript{
  public String option();
  public String longOption();
  public Boolean hasArgs();
  public int numberArgs(int numberArgs);
  public String description();

  public void run(NotesManager manager, String[] arguments);
}
