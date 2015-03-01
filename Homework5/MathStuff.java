//# BEGIN SKELETON
/**
 * Library with static mathematical functions.
 *
<!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 02.10.2014
<!--//# END TODO-->
*/
// -----8<----- cut line -----8<-----
public abstract class MathStuff {

    /**
     * Returns exponentiation, taking care of overflow.
     *
     * @param a  the base
     * @param b  the exponent
     * @pre {@code 0 <= a && 0 <= b}
     * @return {@code a ^ b} if {@code a ^ b <= Integer.MAX_VALUE}
     *      else {@code Long.MAX_VALUE}
     * @throws IllegalArgumentException  if precondition violated
     */
    public static long power(int a, int b) throws IllegalArgumentException {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("power: negative argument");
        }
        // 0 <= a && 0 <= b
        long x = a; // see invariant
        int k = b; // see invariant
        long result = 1L; // see invariant

        // invariant: 0 <= k <= b && result * x^k == a^b
        while (k != 0) {
            if (k % 2 == 0) { // even exponent
                if (x <= Integer.MAX_VALUE) {
                    x *= x;
                } else {
                    x = Long.MAX_VALUE;
                }
                k /= 2;
            } else { // odd exponent
                if (result <= Integer.MAX_VALUE && x <= Integer.MAX_VALUE) {
                    result *= x;
                } else {
                    result = Long.MAX_VALUE;
                }
                k -= 1;
            }
            // invariant holds again, k has decreased
        }
        // k == 0, hence result == a^b

        if (result > Integer.MAX_VALUE) {
            return Long.MAX_VALUE;
        }
        return result;
    }

    /**
     * Record containing a base and an exponent.
     *
     * @inv {@code 0 <= base && 0 <= exponent}
     */
    public static class Power { // BEGIN RECORD TYPE

        /** The base. */
        public int base;

        /** The exponent. */
        public int exponent;

        /**
         * Constructs a Power with given base and exponent.
         *
         * @param base  the base
         * @param exponent  the exponent
         * @pre {@code 0 <= base && 0 <= exponent}
         * @post {@code \result.base == base && \result.exponent == exponent}
         */
        public Power(int base, int exponent) {
            this.base = base;
            this.exponent = exponent;
        }

    } // END RECORD TYPE

    /**
     * Returns exponentiation.
     *
     * @param p  the base and exponent
     * @pre {@code p != null}
     * @return {@code power(p.base, p.exponent)}
     * @throws IllegalArgumentException  if precondition violated
     */
    public static long power(Power p) throws IllegalArgumentException {
        return power(p.base, p.exponent);
    }

    /**
     * Writes a number as a power with maximal exponent.
     *
     * @param n  the number to 'powerize'
     * @return  power decomposition of {@code n} with maximal exponent
     * @throws IllegalArgumentException  if precondition violated
     * @pre {@code 2 <= n}
     * @post {@code n == power(\result) &&
     *     (\forall int b, int e;
     *      2 <= b && 1 <= e && n == b ^ e;
     *      e <= \result.exponent)}
     */
    public static Power powerize(int n) throws IllegalArgumentException {
//# BEGIN TODO Implementation of powerize
        // throw exception if n less than 2
        if (n < 2) {
            throw new IllegalArgumentException("n can't be less than 2");
        }

        int max = 31; // maximum value of exponent
        int min = 2; // minimum value of exponent

        // loop from maximum to minimum
        for (int e = max; e >= min; e--) {
            try {
                int base = binarySearchBase(e, n);
                if (base >= min) {
                    return new Power(base, e);
                }
            } catch (IllegalArgumentException ex) {
                continue;
            }
        }

        //if nothing found
        return new Power(n, 1);
//# END TODO
    }

//# BEGIN TODO Contracts and implementations of auxiliary functions.
    /**
     * Search base of Power.
     * Find between 2 and {@code Math.Sqrt(n) + 1} for optimization.
     *
     * @param e  Exponent
     * @param n  Number
     * @return  Base
     * @throws IllegalArgumentException  if precondition violated
     *    or if there is no base
     * @pre {@code 2 <= e <= 31 && n >= 2}
     * @post {@code power(\result, e) == n || \result == 0}
     */
    public static int binarySearchBase(int e, int n) throws IllegalArgumentException {
        // Illegal e
        if (e < 2 || e > 31) {
            throw new IllegalArgumentException("Illegal e");
        }

        // Illegal n
        if (n < 2) {
            throw new IllegalArgumentException("Illegal n");
        }

        // Base
        int result = 0;

        // Middle number
        int mid;
        // Power
        long powered;
        // Start with minimum
        int min = 2;
        // End with maximum
        int max = (int)Math.sqrt(n) + 1;

        // Binary search
        while (max >= min) {
            mid = min + (max - min) / 2;
            powered = power(mid, e);
            if (powered == n) {
                result = mid;
                break;
            } else if (powered < n) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        // if base was not found
        if (result == 0) {
            throw new IllegalArgumentException("Base was not found");
        }

        return result;
    }
//# END TODO

}
//# END SKELETON
