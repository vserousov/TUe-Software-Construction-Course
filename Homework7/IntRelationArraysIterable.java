import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of {@link IntRelationArrays} using {@Link Iterable}.
 * For relations with a small extent this may work faster.
 * However, relations with a large extent use more memory,
 * even when they are sparse (have few related pairs).
 *
 * Serousov Vitaly, 201 SE, 09.12.2014
 */
// -----8<----- cut line -----8<-----
public class IntRelationArraysIterable extends IntRelationArrays
        implements Iterable<IntPair> {

    /**
     * Constructs Iterable IntRelationArrays.
     *
     * @param n  The extent of relation
     */
    public IntRelationArraysIterable(final int n) {
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

        /** Current position for iterator. */
        private int position;

        /** Limit of iteration per one dimension. */
        private final int sentinel;

        /** Total number of pairs. */
        private int number;

        /** Number of pairs returned by iterator. */
        private int returned;

        /**
         * Constructs iterator in initial state.
         *
         * @pre {@code true}
         * @post {@code position == 0 && sentinel == extent() && returned == 0
         *               && number == (\num of i; i < extent() * extent();
         *               areRelated(i / extent(), i % extent()); )}
         */
        public IntRelationIterator() {
            // Position starts with 0.
            position = 0;
            // Number of elements per 1 dimension get from extent method.
            sentinel = extent();
            // Number of returned elements starts with 0.
            returned = 0;
            // Number of total elements starts with 0.
            number = 0;

            // Count the number of pairs in relation.
            for (int i = 0; i < sentinel * sentinel; i++) {
                if (areRelated(i / sentinel, i % sentinel)) {
                    // Increase number if areRelated.
                    ++ number;
                }
            }
        }

        /**
         * Check if it has next element.
         *
         * @pre {@code true}
         * @post {@code \result == (position < sentinel * sentinel && returned < number)}
         * @return  True, if next element exists, otherwise false
         */
        public boolean hasNext() {
            return position < sentinel * sentinel && returned < number;
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
            // Precondition.
            if (! hasNext()) {
                throw new NoSuchElementException("IntRelationArraysIterator.next");
            }

            // Get first element value of pair.
            int i = position / sentinel;

            // Get second element value of pair.
            int j = position % sentinel;

            // Move to the next position while not areRelated.
            while (!areRelated(i, j)) {
                // Increase position.
                ++ position;

                // Update first element value of pair.
                i = position / sentinel;

                // Update second element value of pair.
                j = position % sentinel;
            }

            // Move to the next position for future next.
            ++ position;

            // Increase number of returned pairs.
            ++ returned;

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
