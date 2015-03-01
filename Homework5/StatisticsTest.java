import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 *
 <!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 09.10.2014
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class StatisticsTest {

    /** Subject Under Test. */
    final static Statistics SUT = null;

    /** Desired accuracy. */
    final static double EPSILON = 1.0e-18;

//# BEGIN TODO Test cases to test "good weather" functionality
    /** Reset test. */
    @Test
    public void testReset() {
        System.out.println("reset()");
        SUT.reset();
        int result = SUT.getCount();
        assertEquals("result", 0, result);
    }

    /** Reset test. */
    @Test
    public void testReset2() {
        System.out.println("reset()");
        SUT.reset();
        SUT.update(4);
        SUT.update(10);
        SUT.reset();
        int result = SUT.getCount();
        assertEquals("result", 0, result);
    }

    /** Count test. */
    @Test
    public void testGetCount() {
        System.out.println("getCount()");
        SUT.reset();
        SUT.update(4);
        SUT.update(10);
        int result = SUT.getCount();
        assertEquals("result", 2, result);
    }

    /** Mean test. */
    @Test
    public void testGetMean() {
        System.out.println("getMean()");
        SUT.reset();
        SUT.update(4);
        SUT.update(10);
        double result = SUT.getMean();
        assertEquals("result", 7.0, result, EPSILON);
    }

    /** Mean test. */
    @Test
    public void testGetMean2() {
        System.out.println("getMean()");
        SUT.reset();
        SUT.update(0);
        double result = SUT.getMean();
        assertEquals("result", 0.0, result, EPSILON);
    }

    /** Mean test. */
    @Test
    public void testGetMean3() {
        System.out.println("getMean()");
        SUT.reset();
        SUT.update(Integer.MAX_VALUE);
        double result = SUT.getMean();
        assertEquals("result", Integer.MAX_VALUE, result, EPSILON);
    }

    /** Mean test. */
    @Test
    public void testGetMean4() {
        System.out.println("getMean()");
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        double result = SUT.getMean();
        assertEquals("result", Integer.MIN_VALUE, result, EPSILON);
    }

    /** Mean test. */
    @Test
    public void testGetMean5() {
        System.out.println("getMean()");
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        SUT.update(Integer.MAX_VALUE);
        double result = SUT.getMean();
        assertEquals("result", -0.5, result, EPSILON);
    }

    /** Mean test. */
    @Test
    public void testGetMean6() {
        System.out.println("getMean()");
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        SUT.update(1);
        SUT.update(Integer.MAX_VALUE);
        double result = SUT.getMean();
        assertEquals("result", 0.0, result, EPSILON);
    }

    /** Mean test. */
    @Test
    public void testGetMean7() {
        System.out.println("getMean()");
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        SUT.update(Integer.MIN_VALUE);
        double result = SUT.getMean();
        assertEquals("result", Integer.MIN_VALUE, result, EPSILON);
    }

    /** Mean test. */
    @Test
    public void testGetMean8() {
        System.out.println("getMean()");
        SUT.reset();
        SUT.update(Integer.MAX_VALUE);
        SUT.update(Integer.MAX_VALUE);
        double result = SUT.getMean();
        assertEquals("result", Integer.MAX_VALUE, result, EPSILON);
    }

    /** Mean test. */
    @Test
    public void testGetMean9() {
        System.out.println("getMean()");
        SUT.reset();
        SUT.update(Integer.MAX_VALUE);
        SUT.update(Integer.MIN_VALUE);
        SUT.update(Integer.MAX_VALUE);
        SUT.update(Integer.MIN_VALUE);
        double result = SUT.getMean();
        assertEquals("result", -0.5, result, EPSILON);
    }

    /** Maximum test. */
    @Test
    public void testGetMaximum() {
        System.out.println("getMaximum()");
        SUT.reset();
        SUT.update(4);
        SUT.update(10);
        int result = SUT.getMaximum();
        assertEquals("result", 10, result);
    }

    /** Maximum test. */
    @Test
    public void testGetMaximum2() {
        System.out.println("getMaximum()");
        SUT.reset();
        SUT.update(10);
        SUT.update(Integer.MAX_VALUE);
        int result = SUT.getMaximum();
        assertEquals("result", Integer.MAX_VALUE, result);
    }

    /** Maximum test. */
    @Test
    public void testGetMaximum3() {
        System.out.println("getMaximum()");
        SUT.reset();
        SUT.update(4);
        int result = SUT.getMaximum();
        assertEquals("result", 4, result);
    }

    /** Maximum test. */
    @Test
    public void testGetMaximum4() {
        System.out.println("getMaximum()");
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        int result = SUT.getMaximum();
        assertEquals("result", Integer.MIN_VALUE, result);
    }

    /** Maximum test. */
    @Test
    public void testGetMaximum5() {
        System.out.println("getMaximum()");
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        SUT.update(Integer.MAX_VALUE);
        int result = SUT.getMaximum();
        assertEquals("result", Integer.MAX_VALUE, result);
    }

    /** Minimum test. */
    @Test
    public void testGetMinimum() {
        System.out.println("getMinimum()");
        SUT.reset();
        SUT.update(4);
        SUT.update(10);
        int result = SUT.getMinimum();
        assertEquals("result", 4, result);
    }

    /** Minimum test. */
    @Test
    public void testGetMinimum2() {
        System.out.println("getMinimum()");
        SUT.reset();
        SUT.update(4);
        SUT.update(Integer.MIN_VALUE);
        int result = SUT.getMinimum();
        assertEquals("result", Integer.MIN_VALUE, result);
    }

    /** Minimum test. */
    @Test
    public void testGetMinimum3() {
        System.out.println("getMinimum()");
        SUT.reset();
        SUT.update(4);
        int result = SUT.getMinimum();
        assertEquals("result", 4, result);
    }

    /** Minimum test. */
    @Test
    public void testGetMinimum4() {
        System.out.println("getMinimum()");
        SUT.reset();
        SUT.update(Integer.MIN_VALUE);
        SUT.update(Integer.MAX_VALUE);
        int result = SUT.getMinimum();
        assertEquals("result", Integer.MIN_VALUE, result);
    }

    /** Minimum test. */
    @Test
    public void testGetMinimum5() {
        System.out.println("getMinimum()");
        SUT.reset();
        SUT.update(Integer.MAX_VALUE);
        int result = SUT.getMinimum();
        assertEquals("result", Integer.MAX_VALUE, result);
    }
//# END TODO

//# BEGIN TODO Test cases to test for robustness ("bad weather")
    /** Minimum test for robustness. */
    @Test
    public void testGetMinimumIllegalState() {
        System.out.println("getMinimum()");
        Class expected = IllegalStateException.class;
        try {
            SUT.reset();
            SUT.getMinimum();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /** Minimum test for robustness. */
    @Test
    public void testGetMinimumIllegalState2() {
        System.out.println("getMinimum()");
        Class expected = IllegalStateException.class;
        try {
            SUT.reset();
            SUT.update(1);
            SUT.reset();
            SUT.getMinimum();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /** Maximum test for robustness. */
    @Test
    public void testGetMaximumIllegalState() {
        System.out.println("getMaximum()");
        Class expected = IllegalStateException.class;
        try {
            SUT.reset();
            SUT.getMaximum();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /** Maximum test for robustness. */
    @Test
    public void testGetMaximumIllegalState2() {
        System.out.println("getMaximum()");
        Class expected = IllegalStateException.class;
        try {
            SUT.reset();
            SUT.update(1);
            SUT.reset();
            SUT.getMaximum();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /** Mean test for robustness. */
    @Test
    public void testGetMeanIllegalState() {
        System.out.println("getMean()");
        Class expected = IllegalStateException.class;
        try {
            SUT.reset();
            SUT.getMean();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /** Mean test for robustness. */
    @Test
    public void testGetMeanIllegalState2() {
        System.out.println("getMean()");
        Class expected = IllegalStateException.class;
        try {
            SUT.reset();
            SUT.update(1);
            SUT.reset();
            SUT.getMean();
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }
//# END TODO
}