//# BEGIN SKELETON
/**
 * Illustrates Test-Driven Development of a single method.
 *
<!--//# BEGIN TODO Name, student id, and date-->
Serousov Vitaly, 201 SE, 02.10.2014
<!--//# END TODO -->
 */
// -----8<----- cut line -----8<-----
public class TDDForCountDigitsMethod {

    /**
     * Counts the digits of a number.
     * This concerns a non-negative integer, assumed to be
     * written in positional notation without leading zeroes.
     * Robust version.
     *
<!--//# BEGIN TODO Contract-->
     * @param n  number
     * @param r  positional notation
     * @return  number of digits
     * @throws  IllegalArgumentException  if {@code n < 0} or {@code r < 2}
     * @pre {@code n >= 0 && r >= 2}
     * @post {@code result >= 1}
<!--//# END TODO-->
     */
    public static int countDigits(long n, long r)
            throws IllegalArgumentException {
//# BEGIN TODO Implementation
        if (n < 0) {
            throw new IllegalArgumentException("Illegal n");
        }

        if (r < 2) {
            throw new IllegalArgumentException("Illegal r");
        }

        int result = 1;

        while (n >= r) {
            n /= r;
            result++;
        }

        return result;
//# END TODO
    }

}
//# END SKELETON
