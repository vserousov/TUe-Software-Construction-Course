import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 * Test cases for DiceGame Decomposed,
 * especially for robustness.
 *
 <!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 08.10.2014
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class DiceGameDecomposedTest {

    /** Subject Under Test; involves only static methods */
    final static DiceGameDecomposed SUT = null;

    /**
     * Calls SUT.count(0, null) when a NullPointerException is expected,
     * and checks the exception.
     */
    @Ignore("Example")
    @Test
    public void testCountNull() {
        System.out.println("count(0, null)");
        Class expected = NullPointerException.class;
        try {
            SUT.count(0, null);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.count(0, empty) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Ignore("Example")
    @Test
    public void testCountEmpty() {
        System.out.println("count(0, empty)");
        SUT.count(0, new int[] { });
    }

//# BEGIN TODO Test cases to test for robustness (simulate, roll, max, find)
    /**
     * Calls SUT.simulate(0, 5) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testSimulateIllegalArgument() {
        System.out.println("simulate(0, 5)");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.simulate(0, 5);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.simulate(1, -1) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testSimulateIllegalArgument2() {
        System.out.println("simulate(1, -1)");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.simulate(0, 5);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.simulate(4, 5) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Test
    public void testSimulate() {
        System.out.println("simulate(4, 5)");
        SUT.simulate(4, 5);
    }

    /**
     * Calls SUT.simulate(1, 0) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Test
    public void testSimulate2() {
        System.out.println("simulate(1, 0)");
        SUT.simulate(1, 0);
    }

    /**
     * Calls SUT.roll(0) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testRollIllegalArgument() {
        System.out.println("roll(0)");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.roll(0);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.roll(-5) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testRollIllegalArgument2() {
        System.out.println("roll(-5)");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.roll(-5);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.roll(2) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Test
    public void testRoll() {
        System.out.println("roll(2)");
        SUT.roll(2);
    }

    /**
     * Calls SUT.roll(1) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Test
    public void testRoll2() {
        System.out.println("roll(1)");
        SUT.roll(1);
    }

    /**
     * Calls SUT.max(null) when a NullPointerException is expected,
     * and checks the exception.
     */
    @Test
    public void testMaxNullPointer() {
        System.out.println("max(null)");
        Class expected = NullPointerException.class;
        try {
            SUT.max(null);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.max(new int[] { }) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testMaxIllegalArgument() {
        System.out.println("max(new int[] { })");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.max(new int[] { });
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.max(new int[] { 5, -2, 3 }) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testMaxIllegalArgument2() {
        System.out.println("max(new int[] { 5, -2, 3 })");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.max(new int[] { 5, -2, 3 });
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.max(new int[] { -1 }) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testMaxIllegalArgument3() {
        System.out.println("max(new int[] { -1 })");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.max(new int[] { -1 });
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.max(new int[] { 5, 2, 8, 9 }) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Test
    public void testMax() {
        System.out.println("max(new int[] { 5, 2, 8, 9 })");
        SUT.max(new int[] { 5, 2, 8, 9 });
    }

    /**
     * Calls SUT.max(new int[] { 5 }) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Test
    public void testMax2() {
        System.out.println("max(new int[] { 5 })");
        SUT.max(new int[] { 5 });
    }

    /**
     * Calls SUT.find(0, null) when a NullPointerException is expected,
     * and checks the exception.
     */
    @Test
    public void testFindNullPointer() {
        System.out.println("find(0, null)");
        Class expected = NullPointerException.class;
        try {
            SUT.find(0, null);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.find(3, new int[] { 2, 6, 4, 7 }) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testFindIllegalArgument() {
        System.out.println("find(3, new int[] { 2, 6, 4, 7 })");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.find(3, new int[] { 2, 6, 4, 7 });
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.find(3, new int[] { }) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testFindIllegalArgument2() {
        System.out.println("find(3, new int[] { })");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.find(3, new int[] { });
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.find(3, new int[] { -5 }) when a IllegalArgumentException is expected,
     * and checks the exception.
     */
    @Test
    public void testFindIllegalArgument3() {
        System.out.println("find(3, new int[] { })");
        Class expected = IllegalArgumentException.class;
        try {
            SUT.find(3, new int[] { -5 });
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }

    /**
     * Calls SUT.find(2, new int[] { 2, 6, 4, 7 }) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Test
    public void testFind() {
        System.out.println("find(2, new int[] { 2, 6, 4, 7 })");
        SUT.find(2, new int[] { 5, 2, 8, 9 });
    }

    /**
     * Calls SUT.find(2, new int[] { 2 }) when NO Exception is expected,
     * and checks the absence of an exception (implicitly).
     */
    @Test
    public void testFind2() {
        System.out.println("find(2, new int[] { 2 })");
        SUT.find(2, new int[] { 2 });
    }
//# END TODO
}