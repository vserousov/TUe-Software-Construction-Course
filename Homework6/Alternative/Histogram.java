import java.util.Arrays;

/**
 * Class to maintain a mutable histogram of frequency counts
 * for values in the interval {@code 0} through {@code n}.
 * <p>
 * Public invariants:
 * <ul>
 * <li>{@code (\forall Histogram h, int v; h.has(v); 0 <= h.getCount(v))}
 * </ul>
 *
 <!--//# BEGIN TODO Name, group id, and date-->
 Serousov Vitaly, 201 SE, 15.10.2014
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class Histogram {

    /** The frequency counts */
    private final long[] frequencies;

    /** Total count */
    private long totalCount;
    // Representation invariant:
    //   frequencies != null

    // ===== ===== Constructors ===== =====

    /**
     * Contructs an empty histogram for values
     * in the interval {@code 0} through {@code n}.
     *
     * @param n  upper bound on the values
     * @throws IllegalArgumentException if precondition violated
     * @pre {@code 0 <= n}
     * @post new histogram constructed with
     *   {@code (\forall v; has(v); getCount(v) == 0)}
     */
    public Histogram(int n) throws IllegalArgumentException {
//# BEGIN TODO constructor implementation
        if (! (0 <= n)) {
            throw new IllegalArgumentException("Precondition violated: " +
                    "n = " + n);
        }
        this.frequencies = new long[n + 1];
        this.totalCount = 0;
//# END TODO
    }

    // ===== ===== Queries ===== =====

    /**
     * Gets the upper bound on the values.
     *
     * @pre {@code true}
     * @post {@code \result == upper bound}
     */
    public int getUpperBound() {
        return frequencies.length - 1;
    }

    /**
     * Determines whether a given value is valid.
     *
     * @param v  value
     * @pre {@code true}
     * @post {@code \result == (0 <= v <= getUpperBound())}
     */
    public boolean has(int v) {
        return 0 <= v && v <= getUpperBound();
    }

    /**
     * Gets frequency for given value.
     *
     * @param v  value to get frequency for
     * @throws IllegalArgumentException if precondition violated
     * @pre {@code has(v)}
     * @post {@code \result == frequency for value v}
     */
    public long getCount(int v) throws IllegalArgumentException {
//# BEGIN TODO query implementation
        if (! has(v)) {
            throw new IllegalArgumentException("Precondition violated: " +
                    "v = " + v);
        }
        return frequencies[v];
//# END TODO
    }

    /**
     * Gets all the frequencies in an array.
     *
     * @pre {@code true}
     * @post {@code \result.length = 1 + getUpperBound() &&}<br>
     *   {@code (\forall v; has(v); \result[v] == getCount(v)}
     */
    public long[] getAllCounts() {
        return Arrays.copyOf(frequencies, frequencies.length);
    }

    /**
     * Gets total count.
     *
     * @pre {@code true}
     * @post {@code \result == (\sum v; has(v); getCount(v))}
     */
    public long getTotalCount() {
//# BEGIN TODO query implementation
        return totalCount;
//# END TODO
    }

    // ===== ===== Commands ===== =====

    /**
     * Updates the histogram by one for a given value.
     *
     * @param v  value to update frequency for
     * @throws IllegalArgumentException if precondition violated
     * @pre {@code has(v)}
     * @modifies {@code this}
     * @post {@code (\forall w; has(w) && w != v;
     *   getCount(w) == \old(getCount(w))) &&}<br>
     *   {@code getCount(v) == \old(getCount(v) + 1)}
     */
    public void update(int v) throws IllegalArgumentException {
//# BEGIN TODO command implementation
        if (! has(v)) {
            throw new IllegalArgumentException("Precondition violated: " +
                    "v = " + v);
        }
        ++ frequencies[v];
        ++ totalCount;
//# END TODO
    }

}
