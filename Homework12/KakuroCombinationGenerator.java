import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Observable generator of Kakuro combinations.
 *
 * @author Tom Verhoeff (TU/e)
<!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 11.12.2014
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class KakuroCombinationGenerator implements Generator<Set<Integer>> {

    /**
     * Largest number in a Kakuro combination, with {@code 0 <= maxNumber}.
     */
    protected int maxNumber;

    /** The registered observer. */
    protected GeneratorObserver<Set<Integer>> observer;

    /** The most recently generated object. */
    protected Set<Integer> object;

    /**
     * Constructs a generator without observer.
     */
    public KakuroCombinationGenerator() {
        this.observer = null;
        this.object = null;
        this.maxNumber = 9; // default
    }

    @Override
    public void setObserver(GeneratorObserver<Set<Integer>> observer) {
        this.observer = observer;
    }

    @Override
    public Set<Integer> getObject() {
        return new HashSet<Integer>(object); // copy to avoid interference
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

    /** Counts number of {@code generate(Set<Integer>, int, int, int)} calls. */
    public long count = 0;

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
     *        has been reported to the registered observer exactly once,
     *        in lexicographic order
     */
    public void generate(int s, int n) {
        count = 0;
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
     *        has been reported to the registered observer exactly once,
     *        in lexicographic order
     * @bound {@code n} (upper bound on number of recursive calls)
     */
    private void generate(Set<Integer> c, int s, int n, int k) {
        ++ count;
//# BEGIN TODO Recursive implementation of generate(Set<Integer>, int, int, int)
        /*
        // maximum sum
        int maximum = (2 * maxNumber - n + 1) * n / 2;

        // minimum sum
        int minimum = (2 * k + n - 1) * n / 2;

        if (maximum < s || minimum > s) {
            return;
        }
        */

        if (n == 0 && s == 0) {
            object = c;
            observer.objectGenerated(this);
        } else if (n == 1) {
            if (s >= k && s <= maxNumber) {
                c.add(s);
                object = c;
                observer.objectGenerated(this);
                c.remove(s);
            }
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
