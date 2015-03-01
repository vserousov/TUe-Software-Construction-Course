import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Class to illustrate Test-Driven Development of an Abstract Data Type.

/**
 * An implementation of {@link IntRelation} using a {@link List} of {@link Set}.
 * For sparse relations with a large extent, this reduces memory usage.
 * However, there is a bit of performance overhead.
 *
 * Serousov Vitaly, 201 SE, 22.10.2014
 */
// -----8<----- cut line -----8<-----
public class IntRelationListOfSets extends IntRelation {

    /** Representation of the relation. */
    protected List<Set<Integer>> relation;
    // could be declared final

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
    public IntRelationListOfSets(final int n) throws IllegalArgumentException {
        super(n);
        relation = new ArrayList<Set<Integer>>();
        /* Java 7, but this upsets Checkstyle
        relation = new ArrayList<>();
        */
        for (int i = 0; i != n; ++ i) {
            relation.add(new HashSet<Integer>());
        }
    }

    /* Why is it harder to get the following intialization to work?
     * It is probably better to exclude this through a rep invariant.
           relation = new ArrayList<Set<Integer>>();
           final Set empty = new HashSet<Integer>();
           for (int i = 0; i != n; ++ i) {
               relation.add(empty);
           }
     */
    @Override
    public boolean isRepOk() throws IllegalStateException {
        // NotNull
        if (relation == null) {
            throw new IllegalStateException("relation == null");
        }
        for (int i = 0; i != extent(); ++ i) {
            final Set<Integer> set = relation.get(i);
            // SetsNotNull
            if (set == null) {
                throw new IllegalStateException(
                        "relation.get(" + i + ") == null");
            }
            // SetsInExtent
            for (int j : set) {
                if (! (0 <= j && j < extent())) {
                    throw new IllegalStateException(
                            "relation.get(" + i + ") contains " + j);
                }
            }
        }
        return true;
    }

    /**
     * Returns the extent of this relation.
     *
     * @return extent of this relation
     * @pre {@code true}
     * @modifies None
     * @post (basic query)
     */
    @Override
    public int extent() {
        return relation.size();
    }

    /**
     * Returns whether the elements in a pair are related.
     *
     * @param a  first element of the pair
     * @param b  second element of the pair
     * @return  whether {@code (a, b)} are related
     * @throws IllegalArgumentException  if precondition violated
     * @pre {@code isValidPair(a, b)}
     * @modifies None
     * @post {@code \result == (a, b) in this}
     */
    @Override
    public boolean areRelated(int a, int b) throws IllegalArgumentException {
        if (!isValidPair(a, b)) {
            throw new IllegalArgumentException("Precondition violated " +
                    "areRelated(" + a + ", " + b + ")");
        }
        return relation.get(a).contains(b);
    }

    /**
     * Adds a pair to the relation.
     *
     * @param a  first element of the pair to add
     * @param b  second element of the pair to add
     * @pre {@code isValidPair(a, b)}
     * @modifies {@code this}, but not {@code this.extent()}
     * @post {@code this == \old(this) union [ (a, b) ]}
     */
    @Override
    public void add(int a, int b) throws IllegalArgumentException {
        if (!isValidPair(a, b)) {
            throw new IllegalArgumentException("Precondition violated " +
                    "add(" + a + ", " + b + ")");
        }
        relation.get(a).add(b);
    }

    /**
     * Removes a pair from the relation.
     *
     * @param a  first element of the pair to remove
     * @param b  second element of the pair to remove
     * @throws IllegalArgumentException  if precondition violated
     * @pre {@code isValidPair(a, b)}
     * @modifies {@code this}, but not {@code this.extent()}
     * @post {@code this == \old(this) minus [ (a, b) ]}
     */
    @Override
    public void remove(int a, int b) throws IllegalArgumentException {
        if (!isValidPair(a, b)) {
            throw new IllegalArgumentException("Precondition violated " +
                    "remove(" + a + ", " + b + ")");
        }
        relation.get(a).remove(b);
    }

}