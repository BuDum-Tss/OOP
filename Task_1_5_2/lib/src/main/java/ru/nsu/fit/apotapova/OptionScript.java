package ru.nsu.fit.apotapova;

/**
 * Interface for creating classes that implement methods for executing a command, its description,
 * and properties.
 */
public interface OptionScript {

  /**
   * Returns short option name.
   *
   * @return - short name
   */
  public String option();

  /**
   * Returns long option name.
   *
   * @return - short name
   */
  public String longOption();

  /**
   * Returns whether the option has arguments or not.
   *
   * @return - has options arguments or not
   */
  public Boolean hasArgs();

  /**
   * Returns the number of arguments.
   *
   * @param numberArgs - received number of arguments
   * @return - right number of arguments
   */
  public int numberArgs(int numberArgs);

  /**
   * Returns the description of the option.
   *
   * @return description
   */
  public String description();

  /**
   * Runs script.
   *
   * @param manager   - NotesManager
   * @param arguments - option arguments
   */
  public void run(NotesManager manager, String[] arguments) throws Exception;
}