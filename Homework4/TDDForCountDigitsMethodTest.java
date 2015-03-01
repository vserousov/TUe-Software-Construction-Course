//# BEGIN SKELETON
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for countDigits method, of class TDDForCountDigitsMethod.
 *
<!--//# BEGIN TODO Name, student id, and date-->
Serousov Vitaly, 201 SE, 02.10.2014
<!--//# END TODO -->
 */
// -----8<----- cut line -----8<-----
public class TDDForCountDigitsMethodTest {

    /** Subject Under Test.  Only static members are used. */
    private static final TDDForCountDigitsMethod SUT = null;

    /**
     * Invokes countDigits(n, r) and checks result against expectation.
     *
     * @param n  the number whose digits are counted
     * @param r  the radix
     * @param expResult  the expected result
     */
    private void checkCountDigits(long n, long r, int expResult) {
        System.out.println("countDigits(" + n + ", " + r + ")");
        int result = SUT.countDigits(n, r);
        assertEquals("result", expResult, result);
    }

//# BEGIN TODO Test cases
    /** Test case for number 0. */
    @Test
    public void testCountDigits0() {
        checkCountDigits(0L, 10, 1);
    }

    /** Test case for number 9. */
    @Test
    public void testCountDigits9() {
        checkCountDigits(9, 10, 1);
    }

    /** Test case for number 10. */
    @Test
    public void testCountDigits10() {
        checkCountDigits(10L, 10, 2);
    }

    /** Test case for number 1000. */
    @Test
    public void testCountDigits1000() {
        checkCountDigits(1000L, 10, 4);
    }

    /** Different test cases. */
    @Test
    public void testCountDigitsSmall() {
        long n = 1L;
        for (int r = 0; r <= 5; ++ r) {
            checkCountDigits(n - 1, 10, Math.max(1, r));
            checkCountDigits(n, 10, r + 1);
            n *= 10;
        }
    }

    /** Test for max value of number. */
    @Test(timeout = 1000)
    public void testCountNumberMaxValue() {
        checkCountDigits(Long.MAX_VALUE, 10, 19);
    }

    /**  Test for max value number and notation. */
    @Test(timeout = 1000)
    public void testCountNotationNumberMaxValue() {
        checkCountDigits(Long.MAX_VALUE, Long.MAX_VALUE, 2);
    }

    /** Test for max value of notation. */
    @Test(timeout = 1000)
    public void testCountNotationMaxValue() {
        checkCountDigits(1, Long.MAX_VALUE, 1);
    }

    /** Test for exception if number less than 0. */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeNumber() {
        SUT.countDigits(-1, 2);
    }

    /** Test for exception if radix less than 2. */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNotation() {
        SUT.countDigits(2, 1);
    }

    /** Test for exception if radix less than 2 and number is less than 0. */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNumberNotation() {
        SUT.countDigits(-1, 1);
    }
//# END TODO

}
//# END SKELETON
