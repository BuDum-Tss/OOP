package ru.nsu.fit.apotapova;

import java.util.Date;

/**
 * A record, that contains date of creation note and note.
 *
 * @param date - date of creation
 * @param note - note
 */
public record Note(Date date, String note) {

}
