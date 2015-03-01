/**
 * Class for a player that can roll with a specific set of dice.
 *
 <!--//# BEGIN TODO Name, group id, and date-->
 Serousov Vitaly, 201 SE, 15.10.2014
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class Player {

    /** Number of dice */
    private final int nDice;

    /** Number of sides per dice */
    private final int nSides;

    /** Last roll value */
    private int value;

    /** Random generator */
    private final java.util.Random random;

    // Representation invariants:
    //   1 <= nDice
    //   1 <= nSides
    //   0 <= value <= nDice * nSides
    //   random != null

    // ===== ===== Constructors ===== =====

    /**
     * Constructs a player for a given set of dice.
     *
     * @param nDice  number of dice
     * @param nSides  number of sides per dice
     * @throws IllegalArgumentException if precondition violated
     * @pre {@code 1 <= nDice && 1 <= nSides}
     * @post new Player constructed
     */
    public Player(final int nDice, final int nSides)
            throws IllegalArgumentException {
//# BEGIN TODO constructor implementation
        if (! (1 <= nDice && 1 <= nSides)) {
            throw new IllegalArgumentException("Precondition violated:" +
                    " nDice = " + nDice + ", nSides = " + nSides);
        }
        this.nDice = nDice;
        this.nSides = nSides;
        this.random = new java.util.Random();
//# END TODO
    }

    // ===== ===== Queries ===== =====

    /**
     * Gets last roll value, or 0 if never rolled.
     *
     * @pre {@code true}
     * @post {@code \result == value of last roll}
     */
    public int getValue() {
//# BEGIN TODO query implementation
        return this.value;
//# END TODO
    }

    // ===== ===== Commands ===== =====

    /**
     * Lets player roll with the dice.
     *
     * @pre {@code true}
     * @modifies {@code this}
     * @post {@code getValue() == total value of roll with player's dice}
     */
    public void rollDice() {
//# BEGIN TODO command implementation
        this.value = 0;
        for (int i = 0; i < this.nDice; i++) {
            this.value += 1 + random.nextInt(nSides);
        }
//# END TODO
    }

}
