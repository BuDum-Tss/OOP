
package ru.nsu.fit.apotapova;

import java.io.IOException;
import java.io.Reader;

/**
 * A class that implements substring search in a string.
 */
public class SubstringsFinder {

  private static final int BUFFER_SIZE = 32;
  private final char[] stringBuffer;
  private final Reader reader;

  /**
   * Constructor of a class that defines file and stringBuffer.
   *
   * @param reader - reader
   */
  public SubstringsFinder(Reader reader) {
    this.reader = reader;
    stringBuffer = new char[BUFFER_SIZE
        ];
  }

  /**
   * A method that implements a substring search in a string.
   *
   * @param substring - substring
   * @return - the beginning of the substring
   */
  public Integer findSubstring(String substring) {
    char[] substringBuffer = substring.toCharArray();
    int substringLength = substring.length();
    int stringPoint = 0;
    int substringPoint = 0;
    int substringBeginning = 0;
    int numberCharsInBuffer = BUFFER_SIZE;
    while (substringPoint < substringLength && stringPoint % BUFFER_SIZE <= numberCharsInBuffer) {
      if (stringPoint % BUFFER_SIZE == 0) {
        numberCharsInBuffer = updateBuffer();
      }
      if (substringBuffer[substringPoint] == stringBuffer[stringPoint % BUFFER_SIZE
          ]) {
        substringPoint++;
      } else {
        substringBeginning = stringPoint + 1;
        substringPoint = 0;
      }
      stringPoint++;
    }
    if (stringPoint % BUFFER_SIZE
        > numberCharsInBuffer) {
      return null;
    }
    return substringBeginning;
  }

  private Integer updateBuffer() {
    int n;
    try {
      n = reader.read(stringBuffer, 0, BUFFER_SIZE
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return n;
  }
}
