//# BEGIN SKELETON
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Observable generator of Kakuro combinations.
 *
 * @author Tom Verhoeff (TU/e)
<!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 20.11.2014
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class KakuroCombinationGenerator {

    /**
     * Largest number in a Kakuro combination, with {@code 0 <= maxNumber}.
     */
    private int maxNumber;

    /** The registered listeners. */
    private final List<GeneratorListener> listeners;

    /**
     * Constructs a generator without listeners.
     */
    public KakuroCombinationGenerator() {
        this.listeners = new ArrayList<GeneratorListener>();
        this.maxNumber = 9; // default
    }

    /**
     * Sets largest number.
     *
     * @param maxNumber
     * @pre {@code 0 <= maxNumber}
     */
    public void setMaxNumber(final int maxNumber) {
        if (maxNumber < 0) {
            throw new IllegalArgumentException(
                    "setMaxNumber precondition violated: maxNumber = "
                    + maxNumber);
        }
        this.maxNumber = maxNumber;
    }

    /**
     * Gets largest number.
     */
    public int getMaxNumber() {
        return this.maxNumber;
    }

    /**
     * Adds a listener for generator events.
     */
    public void addListener(GeneratorListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies all registered listeners.
     */
    protected void notifyListeners(Set<Integer> combination) {
        for (GeneratorListener listener : listeners) {
            listener.combinationGenerated(combination);
        }
    }

    /**
     * Generates all combinations (modulo order of digits) of
     * n distinct non-zero digits with sum s,
     * in lexicographic order (NOT ROBUST).
     *
     * @param n  number of digits for extension
     * @param s  digit sum for extension
     * @pre   {@code 0 <= n}
     * @post  each (sorted) combination of
     *        {@code n} distinct non-zero digits with sum {@code s}
     *        has been reported to all registered listeners exactly once,
     *        in lexicographic order
     */
    public void generate(int s, int n) {
        generate(new HashSet<Integer>(), s, n, 1);
    }

    /**
     * Generates all ways (modulo order of digits) in which a given combination
     * can be extended by n distinct non-zero digits at least k and with sum s,
     * in lexicographic order (NOT ROBUST).
     *
     * @param c  given combination to be extended
     * @param n  number of digits for extension
     * @param s  digit sum for extension
     * @param k  lower bound for digits in extension
     * @pre   {@code 0 <= n && && 1 <= k &&
     *        (\forall i; i : c; i < k)}
     * @modifies None
     * @post  each (sorted) way of extending {@code c} with
     *        {@code n} distinct non-zero digits at least {@code k} and
     *        less than {@code RADIX}, with sum {@code s},
     *        has been reported to all registered listeners exactly once,
     *        in lexicographic order
     * @bound {@code n} (upper bound on number of recursive calls)
     */
    private void generate(Set<Integer> c, int s, int n, int k) {
//# BEGIN TODO Recursive implementation of generate(Set<Integer>, int, int, int)
        if (n == 0 && s == 0) {
            notifyListeners(c);
        } else if (n == 1 && s >= k && s <= maxNumber) {
            c.add(s);
            notifyListeners(c);
            c.remove(s);
        } else {
            for (int i = k; i <= maxNumber; i++) {
                c.add(i);
                generate(c, s - i, n - 1, i + 1);
                c.remove(i);
            }
        }
//# END TODO
    }

}
//# END SKELETON
