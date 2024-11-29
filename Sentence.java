import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
/**

 * This class represents a sentence.
 * A sentence is a collection of words.
 *     @param <T> the type of words in the sentence.
 */
public class Sentence<T> {

  private List<T> words;

  /**
   * Constructs an empty sentence.
   */
  public Sentence() {
    words = new ArrayList<>();
  }

  /**
   * Constructs a sentence with a given list of words.
   *     @param words the list of words to be added to the sentence.
   */
  public Sentence(List<T> words) {
    this.words = new ArrayList<>(words);
  }

  /**
   * Returns the number of words in the sentence.
   *     @return the number of words in the sentence.
   */
  public int getNumberOfWords() {
    int count = 0;
    for (T word : words) {

      // Check if the word is not empty and does not consist of only punctuation.
      if (word != null && !word.toString().matches("[ .,!?;:()\\[\\]{}]")) {
        count++;
      }
    }
    return count;
  }

  /**
   * Returns the longest word in the sentence.
   *     @return the longest word in the sentence.
   */
  public T longestWord() {

    // Set longest to null.
    T longest = null;

    // For each word in words, if longest == null
    // or if current word is longer than the current longest
    // reset longest to current word.
    for (T word : words) {
      if (longest == null || word.toString().length() > longest.toString().length()) {
        longest = word;
      }
    }
    return longest;
  }

  /**
   * Returns a string representation of the sentence.
   *     @return a string representation of the sentence.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Iterator<T> iterator = words.iterator();
    while (iterator.hasNext()) {
      sb.append(iterator.next()).append(" ");
    }
    return sb.toString().trim();
  }

  /**
   * Returns a clone of the sentence.
   *     @return a clone of the sentence.
   */
  @Override
  public Sentence<T> clone() {
    return new Sentence<>(words);
  }

  /**
   * Merges the current sentence with another sentence.
   * The returned sentence contains the words from both sentences.
   *     @param sentence the sentence to be merged with the current sentence.
   *     @return a new sentence that combines the sentences.
   */
  public Sentence<T> merge(Sentence<T> sentence) {

    // Copy words from current list to new arraylist.
    List<T> mergedWords = new ArrayList<>(words);

    // Add words from input sentence to new Arraylist.
    mergedWords.addAll(sentence.getWords());
    return new Sentence<>(mergedWords);
  }

  /**
   * Counts the number of words in a sentence with a given condition.
   *     @param condition the condition to be checked for each word.
   *     @return the number of words with the condition.
   */
  public int countPunctuation(Predicate<T> condition) {

    // Create a counter to track the number of times condition is true.
    int count = 0;

    // Loop through words, checking if a word has a condition (punct).
    // Add to count if True.
    Iterator<T> iterator = words.iterator();
    while (iterator.hasNext()) {
      T word = iterator.next();
      if (condition.test(word)) {
        count++;
      }
    }
    return count;
  }

  /**
   * Counts the number of words in the sentence with a given letter condition.
   *     @param condition the condition to be checked for each word.
   *     @return the number of words with the letter.
   */
  public int countWordsWithLetter(Predicate<T> condition) {

    // Create counter to track number times condition is true.
    int count = 0;

    // Iterate through each word in the list.
    for (T word : words) {

      // Add one to counter if True.
      if (condition.test(word)) {
        count++;
      }
    }

    return count;
  }

  /**
   * returns the words.
   *     @return the words.
   */
  public List<T> getWords() {
    return words;
  }

  /**
   * Changes the sentence to pig latin.
   * If a word begins with a vowel, "WAY" is appended.
   * If a word begins with a consonant, the first letter and "AY" is appended.
   *     @param inputSentence the sentence to change to pig latin.
   *     @return a new sentence in pig latin.
   */
  public Sentence<T> pigLatin(Sentence<T> inputSentence) {

    Sentence<T> sentence = new Sentence<>();

    // Get the words from the input sentence.
    List<T> words = inputSentence.getWords();

    // Track the index of the current word.
    int index = 0;

    // Iterate through input sentence.
    while (index < words.size()) {

      // Get the current word.
      T word = words.get(index);

      // Convert word to string.
      String wordString = word.toString();

      // convert to lowercase.
      char firstLetter = Character.toLowerCase(wordString.charAt(0));

      // If vowel append WAY.
      if (firstLetter == 'a' || firstLetter == 'e' ||
              firstLetter == 'i' || firstLetter == 'o' || firstLetter == 'u') {
        sentence.getWords().add((T) (wordString + "WAY"));
      } else {

        // Else move first letter to end of word and append AY.
        sentence.getWords().add((T) (wordString.substring(1) + firstLetter + "AY"));
      }
      // Loop to next word in index.
      index++;
    }
    return sentence;
  }
}