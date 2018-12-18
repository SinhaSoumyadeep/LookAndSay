package lookandsay;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This is a concrete implementation of the RIterator. A look-and-say sequence is a sequence of
 * numbers. Given the current number, the next number is obtained by reading the current number out
 * loud, writing what we say. For example, if the current number is 1, then we read that as “one 1”
 * and thus the next number in the sequence will be 11. Similarly, if the current number is 112321,
 * then we read that as “two 1s, one 2, one 3, one 2, one 1”, producing the next number as
 * 2112131211, and so on. A look-and-say sequence is a simple example of run-length encoding.The
 * look-and-say sequence can also be computed in reverse. For example 2112131211 can be reversed by
 * taking digits two at a time, and writing them out (write two 1s, then one 2, and so on). This
 * produces 112321. Work out a couple of examples of the sequence before proceeding. RIterator T
 * interface extends Iterator T and adds two methods:  T prev() which should return the current
 * number in the sequence and revert to the previous number in the sequence. and boolean
 * hasPrevious()should return true if it is possible to go back one step.
 */

public class LookAndSayIterator implements RIterator<BigInteger> {

  private BigInteger end;
  private BigInteger current;

  /**
   * This constructor that takes two arguments: a starting seed and an end value. The seed is the
   * number at which the sequence must begin. The iterator should stop incrementing once a number
   * greater than end is reached.
   *
   * @throws IllegalArgumentException If the seed is not a positive number.
   * @throws IllegalArgumentException If the seed is not less than the end.
   */
  public LookAndSayIterator(BigInteger seed, BigInteger end) throws IllegalArgumentException {

    if (seed == null || end == null) {
      throw new IllegalArgumentException("seed or end cannot be null");
    }
    if (seed.compareTo(new BigInteger("0")) == -1 || seed.compareTo(end) == 1) {
      throw new IllegalArgumentException("invalid argument");
    }

    if (seed.compareTo(new BigInteger("22")) == 0) {
      throw new IllegalArgumentException("22 case.");
    }

    this.end = end;
    this.current = seed;

  }

  /**
   * constructor that takes a starting seed as its only argument. The seed is the number at which
   * the sequence must begin. The end value will be a number with 100 9s (the largest 100 digit
   * number)
   *
   * @throws IllegalArgumentException If the seed is not a positive number.
   * @throws IllegalArgumentException If the seed is not less than the end.
   */
  public LookAndSayIterator(BigInteger seed) throws IllegalArgumentException {
    if (seed == null) {
      throw new IllegalArgumentException("seed cannot be null");
    }
    if (seed.compareTo(new BigInteger("22")) == 0) {
      throw new IllegalArgumentException("22 case.");
    }
    this.end = new BigInteger("10").pow(100).subtract(new BigInteger("1"));
    if (seed.compareTo(new BigInteger("0")) == -1 || seed.compareTo(end) == 1) {
      throw new IllegalArgumentException("invalid argument");
    }

    this.current = seed;

  }

  /**
   * It must offer a constructor that takes no arguments. This should start the sequence with a seed
   * of 1 (e.g. calling next and an end value as a number with 100 9s (the largest 100 digit
   * number).
   */
  public LookAndSayIterator() {

    this.end = new BigInteger("10").pow(100).subtract(new BigInteger("1"));
    this.current = new BigInteger("1");

  }

  /**
   * this method should return the current number in the sequence and revert to the previous number
   * in the sequence.
   *
   * @return the current number as BigInteger.
   */
  public BigInteger prev() {

    if (hasPrevious()) {
      BigInteger currentNumber = this.current;
      this.current = lookandsayDecoder(currentNumber);

      return currentNumber;
    } else {
      return this.current;
    }
  }

  /**
   * should yield true if it is possible to go back one step, false otherwise.
   *
   * @return true if it is possible to go back one step, false otherwise.
   */
  public boolean hasPrevious() {


    Integer length = this.current.toString().length();

    return length % 2 == 0;
  }

  /**
   * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true}
   * if {@link #next} would return an element rather than throwing an exception.)
   *
   * @return {@code true} if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return current.compareTo(end) == -1;

  }

  /**
   * Returns the next element in the iteration.
   *
   * @return the next element in the iteration
   * @throws NoSuchElementException when seed exceeds end value.
   */
  @Override
  public BigInteger next() throws NoSuchElementException {

    if (hasNext()) {
      BigInteger currentNumber = this.current;

      this.current = lookandsayEncoder(currentNumber);

      return currentNumber;
    } else {
      return this.current;
    }
  }

  /**
   * This method yields the next number in the look and say sequence.
   *
   * @param receivedNumber BigInteger that represents the current Number.
   * @return BigInteger that is the next number in the Look and Say Sequence.
   */
  private BigInteger lookandsayEncoder(BigInteger receivedNumber) {
    ArrayList<Character> big = convertedArray(receivedNumber);

    ArrayList<String> number = new ArrayList<String>();

    int counter = 1;

    if (big.size() == 1) {
      number.add(counter + "");
      number.add(big.get(0) + "");
    } else {

      Character sede = big.get(0);
      for (int i = 1; i < big.size(); i++) {
        if (big.get(i) == sede) {
          counter++;
          if (i == big.size() - 1) {
            number.add(counter + "");
            number.add(big.get(i) + "");
          }
        } else {

          number.add(counter + "");
          number.add(big.get(i - 1) + "");
          counter = 1;
          sede = big.get(i);
          if (i == big.size() - 1) {
            number.add(counter + "");
            number.add(big.get(i) + "");
          }

        }


      }
    }


    return new BigInteger(bigIntegerString(number));
  }


  /**
   * This method yields the previous number in the look and say sequence.
   *
   * @param receivedNumber BigInteger that represents the current Number.
   * @return BigInteger that is the previous number in the Look and Say Sequence.
   */
  private BigInteger lookandsayDecoder(BigInteger receivedNumber) {

    ArrayList<Character> big = convertedArray(receivedNumber);

    ArrayList<String> number = new ArrayList<String>();

    for (int i = 0; i < big.size(); i = i + 2) {


      Integer counter = Integer.parseInt(big.get(i) + "");


      for (int j = 0; j < counter; j++) {

        number.add(big.get(i + 1) + "");

      }

    }

    return new BigInteger(bigIntegerString(number));
  }

  /**
   * This method converts the bigInteger into an arry of Characters.
   *
   * @param receivedNumber is a big Integer.
   * @return ArrayList of Characters that is a representation of BigInteger.
   */
  private ArrayList<Character> convertedArray(BigInteger receivedNumber) {
    String bigRepresntation = receivedNumber.toString();
    Character[] rep = bigRepresntation.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
    ArrayList<Character> big = new ArrayList<Character>(Arrays.asList(rep));
    return big;
  }

  /**
   * This method is used to convert the ArrayList of each digit to a String.
   *
   * @param number ArrayList of Each Digit.
   * @return the String representation of the number.
   */
  private String bigIntegerString(ArrayList<String> number) {
    String nextNumber = "";
    for (String dig : number) {
      nextNumber = nextNumber + dig;
    }
    return nextNumber;
  }
}
