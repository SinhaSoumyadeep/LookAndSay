import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import lookandsay.LookAndSayIterator;
import lookandsay.RIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This Class is used to check the Implementation of the LookAndSayIterator.
 */
public class LookAndSayIteratorTest {

  private RIterator lookSayIteratorNoArguments;
  private RIterator lookSayIteratorOnlySeed;
  private RIterator lookSayIteratorSeedAndEnd;

  /**
   * This method initializes look and say iterators with default contructor, Constructor with only
   * seed and constructor with seed and end values.
   */
  @Before
  public void setUp() throws Exception {

    lookSayIteratorNoArguments = new LookAndSayIterator();
    lookSayIteratorOnlySeed = new LookAndSayIterator(new BigInteger("11221134411113344267"));
    lookSayIteratorSeedAndEnd = new LookAndSayIterator(new BigInteger("112321"),
            new BigInteger(
                    "9999999999999999999999999999999999999999999"));

  }

  /**
   * testing LookAndSayIterator with seed being null.
   */
  @Test
  public void testNullSeed() {
    try {
      lookSayIteratorSeedAndEnd = new LookAndSayIterator(null, new BigInteger("112321"));
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("seed or end cannot be null", iae.getMessage());
    }
  }


  /**
   * testing LookAndSayIterator with end being null.
   */
  @Test
  public void testNullEndValue() {
    try {
      lookSayIteratorSeedAndEnd = new LookAndSayIterator(new BigInteger("112321"), null);
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("seed or end cannot be null", iae.getMessage());
    }
  }


  /**
   * testing LookAndSayIterator with seed being negative.
   */
  @Test
  public void testSeedNegative() {
    try {
      lookSayIteratorSeedAndEnd = new LookAndSayIterator(new BigInteger("-1"),
              new BigInteger("112321"));
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("invalid argument", iae.getMessage());
    }
  }

  /**
   * testing LookAndSayIterator with seed greater than end.
   */
  @Test
  public void testSeedGreaterThanEndValue() {
    try {
      lookSayIteratorSeedAndEnd = new LookAndSayIterator(new BigInteger("177"),
              new BigInteger("11"));
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("invalid argument", iae.getMessage());
    }
  }


  /**
   * testing LookAndSayIterator with seed being null for Seed Only Constructor.
   */
  @Test
  public void testNullSeedForSeedOnlyConstructor() {
    try {
      lookSayIteratorOnlySeed = new LookAndSayIterator(null);
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("seed cannot be null", iae.getMessage());
    }
  }

  /**
   * testing LookAndSayIterator with seed being negative for Seed Only Constructor.
   */
  @Test
  public void testSeedNegativeSeedOnlyConstructor() {
    try {
      lookSayIteratorOnlySeed = new LookAndSayIterator(new BigInteger("-1"));
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("invalid argument", iae.getMessage());
    }
  }

  /**
   * testing LookAndSayIterator with seed being greater than end for Seed Only Constructor.
   */
  @Test
  public void testSeedGreaterThanEndValueSeedOnlyConstructor() {
    try {

      BigInteger x = new BigInteger("10").pow(102);
      lookSayIteratorOnlySeed = new LookAndSayIterator(x);
      fail();
    } catch (IllegalArgumentException iae) {
      assertEquals("invalid argument", iae.getMessage());
    }
  }

  /**
   * testing LookAndSayIterator with seed trying to exceed the end value.
   */
  @Test
  public void testSeedNextExceeded() {

    lookSayIteratorSeedAndEnd = new LookAndSayIterator(new BigInteger("1"),
            new BigInteger("21"));
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    lookSayIteratorSeedAndEnd.next();
    assertEquals(new BigInteger("21"), lookSayIteratorSeedAndEnd.next());

  }

  /**
   * testing LookAndSayIterator with seed trying to go below the possible previous value.
   */
  @Test
  public void testSeedPreviousExceeded() {


    lookSayIteratorOnlySeed = new LookAndSayIterator(new BigInteger("21"));
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    lookSayIteratorOnlySeed.prev();
    assertEquals(new BigInteger("1"), lookSayIteratorOnlySeed.prev());

  }

  /**
   * This method tries to test the next value when 22 is passed as a seed.
   */
  @Test
  public void testSeedCaseWithTwoTwosInfiniteLoopTestingNext() {


    try {
      lookSayIteratorOnlySeed = new LookAndSayIterator(new BigInteger("22"));
      while (lookSayIteratorOnlySeed.hasNext()) {
        lookSayIteratorOnlySeed.next();
      }
    } catch (IllegalArgumentException ise) {
      assertEquals("22 case.", ise.getMessage());
    }

  }

  /**
   * This method tries to test the previous value when 22 is passed as a seed.
   */
  @Test
  public void testSeedCaseWithTwoTwosInfiniteLoopTestingPrevious() {

    try {
      lookSayIteratorOnlySeed = new LookAndSayIterator(new BigInteger("22"));
      while (lookSayIteratorOnlySeed.hasPrevious()) {
        lookSayIteratorOnlySeed.prev();
      }
      fail();
    } catch (IllegalArgumentException ise) {
      assertEquals("22 case.", ise.getMessage());
    }
  }

  /**
   * This method tries to test the previous value when Current Value Have a Zero.
   */
  @Test
  public void testSeedTestingPreviousWithCurrentValueHavingZero() {
    lookSayIteratorOnlySeed = new LookAndSayIterator(new BigInteger("1012"));
    assertEquals(new BigInteger("1012"), lookSayIteratorOnlySeed.prev());
    assertEquals(new BigInteger("2"), lookSayIteratorOnlySeed.prev());
    assertEquals(new BigInteger("2"), lookSayIteratorOnlySeed.prev());

  }


  /**
   * This method tries to test seed with same digit occuring more than nine times.
   */
  @Test
  public void testSeedWithSeedNumberHavingSameDigitsOccuringMoreThanNine() {
    lookSayIteratorOnlySeed = new LookAndSayIterator(new BigInteger("11111111111111111111"));
    assertEquals(new BigInteger("11111111111111111111"), lookSayIteratorOnlySeed.next());
    assertEquals(new BigInteger("201"), lookSayIteratorOnlySeed.next());

  }


  /**
   * This method tests the no argument constructor.
   */
  @Test
  public void testNormalNoArgumentConstructor() {

    BigInteger next = (BigInteger) lookSayIteratorNoArguments.next();
    assertEquals(new BigInteger("1"), next);
    next = (BigInteger) lookSayIteratorNoArguments.next();
    assertEquals(new BigInteger("11"), next);
    next = (BigInteger) lookSayIteratorNoArguments.next();
    assertEquals(new BigInteger("21"), next);
    next = (BigInteger) lookSayIteratorNoArguments.next();
    assertEquals(new BigInteger("1211"), next);
    next = (BigInteger) lookSayIteratorNoArguments.next();
    assertEquals(new BigInteger("111221"), next);
    next = (BigInteger) lookSayIteratorNoArguments.prev();
    assertEquals(new BigInteger("312211"), next);
    next = (BigInteger) lookSayIteratorNoArguments.prev();
    assertEquals(new BigInteger("111221"), next);
    next = (BigInteger) lookSayIteratorNoArguments.prev();
    assertEquals(new BigInteger("1211"), next);
    next = (BigInteger) lookSayIteratorNoArguments.prev();
    assertEquals(new BigInteger("21"), next);
    next = (BigInteger) lookSayIteratorNoArguments.prev();
    assertEquals(new BigInteger("11"), next);

  }

  /**
   * This method tests the Seed Only Constructor.
   */
  @Test
  public void testSimpleLookAndSaySeedOnlyConstructor() {

    BigInteger next = (BigInteger) lookSayIteratorOnlySeed.next();
    assertEquals(new BigInteger("11221134411113344267"), next);
    next = (BigInteger) lookSayIteratorOnlySeed.next();
    assertEquals(new BigInteger("2122211324412324121617"), next);
    next = (BigInteger) lookSayIteratorOnlySeed.next();
    assertEquals(new BigInteger("121132211312241112131214111211161117"), next);
    next = (BigInteger) lookSayIteratorOnlySeed.next();
    assertEquals(new BigInteger("111221132221131122143112111311121114311231163117"), next);
    next = (BigInteger) lookSayIteratorOnlySeed.next();
    assertEquals(new BigInteger("3122211332211321221114132112311331123114132112132116132117"),
            next);
    next = (BigInteger) lookSayIteratorOnlySeed.prev();
    assertEquals(new BigInteger("1311322123222113121122311411131221121321232112" +
            "132114111312211211131221161113122117"), next);
    next = (BigInteger) lookSayIteratorOnlySeed.prev();
    assertEquals(new BigInteger("31222113322113212211141321123113311231141321" +
            "12132116132117"), next);
    next = (BigInteger) lookSayIteratorOnlySeed.prev();
    assertEquals(new BigInteger("111221132221131122143112111311121114311231163117"), next);
    next = (BigInteger) lookSayIteratorOnlySeed.prev();
    assertEquals(new BigInteger("121132211312241112131214111211161117"), next);

  }


  /**
   * This method tests the Constructor that takes in Seed and End values.
   */
  @Test
  public void testSimpleLookAndSaySeedAndEndValues() {

    BigInteger next = (BigInteger) lookSayIteratorSeedAndEnd.next();
    assertEquals(new BigInteger("112321"), next);
    next = (BigInteger) lookSayIteratorSeedAndEnd.next();
    assertEquals(new BigInteger("2112131211"), next);
    next = (BigInteger) lookSayIteratorSeedAndEnd.next();
    assertEquals(new BigInteger("1221121113111221"), next);
    next = (BigInteger) lookSayIteratorSeedAndEnd.next();
    assertEquals(new BigInteger("112221123113312211"), next);
    next = (BigInteger) lookSayIteratorSeedAndEnd.next();
    assertEquals(new BigInteger("21322112132123112221"), next);
    next = (BigInteger) lookSayIteratorSeedAndEnd.prev();
    assertEquals(new BigInteger("121113222112111312111213213211"), next);
    next = (BigInteger) lookSayIteratorSeedAndEnd.prev();
    assertEquals(new BigInteger("21322112132123112221"), next);
    next = (BigInteger) lookSayIteratorSeedAndEnd.prev();
    assertEquals(new BigInteger("112221123113312211"), next);
    next = (BigInteger) lookSayIteratorSeedAndEnd.prev();
    assertEquals(new BigInteger("1221121113111221"), next);
    next = (BigInteger) lookSayIteratorSeedAndEnd.prev();
    assertEquals(new BigInteger("2112131211"), next);
  }

  /**
   * Test if the hasNext method works correctly.
   */
  @Test
  public void testHasNext() {
    RIterator lookAndSayNew = new LookAndSayIterator(new BigInteger("1"),
            new BigInteger("1111111111111111"));
    assertEquals(true, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("1"), lookAndSayNew.next());
    assertEquals(true, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("11"), lookAndSayNew.next());
    assertEquals(true, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("21"), lookAndSayNew.next());
    assertEquals(true, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("1211"), lookAndSayNew.next());
    assertEquals(true, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("111221"), lookAndSayNew.next());
    assertEquals(true, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("312211"), lookAndSayNew.next());
    assertEquals(true, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("13112221"), lookAndSayNew.next());
    assertEquals(true, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("1113213211"), lookAndSayNew.next());
    assertEquals(true, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("31131211131221"), lookAndSayNew.next());
    assertEquals(false, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("13211311123113112211"), lookAndSayNew.next());
    assertEquals(false, lookAndSayNew.hasNext());
    assertEquals(new BigInteger("13211311123113112211"), lookAndSayNew.next());


  }

  /**
   * Test if hasPrev methods function correctly.
   */
  @Test
  public void testHasPrev() {
    RIterator lookAndSayIterator = new LookAndSayIterator(new BigInteger("312211"));
    assertEquals(true, lookAndSayIterator.hasPrevious());
    assertEquals(new BigInteger("312211"), lookAndSayIterator.prev());
    assertEquals(true, lookAndSayIterator.hasPrevious());
    assertEquals(new BigInteger("111221"), lookAndSayIterator.prev());
    assertEquals(true, lookAndSayIterator.hasPrevious());
    assertEquals(new BigInteger("1211"), lookAndSayIterator.prev());
    assertEquals(true, lookAndSayIterator.hasPrevious());
    assertEquals(new BigInteger("21"), lookAndSayIterator.prev());
    assertEquals(true, lookAndSayIterator.hasPrevious());
    assertEquals(new BigInteger("11"), lookAndSayIterator.prev());
    assertEquals(false, lookAndSayIterator.hasPrevious());
    assertEquals(new BigInteger("1"), lookAndSayIterator.prev());
  }

  /**
   * Test to check if next method works correctly when given a seed and an end value with while
   * loop.
   */
  @Test
  public void testNextInLoop() {
    RIterator lookAndSayIterator = new LookAndSayIterator(new BigInteger("11"),
            new BigInteger("999999999999999999999999999999999999999999999999999999"));
    String temp = "";
    while (lookAndSayIterator.hasNext()) {
      temp = lookAndSayIterator.next().toString();
    }
    assertEquals("1321132132111213122112311311222113111221131221", temp);

  }


}