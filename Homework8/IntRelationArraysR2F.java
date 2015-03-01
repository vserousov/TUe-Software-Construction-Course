import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Version of {@link IntRelationArrays}, that allows iteration
 * over all related integers to a given first integer.
 *
<!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 11.11.2014
<!--//# END TODO-->
*/
// -----8<----- cut line -----8<-----
public class IntRelationArraysR2F extends IntRelationArrays
        implements IntRelationExtra {

    public IntRelationArraysR2F(final int n) {
        super(n);
    }

    @Override
    public Iterable<Integer> relatedToFirst(final int a) {
//# BEGIN TODO Robust implementation of relatedToFirst
        // Precondition
        if (!isValid(a)) {
            throw new IllegalArgumentException("Precondition violated");
        }

        // Return new object of anonymous iterable class
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new RelationIterator(a);
            }
        };
//# END TODO
    }

    private class RelationIterator implements Iterator<Integer> {
//# BEGIN TODO Definition of inner class RelationIterator
        /** First element of pair. */
        private final int a;

        /** Current step. */
        private int step;

        /**
         * Relation iterator constructor.
         * @param a  First element of pair
         */
        public RelationIterator(final int a) {
            this.a = a;
            this.step = 0;
        }

        /**
         * Check whether the next element exist.
         * @return  True, if next element exist, otherwise false
         */
        public boolean hasNext() {
            while (isValidPair(a, step)) {
                if (areRelated(a, step)) {
                    return true;
                } else {
                    step++;
                }
            }
            return false;
        }

        /**
         * Returns next element.
         * @return  Next element.
         */
        public Integer next() throws NoSuchElementException {
            if (!isValidPair(a, step)) {
                throw new NoSuchElementException("No such element exception");
            }

            return step++;
        }
//# END TODO
    }

}
