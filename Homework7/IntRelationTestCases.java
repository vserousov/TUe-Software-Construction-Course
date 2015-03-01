import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Abstract test cases for IntRelation, to be extended to obtain
 * concrete test cases for an extension of IntRelation.
 * 
 * Serousov Vitaly, 201 SE, 22.10.2014
 */
// -----8<----- cut line -----8<-----
public abstract class IntRelationTestCases {
    
    /** Test fixture. */
    protected IntRelation instance;
    
    /**
     * Sets instance to a newly constructed relation of given extent.
     * 
     * @param n   extent
     */
    protected abstract void setInstance(final int n);
    
    /** Tests the constructor with small values. */
    @Test
    public void testConstructor() {
        System.out.println("constructor(int)");
        for (int n = 0; n <= 3; ++ n) {
            setInstance(n);
            assertTrue("isRepOk()", instance.isRepOk());
        }
    }

    /** Tests the constructor for robustness. */
    @Test
    public void robustTestConstructor() {
        checkConstructorException(-1, IllegalArgumentException.class);
        checkConstructorException(-1000, IllegalArgumentException.class);
        checkConstructorException(Integer.MIN_VALUE, IllegalArgumentException.class);
    }
    
    /** Tests the extent method with small relations. */
    @Test
    public void testExtent() {
        System.out.println("extent");
        for (int n = 0; n <= 3; ++ n) {
            setInstance(n);
            assertEquals("size", n, instance.extent());
            assertTrue("isRepOk()", instance.isRepOk());
        }
    }
    
    /**
     * Invokes areRelated(a, b) and checks the result.
     * 
     * @param a  first element in pair
     * @param b  second element in pair
     * @param expResult  expected result
     */
    private void checkAreRelated(int a, int b, boolean expResult) {
        boolean result = instance.areRelated(a, b);
        assertEquals("areRelated(" + a + ", " + b + ")", expResult, result);
        assertTrue("isRepOk()", instance.isRepOk());
    }
    
    /** Tests the areRelated method on empty relation. */
    @Test
    public void testAreRelated() {
        System.out.println("areRelated");
        setInstance(1);
        checkAreRelated(0, 0, false);
        setInstance(2);
        checkAreRelated(0, 0, false);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }

    /** Tests the areRelated method for robustness. */
    @Test
    public void robustTestAreRelated() {
        setInstance(2);
        instance.add(0, 1);
        checkAreRelatedException(3, 1, IllegalArgumentException.class);
        checkAreRelatedException(1, 3, IllegalArgumentException.class);
        checkAreRelatedException(3, 3, IllegalArgumentException.class);
        checkAreRelatedException(-1, 1, IllegalArgumentException.class);
        checkAreRelatedException(1, -1, IllegalArgumentException.class);
        checkAreRelatedException(Integer.MIN_VALUE, 1, IllegalArgumentException.class);
        checkAreRelatedException(1, Integer.MIN_VALUE, IllegalArgumentException.class);
        checkAreRelatedException(Integer.MAX_VALUE, 1, IllegalArgumentException.class);
        checkAreRelatedException(1, Integer.MAX_VALUE, IllegalArgumentException.class);
        checkAreRelatedException(Integer.MIN_VALUE, Integer.MAX_VALUE, IllegalArgumentException.class);
        checkAreRelatedException(Integer.MIN_VALUE, Integer.MIN_VALUE, IllegalArgumentException.class);
        checkAreRelatedException(Integer.MAX_VALUE, Integer.MAX_VALUE, IllegalArgumentException.class);
    }
    
    /** Tests the add method. */
    @Test
    public void testAdd() {
        System.out.println("add");
        setInstance(2);
        instance.add(0, 1); // N.B. not a pair of equals
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 1, true);
        checkAreRelated(0, 0, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
        instance.add(0, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 1, true);
        checkAreRelated(0, 0, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }

    /** Tests the add method for robustness. */
    @Test
    public void robustTestAdd() {
        setInstance(2);
        checkAddException(3, 1, IllegalArgumentException.class);
        checkAddException(1, 3, IllegalArgumentException.class);
        checkAddException(3, 3, IllegalArgumentException.class);
        checkAddException(-1, 1, IllegalArgumentException.class);
        checkAddException(1, -1, IllegalArgumentException.class);
        checkAddException(Integer.MIN_VALUE, 1, IllegalArgumentException.class);
        checkAddException(1, Integer.MIN_VALUE, IllegalArgumentException.class);
        checkAddException(Integer.MAX_VALUE, 1, IllegalArgumentException.class);
        checkAddException(1, Integer.MAX_VALUE, IllegalArgumentException.class);
        checkAddException(Integer.MIN_VALUE, Integer.MAX_VALUE, IllegalArgumentException.class);
        checkAddException(Integer.MIN_VALUE, Integer.MIN_VALUE, IllegalArgumentException.class);
        checkAddException(Integer.MAX_VALUE, Integer.MAX_VALUE, IllegalArgumentException.class);
    }
    
    /** Tests the remove method. */
    @Test
    public void testRemove() {
        System.out.println("remove");
        setInstance(2);
        instance.remove(0, 1); // N.B. not a pair of equals
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, false);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
        instance.add(0, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 1, true);
        instance.remove(0, 1);
        assertTrue("isRepOk()", instance.isRepOk());
        checkAreRelated(0, 0, false);
        checkAreRelated(0, 1, false);
        checkAreRelated(1, 0, false);
        checkAreRelated(1, 1, false);
    }

    /** Tests the remove method for robustness.*/
    public void robustTestRemove() {
        setInstance(2);
        checkRemoveException(3, 1, IllegalArgumentException.class);
        checkRemoveException(1, 3, IllegalArgumentException.class);
        checkRemoveException(3, 3, IllegalArgumentException.class);
        checkRemoveException(1, -1, IllegalArgumentException.class);
        checkRemoveException(Integer.MIN_VALUE, 1, IllegalArgumentException.class);
        checkRemoveException(1, Integer.MIN_VALUE, IllegalArgumentException.class);
        checkRemoveException(Integer.MAX_VALUE, 1, IllegalArgumentException.class);
        checkRemoveException(1, Integer.MAX_VALUE, IllegalArgumentException.class);
        checkRemoveException(Integer.MIN_VALUE, Integer.MAX_VALUE, IllegalArgumentException.class);
        checkRemoveException(Integer.MIN_VALUE, Integer.MIN_VALUE, IllegalArgumentException.class);
        checkRemoveException(Integer.MAX_VALUE, Integer.MAX_VALUE, IllegalArgumentException.class);
    }

    /**
     * Invokes constructor(n) and checks for expected exception.
     *
     * @param n  extent of the new relation
     * @param expected   expected exception
     */
    private void checkConstructorException(int n, Class expected) {
        System.out.println("new intRelation(" + n + "), for exception");
        try {
            setInstance(n);
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
     * Invokes {@code areRelated(a, b)} and checks for expected exception.
     *
     * @param a  first element of the pair
     * @param b  second element of the pair
     * @param expected   expected exception
     */
    private void checkAreRelatedException(int a, int b, Class expected) {
        System.out.println("areRelated(" + a + ", " + b + "), for exception");
        try {
            instance.areRelated(a, b);
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
     * Invokes {@code add(a, b)} and checks for expected exception.
     *
     * @param a  first element of the pair
     * @param b  second element of the pair
     * @param expected   expected exception
     */
    private void checkAddException(int a, int b, Class expected) {
        System.out.println("add(" + a + ", " + b + "), for exception");
        try {
            instance.add(a, b);
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
     * Invokes {@code remove(a, b)} and checks for expected exception.
     *
     * @param a  first element of the pair
     * @param b  second element of the pair
     * @param expected   expected exception
     */
    private void checkRemoveException(int a, int b, Class expected) {
        System.out.println("remove(" + a + ", " + b + "), for exception");
        try {
            instance.remove(a, b);
            fail("should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
                            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }
}
