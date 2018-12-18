package lookandsay;

import java.util.Iterator;

/**
 * A look-and-say sequence is a sequence of numbers. Given the current number, the next number is
 * obtained by reading the current number out loud, writing what we say. For example, if the current
 * number is 1, then we read that as “one 1” and thus the next number in the sequence will be 11.
 * Similarly, if the current number is 112321, then we read that as “two 1s, one 2, one 3, one 2,
 * one 1”, producing the next number as  2112131211, and so on. A look-and-say sequence is a simple
 * example of run-length encoding.The look-and-say sequence can also be computed in reverse. For
 * example 2112131211 can be reversed by taking digits two at a time, and writing them out (write
 * two 1s, then one 2, and so on). This produces 112321. Work out a couple of examples of the
 * sequence before proceeding. RIterator T interface extends Iterator T and adds two methods:  T
 * prev() which should return the current number in the sequence and revert to the previous number
 * in the sequence. and boolean hasPrevious()should return true if it is possible to go back one
 * step.
 */
public interface RIterator<T> extends Iterator<T> {
  /**
   * This method should return the current number in the sequence and revert to the previous number
   * in the sequence.
   *
   * @return the current number as BigInteger.
   */
  T prev();

  /**
   * This method should yield true if it is possible to go back one step, false otherwise.
   *
   * @return true if it is possible to go back one step, false otherwise.
   */
  boolean hasPrevious();

}
