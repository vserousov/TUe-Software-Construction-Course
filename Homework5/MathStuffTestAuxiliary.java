//# BEGIN SKELETON
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for auxiliary methods in {@code MathStuff}.
 *
<!--//# BEGIN TODO Name, student id, and date-->
Serousov Vitaly, 201 SE, 08.10.2014
<!--//# END TODO-->
*/
// -----8<----- cut line -----8<-----
public class MathStuffTestAuxiliary {

//# BEGIN TODO Test cases for auxiliary functions

    /**
     * Invokes binarySearchBase(e, n) and checks for expected result.
     *
     * @param e  the exponent
     * @param n  the number
     * @param expResult  the expected result
     * @pre {@code 2 <= e <= 31 && n >= 2}
     */
    private void checkBinarySearchBase(int e, int n, int expResult) {
        System.out.println("binarySearchBase(" + e + ", " + n + ")");
        int result = MathStuff.binarySearchBase(e, n);
        Assert.assertEquals("result", expResult, result);
    }

    /** Test for BinarySearchBase where e = 3 and n = 27. */
    @Test
    public void testBinarySearchBase1() {
        checkBinarySearchBase(3, 27, 3);
    }

    /** Test for BinarySearchBase where e = 2 and n = 16. */
    @Test
    public void testBinarySearchBase2() {
        checkBinarySearchBase(4, 16, 2);
    }

    /** Test for BinarySearchBase where e = 5 and n = 625. */
    @Test
    public void testBinarySearchBase3() {
        checkBinarySearchBase(4, 625, 5);
    }

    /** Test for BinarySearchBase where {@code e = n = Integer.MAX_VALUE}. */
    @Test(expected = IllegalArgumentException.class)
    public void testBinarySearchBaseMaxValue() {
        System.out.println("binarySearchBase(Integer.MAX_VALUE, Integer.MAX_VALUE)");
        MathStuff.binarySearchBase(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /** Test for BinarySearchBase which throws exception. */
    @Test(expected = IllegalArgumentException.class)
    public void testBinarySearchBase4() {
        System.out.println("binarySearchBase(0, 3)");
        MathStuff.binarySearchBase(0, 3);
    }

    /** Test for BinarySearchBase which throws exception. */
    @Test(expected = IllegalArgumentException.class)
    public void testBinarySearchBase5() {
        System.out.println("binarySearchBase(2, 2)");
        MathStuff.binarySearchBase(2, 2);
    }
//# END TODO

}
//# END SKELETON
