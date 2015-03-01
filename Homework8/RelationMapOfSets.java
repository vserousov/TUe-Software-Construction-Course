import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

// Class to illustrate Test-Driven Development of a generic Abstract Data Type.

/**
 * An implementation of {@link Relation} using a {@link Map} of {@link Set}s.
 *
 <!--//# BEGIN TODO Name, group id, and date-->
 Serousov Vitaly, 201 SE, 13.11.2014
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class RelationMapOfSets<A, B> implements Relation<A, B> {
//# BEGIN TODO Definition of class RelationMapOfSets
    /** Representation of the relation. */
    private Map<A, Set<B>> relation;

    /*
     * Representation invariants
     *
     * NotNull: relation != null
     * SetsNotNull: (\forall i; relation.has(i); relation.get(i) != null),
     *     where List.has(i) == 0 <= i < List.size()
     * SetsInExtent: (\forall i; relation.has(i);
     *     relation.get(i) subset of [0 .. relation.size()))
     *
     * Abstraction function: set of (a, b) such that
     *     relation.get(a).contains(b) holds
     */
    public RelationMapOfSets() {
        relation = new HashMap<A, Set<B>>();
    }

    /**
     * Checks whether the representation invariants hold.
     * For testing purposes only.
     *
     * @return whether the representation invariants hold
     * @throws IllegalStateException  if precondition violated
     * @pre representation invariants hold
     * @modifies None
     * @post {@code \result}
     */
    @Override
    public boolean isRepOk() throws IllegalStateException {
        // NotNull
        if (relation == null) {
            throw new IllegalStateException("relation == null");
        }
        for (A i : relation.keySet()) {
            final Set<B> set = relation.get(i);
            // SetsNotNull
            if (set == null) {
                throw new IllegalStateException(
                        "relation.get(" + i + ") == null");
            }
            // SetsInExtent
            for (B j : set) {
                if (j == null) {
                    throw new IllegalStateException(
                            "relation.get(" + i + ") contains " + j);
                }
            }
        }
        return true;
    }

    /**
     * Returns whether the elements in a pair are related.
     *
     * @param a  first element of the pair
     * @param b  second element of the pair
     * @return  whether {@code (a, b)} are related
     * @modifies None
     * @post {@code \result == (a, b) in this}
     */
    @Override
    public boolean areRelated(A a, B b) {
        if (relation.get(a) == null) {
            return false;
        } else {
            return relation.get(a).contains(b);
        }
    }

    /**
     * Adds a pair to the relation.
     *
     * @param a  first element of the pair to add
     * @param b  second element of the pair to add
     * @modifies {@code this}, but not {@code this.extent()}
     * @post {@code this == \old(this) union [ (a, b) ]}
     */
    @Override
    public void add(A a, B b) {
        if (relation.get(a) == null) {
            relation.put(a, new HashSet<B>());
        }
        relation.get(a).add(b);
    }

    /**
     * Removes a pair from the relation.
     *
     * @param a  first element of the pair to remove
     * @param b  second element of the pair to remove
     * @modifies {@code this}
     * @post {@code this == \old(this) minus [ (a, b) ]}
     */
    @Override
    public void remove(A a, B b) {
        if (areRelated(a, b)) {
            relation.get(a).remove(b);
        }
    }

    /**
     * Returns an iterable that can be used to iterate over
     * all elements that are related to a given first element.
     *
     * @param a  first element of the pair, to iterate over all related second element
     * @return iterable over all {@code B b} such that
     *     {@code this.areRelated(a, b)}
     * @modifies None
     */
    @Override
    public Iterable<B> relatedToFirst(final A a) {
        // Returns object of anonymous class
        return new Iterable<B>() {

            /**
             * Iterator method.
             *
             * @pre {@code true}
             * @post {@code \result == relation.get(a).iterator() || Collections.emptyIterator()}
             * @return  Iterator
             */
            public Iterator<B> iterator() {
                if (! relation.containsKey(a)) {
                    // returns empty iterator, if key does not exist
                    return java.util.Collections.emptyIterator();
                }
                // returns iterator for second element
                return relation.get(a).iterator();
            }
        };
    }

    /**
     * Iterator method.
     *
     * @return  Pair of 2 elements of A and B types respectively
     */
    @Override
    public Iterator<Pair<A, B>> iterator() {

        // Returns object of anonymous class
        return new Iterator<Pair<A, B>>() {

            /** First element of the pair. */
            private A a;

            /** Second element of the pair. */
            private B b;

            /** Key iterator. */
            private Iterator<A> keyIterator;

            /** Value iterator. */
            private Iterator<B> valueIterator;

            /**
             * Returns next pair of elements.
             *
             * @throws NoSuchElementException  if precondition violated
             * @pre {@code a != null && b != null && relation.conainsKey(a) && areRelated(a, b)}
             * @post {@code \result == Pair(a, b)}
             * @return  Next pair of elements
             */
            public Pair<A, B> next() throws NoSuchElementException {

                // No element exception
                if (a == null || b == null) {
                    throw new NoSuchElementException("No element");
                }

                // No such first element exception
                if (! relation.containsKey(a)) {
                    throw new NoSuchElementException("No such first element");
                }

                // No such second element exception
                if (! areRelated(a, b)) {
                    throw new NoSuchElementException("No such second element");
                }

                return new Pair<A, B>(a, b);
            }

            /**
             * Determines whether next pair of elements exist.
             *
             * @pre {@code true}
             * @post {@code \result == (\exists a, b; a != null && b != null; areRelated(a, b)}
             * @return  True, if next pair of elements exist, otherwise false
             */
            public boolean hasNext() {
                // If first element of pair is not initialized
                if (a == null) {

                    // Initialize iterator for keys
                    keyIterator = relation.keySet().iterator();
                }

                // Result of checking for existence of next element
                boolean result = true;

                // If valueIterator is initialized and it has next element
                if (valueIterator != null && valueIterator.hasNext()) {

                    // Get second element of pair
                    b = valueIterator.next();

                // If exists one more first element of pair
                } else if (keyIterator.hasNext()) {

                    // Get first element of pair
                    a = keyIterator.next();

                    // Reset iterator for values
                    valueIterator = relation.get(a).iterator();

                    // Get second element of pair
                    b = valueIterator.next();

                } else {
                    // If there are no keys, then result is false
                    result = false;
                }

                return result;
            }

            /**
             * Unsupported operation remove.
             *
             * @throws UnsupportedOperationException  if precondition violated
             * @pre {@code false}
             * @post {@code none}
             */
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException("Unsupported operation remove");
            }
        };
    }
//# END TODO
}
