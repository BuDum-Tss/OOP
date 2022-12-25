/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.nsu.fit.apotapova;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubstringsFinderTest {

  @Test
  void findSubstring() {
    SubstringsFinder finder = new SubstringsFinder(new File("src/test/resources/SmallText.txt"));
    Assertions.assertEquals(0, finder.findSubstring("This"));
    Assertions.assertEquals(2, finder.findSubstring("is"));
    Assertions.assertEquals(4, finder.findSubstring(" is"));
    Assertions.assertEquals(14, finder.findSubstring("text"));
    Assertions.assertNull( finder.findSubstring("This line is not in the text"));
    finder = new SubstringsFinder(new File("src/test/resources/BigText.txt"));
    Assertions.assertEquals(10, finder.findSubstring("The Boy Who Lived"));
    Assertions.assertEquals(37, finder.findSubstring("Mrs."));
    Assertions.assertEquals(29, finder.findSubstring("Mr. and Mrs. Dursley"));
    Assertions.assertEquals(11442, finder.findSubstring("How very wrong he was."));
    Assertions.assertEquals(26068, finder.findSubstring("\"To Harry Potter - the boy who lived!\""));
    Assertions.assertNull(finder.findSubstring("This line is not in the text"));
  }
}