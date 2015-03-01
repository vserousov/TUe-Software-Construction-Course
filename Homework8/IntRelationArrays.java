/**
 * An implementation of {@link IntRelation} using nested arrays.
 * For relations with a small extent this may work faster.
 * However, relations with a large extent use more memory,
 * even when they are sparse (have few related pairs).
 *
 * Serousov Vitaly, 201 SE, 22.10.2014
 */
// -----8<----- cut line -----8<-----
public class IntRelationArrays extends IntRelation {

    /** Representation of the relation. */
    private boolean[][] relation;
    // could be declared final

    /*
     * Representation invariants
     *
     * NotNull: relation != null
     * Extent: relation.length == extent()
     * ElementsNotNull: (\forall i; relation.has(i); relation[i] != null)
     * ElementsSameSize: (\forall i; relation.has(i);
     *     relation[i].length == relation.length)
     *
     * Abstraction function: set of (a, b) such that relation[a][b] holds
     */
    public IntRelationArrays(final int n) throws IllegalArgumentException {
        super(n);
        relation = new boolean[n][n];
    }

    /**
     * Checks whether the representation invariants hold.
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
        for (int i = 0; i != extent(); ++ i) {
            // ElementsNotNull
            if (relation[i] == null) {
                throw new IllegalStateException(
                        "relation[" + i + "] == null");
            }
            // ElementsSameSize
            if (relation[i].length != extent()) {
                throw new IllegalStateException(
                        "relation[" + i + "].length != extent()");
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
        return relation.length;
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
        return relation[a][b];
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
        relation[a][b] = true;
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
        relation[a][b] = false;
    }

}
