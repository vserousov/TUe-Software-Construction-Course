import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Abstract test cases for IntRelation, to be extended to obtain
 * concrete test cases for an extension of IntRelationArraysIterable
 * and IntRelationListOfSetsIterable.
 *
 * Serousov Vitaly, 201 SE, 09.12.2014
 */
// -----8<----- cut line -----8<-----
public abstract class IntRelationIterableTestCases
        extends IntRelationTestCases {

    /** Additional test fixture: {@code instance} viewed as iterable. */
    protected Iterable<IntPair> iterable;

    /**
     * Sets {@code instance} and {@code iterable} to a newly constructed
     * iterable relation of given extent.
     * In test cases for iterators,
     * only {@code setIterable} must be called,
     * and not {@code setInstance}.
     * Afterwards, the constructed object can be used
     * through {@code instance} as an {@code IntRelation}, and
     * through {@code iterable} as an {@code Iterable<IntPair>}.
     *
     * @param n   extent
     * @post {@code instance} and {@code iterable} have been assigned
     *     the same object of an appropriate subtype of
     *     {@code IntRelation implements Iterable<IntPair>},
     *     with extent {@code n}
     */
    protected void setIterable(final int n) {
        setInstance(n);
        iterable = ((Iterable<IntPair>) instance);
    }

    /** Test empty iterator. */
    @Test
    public void testEmptyIterator() {
        System.out.println("Test empty iterator");
        setIterable(0);
        IntPair[] pairs = new IntPair[0];
        checkIterator(pairs);
    }

    /** Test singleton iterator. */
    @Test
    public void testSingletonIterator() {
        System.out.println("Test singleton iterator");
        setIterable(1);
        instance.add(0, 0);
        IntPair[] pairs = new IntPair[] { new IntPair(0, 0) };
        checkIterator(pairs);
    }

    /** Test row iterator. */
    @Test
    public void testRowIterator() {
        System.out.println("Test row iterator");
        setIterable(5);
        instance.add(1, 4);
        instance.add(3, 2);
        instance.add(4, 4);
        IntPair[] pairs = new IntPair[] { new IntPair(1, 4),
            new IntPair(3, 2), new IntPair(4, 4) };
        checkIterator(pairs);
    }

    /** Test iterator hasNext method returning true for good weather. */
    @Test
    public void testHasNextTrue() {
        System.out.println("Test hasNext() method (true)");
        setIterable(2);
        instance.add(1, 0);
        instance.add(1, 1);
        Iterator iterator = iterable.iterator();
        assertTrue("iterator.hasNext()", iterator.hasNext());
    }

    /** Another test iterator hasNext method returning true for good weather. */
    @Test
    public void testHasNextTrue2() {
        System.out.println("Test hasNext() method after next (true)");
        setIterable(2);
        instance.add(1, 0);
        instance.add(1, 1);
        Iterator iterator = iterable.iterator();
        iterator.next();
        assertTrue("iterator.hasNext()", iterator.hasNext());
    }

    /** Test iterator hasNext method returning false for good weather. */
    @Test
    public void testHasNextFalse() {
        System.out.println("Test hasNext() method (false)");
        setIterable(2);
        Iterator iterator = iterable.iterator();
        assertFalse("iterator.hasNext()", iterator.hasNext());
    }

    /** Another test iterator hasNext method returning false for good weather. */
    @Test
    public void testHasNextFalse2() {
        System.out.println("Test hasNext() method after next() (false)");
        setIterable(2);
        instance.add(0, 1);
        instance.add(1, 1);
        Iterator iterator = iterable.iterator();
        iterator.next();
        iterator.next();
        assertFalse("iterator.hasNext()", iterator.hasNext());
    }

    /** Test iterator next method for good weather. */
    @Test
    public void testNext() {
        System.out.println("Test next() method");
        setIterable(2);
        instance.add(1, 0);
        instance.add(1, 1);
        Iterator iterator = iterable.iterator();
        iterator.next();
    }

    /** Test iterator double next method for good weather. */
    @Test
    public void testDoubleNext() {
        System.out.println("Test next() method (2 calls)");
        setIterable(2);
        instance.add(1, 0);
        instance.add(1, 1);
        Iterator iterator = iterable.iterator();
        iterator.next();
        iterator.next();
    }

    /** Test iterator next after calling hasNext method. */
    @Test
    public void testNextAfterHasNext() {
        System.out.println("Test next() method after hasNext()");
        setIterable(2);
        instance.add(1, 0);
        instance.add(1, 1);
        Iterator iterator = iterable.iterator();
        iterator.hasNext();
        iterator.hasNext();
        iterator.hasNext();
        iterator.next();
        iterator.next();
    }

    /** Another test iterator next after calling hasNext method. */
    @Test
    public void testNextAfterHasNext2() {
        System.out.println("Test next() method after many hasNext() calls");
        setIterable(10);
        instance.add(9, 9);
        instance.add(8, 9);
        Iterator iterator = iterable.iterator();

        for (int i = 0; i < 100; ++ i) {
            iterator.hasNext();
        }

        iterator.next();
        iterator.next();
    }

    /** Test iterator next method for bad weather. */
    @Test(expected = NoSuchElementException.class)
    public void testNextExceptionNull() {
        System.out.println("Test next() method for robustness (empty relation)");
        setIterable(0);
        Iterator iterator = iterable.iterator();
        iterator.next();
    }

    /** Another test iterator next method for bad weather. */
    @Test(expected = NoSuchElementException.class)
    public void testNextExceptionSingle() {
        System.out.println("Test next() method for robustness (after next() and hasNext() calls)");
        setIterable(1);
        instance.add(0, 0);
        Iterator iterator = iterable.iterator();
        iterator.hasNext();
        iterator.next();
        iterator.hasNext();
        iterator.next();
    }

    /** Test iterator only next method for bad weather. */
    @Test(expected = NoSuchElementException.class)
    public void testNextOnlyException() {
        System.out.println("Test next() method for robustness (after next() calls)");
        setIterable(1);
        instance.add(0, 0);
        Iterator iterator = iterable.iterator();
        iterator.next();
        iterator.next();
    }

    /** Test iterator remove method for bad weather. */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveException() {
        System.out.println("Test remove() method for robustness");
        setIterable(2);
        instance.add(1, 0);
        instance.add(1, 1);
        Iterator iterator = iterable.iterator();
        iterator.remove();
    }

    /**
     * Check iterator method.
     *
     * @param pairs  Array of pairs
     */
    private void checkIterator(IntPair[] pairs) {
        boolean passed = true;
        int i = 0;
        for (IntPair pair : iterable) {
            if (pair.a != pairs[i].a || pair.b != pairs[i].b) {
                passed = false;
                break;
            }
            i++;
        }
        assertTrue("checkIterator(IntPair[] pairs)", passed);
    }
}
