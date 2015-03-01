/**
 * Consider the following dice game.
 * n players (n >= 1) each roll once per round.
 * Player 1 rolls a (fair) dodecahedron,
 * having 12 faces with the numbers 1 through 12.
 * The other players (2 through N) roll two fair dice,
 * each having 6 faces with the numbers 1 through 6.
 * The player with the unique highest roll
 * wins the round.  If the highest roll is
 * not unique, then there is no round winner.
 *
 <!--//# BEGIN TODO Name, group id, and date-->
 Serousov Vitaly, 201 SE, 15.10.2014
 <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class DiceGameDecomposed {

    /**
     * Simulates r >= 0 rounds of the dice game and
     * returns how often each of n >= 1 players has won.
     * The first player rolls with a dodecehedron;
     * the others with two regular (6-sided) dice.
     * The return value is a Histogram, where
     * index Table.NO_WINNER counts the number of rounds without winner, and
     * index i > 0 counts the number of rounds won by player i.
     *
     * @param n  the number of players
     * @param r  number of rounds
     * @return histogram with winning frequencies
     * @throws IllegalArgumentException if precondition violated
     * @pre  {@code 1 <= n && 0 <= r}
     * @post {@code \result.getUpperBound() == n &&}<br>
     *   {@code \result.getCount(Table.NO_WINNER) ==
     *     number of rounds without winner}<br>
     *   {@code (\forall int i; 1 <= i <= n;
     *     \result.getCount(i) == number of rounds won by player i) &&}<br>
     *   {@code \result.getTotalCount() == r}
     */
    static public Histogram simulate(int n, int r)
            throws IllegalArgumentException {
        if (! (1 <= n && 0 <= r)) {
            throw new IllegalArgumentException("DiceGameDecomposed.simulate(" +
                    n + ", " + r + "): precondition violated");
        }
        GameTable table = new GameTable(n); // table with players
        Histogram histogram = new Histogram(n); // winning frequencies

        // Simulate r rounds
        for (int round = 1; round <= r; ++ round) {
            table.rollAll();
            histogram.update(table.getWinner());
        } // for round

        return histogram;
    }

}
