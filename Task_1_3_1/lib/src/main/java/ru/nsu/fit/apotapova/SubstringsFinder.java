package ru.nsu.fit.apotapova;

import java.io.IOException;
import java.io.Reader;

/**
 * A class that implements substring search in a string.
 */
public class SubstringsFinder {

  private final int bufferSize;

  /**
   * Constructor of a class that defines file and stringBuffer.
   *
   * @param bufferSize - size of buffer
   */
  public SubstringsFinder(int bufferSize) {
    this.bufferSize = bufferSize;
  }

  /**
   * A method that implements a substring search in a string.
   *
   * @param substring - substring
   * @return - the beginning of the substring
   */
  public Integer findSubstring(Reader reader, String substring) {
    char[] stringBuffer = new char[bufferSize];
    char[] substringBuffer = substring.toCharArray();
    int substringLength = substring.length();
    int stringPoint = 0;
    int substringPoint = 0;
    int substringBeginning = 0;
    int numberCharsInBuffer = bufferSize;
    while (substringPoint < substringLength && stringPoint % bufferSize <= numberCharsInBuffer) {
      if (stringPoint % bufferSize == 0) {
        numberCharsInBuffer = updateBuffer(stringBuffer, reader);
      }
      if (substringBuffer[substringPoint] == stringBuffer[stringPoint % bufferSize]) {
        substringPoint++;
      } else {
        substringBeginning = stringPoint + 1;
        substringPoint = 0;
      }
      stringPoint++;
    }
    if (stringPoint % bufferSize > numberCharsInBuffer) {
      return null;
    }
    return substringBeginning;
  }

  private Integer updateBuffer(char[] stringBuffer, Reader reader) {
    int n;
    try {
      n = reader.read(stringBuffer, 0, bufferSize);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return n;
  }
}
