import java.util.*;

/**
 * An implementation of {@link IntRelationListOfSets} using {@link Iterable}.
 * For sparse relations with a large extent, this reduces memory usage.
 * However, there is a bit of performance overhead.
 *
 * Serousov Vitaly, 201 SE, 09.12.2014
 */
// -----8<----- cut line -----8<-----
public class IntRelationListOfSetsIterable extends IntRelationListOfSets
        implements Iterable<IntPair> {

    /**
     * Constructs Iterable IntRelationArrays.
     *
     * @param n  The extent of relation
     */
    public IntRelationListOfSetsIterable(final int n) {
        super(n);
    }

    /**
     * Returns IntPair Iterator.
     *
     * @return  IntPair Iterator
     */
    public Iterator<IntPair> iterator() {
        return new IntRelationIterator();
    }

    /**
     * Inner class for a low-to-high iterator.
     */
    private class IntRelationIterator implements Iterator<IntPair> {

        /** Iterator for Set to detect if next element exists using hasNext. */
        private Iterator<Integer> set;

        /** First element of pair. */
        private int i;

        /** Second element of pair. */
        private int j;

        /**
         * Constructs iterator in initial state.
         *
         * @pre {@code true}
         * @post {@code i == 0 && set == relation.get(i).iterator() || null}
         */
        public IntRelationIterator() {
            // Initialize first element of pair to 0.
            i = 0;
            // If relation is not empty
            if (i < extent()) {
                set = relation.get(i).iterator();
            // Otherwise set it null.
            } else {
                set = null;
            }
        }

        /**
         * Check if it has next element.
         *
         * @pre {@code true}
         * @post {@code \result == (\exists i, j; 0 <= i < sentinel
         *   && 0 <= j < sentinel; areRelated(i, j)}
         * @return  True, if next element exists, otherwise false
         */
        public boolean hasNext() {
            // If relation is empty.
            if (set == null) {
                return false;
            }

            // If relation has next second element of pair.
            if (set.hasNext()) {
                return true;
            }

            // Check if next element exists using size().
            for (int a = i + 1; a < extent(); a++) {
                // If size is more than 0 it means that element exists
                if (relation.get(a).size() > 0) {
                    return true;
                }
            }

            // In other case return false.
            return false;
        }

        /**
         * Returns next element of pair.
         *
         * @throws NoSuchElementException  if precondition violated
         * @pre {@code hasNext()}
         * @post {@code \result == IntPair(i, j)}
         * @return  {@code new IntPair(i, j)}
         */
        public IntPair next() throws NoSuchElementException {
            // If next pair doesn't exist.
            if (! hasNext()) {
                throw new NoSuchElementException("IntRelationArraysIterator.next");
            }

            // Loop through different sets.
            while (! set.hasNext()) {
                ++ i;
                set = relation.get(i).iterator();
            }

            // Get second element of pair from set.
            j = set.next();

            // Return pair.
            return new IntPair(i, j);
        }

        /**
         * For that case remove is unsupported operation.
         *
         * @throws UnsupportedOperationException  if precondition violated
         * @pre {@code false}
         * @post {@code none}
         */
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Unsupported operation remove");
        }
    }
}