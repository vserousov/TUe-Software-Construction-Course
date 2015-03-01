//# BEGIN SKELETON
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for Kakuro combination generator.
 *
 * @author Tom Verhoeff (TU/e)
<!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 20.11.2014
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class KakuroCombinationGeneratorTest {

    /** The subject under test. */
    private KakuroCombinationGenerator instance;

    /** The listener. */
    private Checker checker;

    @Before
    public void setUp() {
        System.out.println("\nsetUp()");
        instance = new KakuroCombinationGenerator();
        checker = new Checker();
        instance.addListener(checker);
    }

    // Tests of generate method, of class KakuroCombinationGenerator.

    /**
     * Calls {@code KakuroCombinationGenerator.generate(s, n)},
     * and checks the result, expecting {@code expCount} combinations.
     */
    public void checkGenerator(int s, int n, int expCount) {
        System.out.println("generate(" + s + ", " + n + ")");
        System.out.println("  should generate " + expCount + " combination"
            + ((expCount == 1) ? "" : "s"));
        checker.set(s, n);
        instance.generate(s, n);
        assertEquals("Number of combinations", expCount, checker.count);
    }

    /** Boundary case: minimal s that still works. */
    @Test
    public void testGeneratorMinimal0() {
        checkGenerator(0, 0, 1);
    }

    /** Boundary case: minimal s that just does not work. */
    @Test
    public void testGeneratorMinimal1() {
        checkGenerator(0, 1, 0);
    }

//# BEGIN TODO Further test cases
    /** Test case s = 17, n = 5. */
    @Test
    public void testGenerator1() {
        checkGenerator(17, 5, 2);
    }

    /** Test case s = 10, n = 3. */
    @Test
    public void testGenerator2() {
        checkGenerator(10, 3, 4);
    }

    /** Test case s = 9, n = 3. */
    @Test
    public void testGenerator3() {
        checkGenerator(9, 3, 3);
    }

    /** Test case s = 15, n = 4. */
    @Test
    public void testGenerator4() {
        checkGenerator(15, 4, 6);
    }

    /** Test case s = 18, n = 4. */
    @Test
    public void testGenerator5() {
        checkGenerator(18, 4, 11);
    }

    /** Test case s = 10, n = 9. */
    @Test
    public void testGenerator6() {
        checkGenerator(10, 9, 0);
    }

    /** Test case s = 10, n = 8. */
    @Test
    public void testGenerator7() {
        checkGenerator(10, 8, 0);
    }

    /** Test case s = 10, n = 7. */
    @Test
    public void testGenerator8() {
        checkGenerator(10, 7, 0);
    }

    /** Test case s = 10, n = 6. */
    @Test
    public void testGenerator9() {
        checkGenerator(10, 6, 0);
    }

    /** Test case s = 10, n = 5. */
    @Test
    public void testGenerator10() {
        checkGenerator(10, 5, 0);
    }
//# END TODO

    // Auxiliary definitions

    /**
     * Listener that checks that each generated combination
     * has indeed the expected sum and length,
     * and that they appear in lexicographic order.
     */
    private class Checker implements GeneratorListener {

        /** Number of reported combinations. */
        public int count;

        /** Expected sum. */
        private int s;

        /** Expected number. */
        private int n;

        /** Preceding generated combination. */
        private Set<Integer> preceding;

        /** Creates a default initialized checker. */
        public Checker() {
            this.count = 0;
            this.preceding = null;
        }

        /**
         * Sets the expected sum and length.
         *
         * @param s  expected sum
         * @param n  expected length
         */
        public void set(int s, int n) {
            this.s = s;
            this.n = n;
        }

        @Override
        public void combinationGenerated(Set<Integer> combination) {
            ++ this.count;
            System.out.println(combination);
            assertTrue("Lexicographic", precedes(preceding, combination));
            assertTrue("Range 1 .. " + instance.getMaxNumber(),
                    1 <= min(combination)
                    && max(combination) <= instance.getMaxNumber());
            assertEquals("Sum", s, sum(combination));
            assertEquals("Number", n, combination.size());
            this.preceding = new HashSet<Integer>(combination); // NEEDS COPY!
        }
    }

    /**
     * Returns sum of given set of integers.
     *
     * @param c  set of integers, not null
     * @return  sum of integers in {@code c}
     */
    private int sum(Set<Integer> c) {
        int result = 0;
        for (int i : c) {
            result += i;
        }
        return result;
    }

    /**
     * Returns minimum of given set of integers.
     *
     * @param c  set of integers, not null
     * @return  minimum of integers in {@code c}
     */
    private int min(Set<Integer> c) {
        int result = Integer.MAX_VALUE;
        for (int i : c) {
            if (result < i) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Returns maximum of given set of integers.
     *
     * @param c  set of integers, not null
     * @return  maximum of integers in {@code c}
     */
    private int max(Set<Integer> c) {
        int result = Integer.MIN_VALUE;
        for (int i : c) {
            if (i > result) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Determines whether one integer set lexicographically precedes another.
     * Null precedes every non-null set.
     *
     * @param c  first set of integers
     * @param d  second set of integers
     * @return  whether {@code c} strictly precedes {@code d}
     */
    private boolean precedes(Set<Integer> c, Set<Integer> d) {
        if (c == null | d == null) {
            return c == null && d != null;
        }
        // c != null && d != null
        for (int i = 1; i < instance.getMaxNumber(); ++ i) {
            if (c.contains(i) != d.contains(i)) {
                return c.contains(i);
            }
        }
        return false;
    }

}
//# END SKELETON
