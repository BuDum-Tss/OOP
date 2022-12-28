package ru.nsu.fit.apotapova;

import java.util.Date;

/**
 * A record, that contains date of creation note and note.
 */
public class Note {

  private final Date date;
  private final String note;

  /**
   * A constructor of class.
   *
   * @param date - date
   * @param note - note
   */
  public Note(Date date, String note) {
    this.date = date;
    this.note = note;
  }

  /**
   * Gets data.
   *
   * @return - data
   */
  public Date getDate() {
    return date;
  }

  /**
   * Gets note.
   *
   * @return note
   */
  public String getNote() {
    return note;
  }
}
