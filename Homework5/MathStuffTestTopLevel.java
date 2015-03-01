//# BEGIN SKELETON
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for top-level methods in {@code MathStuff}.
 *
<!--//# BEGIN TODO Name, student id, and date-->
Serousov Vitaly, 201 SE, 08.10.2014
<!--//# END TODO-->
*/
// -----8<----- cut line -----8<-----
public class MathStuffTestTopLevel {

    // Test cases for power(int, int).

    /**
     * Invokes power(a, b) and checks for expected result.
     *
     * @param a  the base
     * @param b  the exponent
     * @param expResult  the expected result
     * @pre {@code 0 <= b && expResult = a ^ b}
     */
    private void checkPower(int a, int b, long expResult) {
        System.out.println("power(" + a + ", " + b + ")");
        long result = MathStuff.power(a, b);
        Assert.assertEquals("result", expResult, result);
    }

    /** Smallest exponent. */
    @Test
    public void testPower0() {
        checkPower(0, 0, 1);
    }

    /** Exponent 1. */
    @Test
    public void testPower1() {
        checkPower(2, 1, 2);
    }

    /** Exponent 2. */
    @Test
    public void testPower2() {
        checkPower(3, 2, 9);
    }

     /** Largest base and smallest exponent without overflow. */
    @Test(timeout = 100)
    public void testPowerSmallestNoOverflow() {
        int n = Integer.MAX_VALUE;
        checkPower(n, 1, n);
    }

    /** Smallest base > 1 and largest exponent without overflow. */
    @Test(timeout = 100)
    public void testPowerLargestNoOverflow() {
        checkPower(2, 30, Integer.MAX_VALUE / 2 + 1);
    }

   /** Largest base and smallest exponent > 1 with overflow. */
    @Test(timeout = 100)
    public void testPowerSmallestOverflow() {
        checkPower(46341, 2, Long.MAX_VALUE);
    }

    /** Smallest base > 1 and smallest exponent with overflow. */
    @Test(timeout = 100)
    public void testPowerLargestOverflow() {
        checkPower(2, 31, Long.MAX_VALUE);
    }

    /**
     * Invokes {@code power(a, b)} and checks for expected exception.
     * 
     * @param a  base
     * @param b  exponent
     * @param expected   expected exception
     */
    private void checkPowerException(int a, int b, Class expected) {
        System.out.println("power(" + a + ", " + b + "), for exception");
        try {
            MathStuff.power(a, b);
            Assert.fail("should have thrown " + expected);
        } catch (Exception e) {
            Assert.assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            Assert.assertNotNull("message should not be null",
                    e.getMessage());
        }
    }
        
    /** Negative base, non-negative exponent. */
    @Test(timeout = 100)
    public void testPowerExceptionA() {
        checkPowerException(-1, 0, IllegalArgumentException.class);
    }
    
    /** Negative exponent, non-negative base. */
    @Test(timeout = 100)
    public void testPowerExceptionB() {
        checkPowerException(0, -1, IllegalArgumentException.class);
    }
    
    // Test cases for power(Power)

    /** Smoke test. */
    @Test
    public void testPowerPower() {
        System.out.println("power(new Power(3, 2))");
        MathStuff.Power p = new MathStuff.Power(3, 2);
        Assert.assertEquals("result", 3 * 3, MathStuff.power(p));
    }

    // Test cases for powerize(int)

    /**
     * Invokes powerize(power(expB, expE)) and checks for expected result.
     *
     * @param expB  expected base
     * @param expE  expected exponent
     * @pre {@code expB} is not a power with exponent greater than one
     */
    private void checkPowerize(int expB, int expE) {
        long n = MathStuff.power(expB, expE);
        System.out.println("powerize(" + n + ")");
        MathStuff.Power result = MathStuff.powerize((int)n);
        Assert.assertEquals("power", n, MathStuff.power(result));
        Assert.assertEquals("base", expB, result.base);
        Assert.assertEquals("exponent", expE, result.exponent);
    }

//# BEGIN TODO Implementations of test cases for powerize(int)
    /** Test for exception where n = 0. */
    @Test(expected = IllegalArgumentException.class)
    public void testPowerize0() {
        MathStuff.powerize(0);
    }

    /** Test for exception where n = 1. */
    @Test(expected = IllegalArgumentException.class)
    public void testPowerize1() {
        MathStuff.powerize(1);
    }

    /** Test for powerize where n = 2. */
    @Test
    public void testPowerize2() {
        checkPowerize(2, 1);
    }

    /** Test for powerize where n = Integer Max Value. */
    @Test(timeout = 1000)
    public void testPowerizeMaxValue() {
        checkPowerize(Integer.MAX_VALUE, 1);
    }

    /** Test for powerize where {@code n = 1 ^ Integer.MAX_VALUE}. */
    @Test(expected = IllegalArgumentException.class)
    public void testPowerizeMaxValue2() {
        checkPowerize(1, Integer.MAX_VALUE);
    }

    /** Test for powerize where {@code n = Integer.MAX_VALUE ^ Integer.MAX_VALUE}. */
    @Test(expected = IllegalArgumentException.class)
    public void testPowerizeMaxValue3() {
        checkPowerize(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /** Test for powerize where n = 4. */
    @Test(timeout = 1000)
    public void testPowerize4() {
        checkPowerize(2, 2);
    }

    /** Test for powerize where n = 6. */
    @Test
    public void testPowerize6() {
        checkPowerize(6, 1);
    }

    /** Test for powerize where n = 16. */
    @Test
    public void testPowerize16() {
        checkPowerize(2, 4);
    }

    /** Test for powerize where n = 36. */
    @Test
    public void testPowerize36() {
        checkPowerize(6, 2);
    }

    /** Test for powerize where n = 125. */
    @Test
    public void testPowerize125() {
        checkPowerize(5, 3);
    }

    /** Test for powerize where n = 1073741824. */
    @Test
    public void testPowerize2e30() {
        checkPowerize(2, 30);
    }
//# END TODO

}
//# END SKELETON
