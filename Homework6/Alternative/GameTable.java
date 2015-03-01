import java.util.ArrayList;

/**
 * A game table with players.
 *
 <!--//# BEGIN TODO Name, group id, and date-->
 Serousov Vitaly, 201 SE, 15.10.2014
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class GameTable {

    /** Virtual player number for rounds without winner */
    public static final int NO_WINNER = 0;

    /** The players at the table */
    private final ArrayList<Player> players;

    // ===== ===== Constructors ===== =====

    /**
     * Constructs a new game table with a given number of players,
     * where the first player rolls a dodecahedron, and
     * the others roll two regular (6-sided) dice.
     *
     * @param n  number of players
     * @throws IllegalArgumentException if precondition violated
     * @pre {@code 1 <= n}
     * @post new game table constructed
     */
    public GameTable(int n) throws IllegalArgumentException {
//# BEGIN TODO constructor implementation
        if (! (1 <= n)) {
            throw new IllegalArgumentException("Precondition violated: " +
                    "n = " + n);
        }
        players = new ArrayList<Player>();
        players.add(new Player(1, 12));
        for (int i = 1; i < n; i++) {
            players.add(new Player(2, 6));
        }
//# END TODO
    }

    // ===== ===== Queries ===== =====

    /**
     * Gets the number of players at the table.
     *
     * @pre {@code true}
     * @post {@code \result == number of players}
     */
    public int getNumOfPlayers() {
//# BEGIN TODO query implementation
        return players.size() - 1;
//# END TODO
    }

    /**
     * Determine player number of round winner.
     * Returns NO_WINNER, if there is no winner.
     *
     * @return player number of round winner, or NO_WINNER if no winner
     * @pre {@code players != null &&} all players have rolled
     * @post {@code NO_WINNER <= \result <= getNumOfPlayers()}
     */
    public int getWinner() {
//# BEGIN TODO query implementation
        int max = players.get(0).getValue();
        int winner = 1;
        for (int i = 1; i <= getNumOfPlayers(); i++) {
            int value = players.get(i).getValue();
            if (value > max) {
                winner = i + 1;
                max = value;
            } else if (value == max) {
                winner = NO_WINNER;
            } else {

            }
        }
        return winner;
//# END TODO
    }

    // ===== ===== Commands ===== =====

    /**
     * Lets all players roll.
     *
     * @pre {@code true}
     * @modifies {@code this}
     * @post all players have rolled once
     */
    public void rollAll() {
//# BEGIN TODO command implementation
        for (Player player : players) {
            player.rollDice();
        }
//# END TODO
    }

}