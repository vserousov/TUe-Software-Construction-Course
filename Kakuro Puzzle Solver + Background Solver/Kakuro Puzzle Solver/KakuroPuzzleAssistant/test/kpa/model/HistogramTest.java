package kpa.model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Tests for class Histogram
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class HistogramTest {

    /** Subject Under Test */
    private Histogram instance;

    @Before
    public void setUp() {
        instance = new Histogram();
    }

    /**
     * Checks SUT state to be equal to given array.
     */
    private void checkSUT(final int[] expected) {
        int result;
        for (int state = KCell.BLOCKED; state <= 1; ++ state) {
            result = instance.get(state);
            assertEquals("Count for state " + state,
                    expected[state - KCell.BLOCKED], result);
        }
    }

    /**
     * Tests constructor, of class Histogram.
     */
    @Test
    public void testConstructor() {
        System.out.println("Histogram()");
        checkSUT(new int[3]);
    }

    /**
     * Tests adjust method, of class Histogram.
     */
    @Test
    public void testAdjust() {
        System.out.println("adjust");
        int[] expected = new int[4];
        int delta = 1;
        for (int state = KCell.BLOCKED; state <= 1; ++ state) {
            instance.adjust(state, delta);
            expected[state - KCell.BLOCKED] += delta;
            ++ delta;
            checkSUT(expected);
        }
    }

}
