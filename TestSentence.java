import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Test class for the Sentence Class and its methods.
 */

public class TestSentence {

  private Sentence<String> s1;
  private Sentence<String> s2;

  @Before
  public void setUp() {
    List<String> words = new ArrayList<>(Arrays.asList("I", "am", "tired"));
    s1 = new Sentence<>(words);

    List<String> words2 = new ArrayList<>(Arrays.asList("Hola", "amigo", "!",
            " ", "que", "pasa", "?"));
    s2 = new Sentence<>(words2);
  }

  @Test
  public void testGetNumberOfWords() {
    assertEquals(3, s1.getNumberOfWords());
    assertEquals(4, s2.getNumberOfWords());
  }

  @Test
  public void testLongestWord() {
    assertEquals("tired", s1.longestWord());
    assertEquals("amigo", s2.longestWord());
  }

  @Test
  public void testToString() {
    assertEquals("I am tired", s1.toString());
    assertEquals("Hola amigo !   que pasa ?", s2.toString());
  }

  @Test
  public void testClone() {
    Sentence<String> clone1 = s1.clone();
    Sentence<String> clone2 = s2.clone();
    assertEquals(s1.toString(), clone1.toString());
    assertEquals(s2.toString(), clone2.toString());
  }

  @Test
  public void testMerge() {
    List<String> words = new ArrayList<>(Arrays.asList("right", "now"));
    List<String> words2 = new ArrayList<>(Arrays.asList("nada", "y tu", " ", "?"));
    Sentence<String> otherSentence = new Sentence<>(words);
    Sentence<String> otherSentence2 = new Sentence<>(words2);

    Sentence<String> mergedSentence = s1.merge(otherSentence);
    assertEquals("I am tired right now", mergedSentence.toString());

    Sentence<String> mergedSentence2 = s2.merge(otherSentence2);
    assertEquals("Hola amigo !   que pasa ? nada y tu   ?", mergedSentence2.toString());
  }

  @Test
  public void testCountPunctuation() {
    Predicate<String> isPunctuation = word -> word.matches("[.,!?;:()\\[\\]{}]");
    int count = s1.countPunctuation(isPunctuation);
    assertEquals(0, count);

    Predicate<String> isPunctuation2 = word2 -> word2.matches("[.,!?;:()\\[\\]{}]");
    int count2 = s2.countPunctuation(isPunctuation2);
    assertEquals(2, count2);
  }

  @Test
  public void testCountWordsWithLetter() {
    Predicate<String> hasLetter1 = word -> word.contains("z");
    Predicate<String> hasLetter2 = word2 -> word2.contains("a");
    int count = s1.countWordsWithLetter(hasLetter1);
    assertEquals(0, count);

    int count2 = s2.countWordsWithLetter(hasLetter2);
    assertEquals(3, count2);
  }

  @Test
  public void testPigLatin() {
    Sentence<String> pigLatinSentence = s1.pigLatin(s1);
    assertEquals("IWAY amWAY iredtAY", pigLatinSentence.toString());

    Sentence<String> pigLatinSentence2 = s2.pigLatin(s2);
    assertEquals("olahAY amigoWAY !AY" +
            "  AY ueqAY asapAY ?AY", pigLatinSentence2.toString());
  }
}