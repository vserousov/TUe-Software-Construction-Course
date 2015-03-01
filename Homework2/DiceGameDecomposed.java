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
 * Serousov Vitaly
 * 201 SE
 * 16.09.2014
 */
// -----8<----- cut line -----8<-----
public class DiceGameDecomposed {

    /** Index for frequency of rounds without winner */
    final static int NO_WINNER = 0;

    /**
     * Simulates r >= 0 rounds of the dice game and
     * returns how often each of n >= 1 players has won.
     * The return value is an array, where
     * index 0 counts the number of rounds without winner, and
     * index i > 0 counts the number of rounds won by player i.
     */
    static public int[] simulate(int n, int r) {
        int[] result; // winning frequencies

        result = new int[1 + n]; // initialize to 0
        for (int i = 0; i < r; i++) {
            int winner = simulate1round(n); // winner of the round
            result[winner]++;
        }

        return result;
    }

    /**
     * Simulates one round of the game for n players.
     * Returns the winner of the round, or NO_WINNER
     * if there is no winner.
     */
    static int simulate1round(int n) {

        int[] rolls = getRolls(n); //array with n rolls
        int winner = getWinner(rolls); // winner of the round

        return winner;
    }

    /**
     * Returns array with n rolls:
     * the first player rolls a dodecahedron;
     * the other players roll two dice.
     * N.B. The roll of player 1 is returned at index 0.
     */
    static int[] getRolls(int n) {
        int[] result = new int[n]; // array with n rolls

        result[0] = roll(12);
        for (int i = 1; i < n; i++) {
            result[i] = roll(6) + roll(6);
        }

        return result;
    }

    /**
     * The random generator, used only by roll()
     */
    final static java.util.Random random = new java.util.Random();

    /**
     * Rolls k-sided fair die with values 1 through k.
     */
    static int roll(int k) {
        return 1 + random.nextInt(k);
    }

    /**
     * Determine round winner, given the round rolls.
     */
    static int getWinner(int[] rolls) {

        int winner = NO_WINNER; // winner of the round
        int maximum = max(rolls); // maximum of array of rolls

        if (count(maximum, rolls) == 1) {
            winner = NO_WINNER + 1 + find(maximum, rolls);
        }

        return winner;
    }

    /**
     * Returns maximum of a given integer array of nonnegative values.
     * N.B. The array is not empty.
     */
    static int max(int[] a) {
        int result = a[0]; // maximum of a integer array

        for (int i = 0; i < a.length; i++) {
            if (a[i] > result) {
                result = a[i];
            }
        }

        return result;
    }

    /**
     * Returns how many times value x occurs in array a.
     */
    static int count(int x, int[] a) {
        int result = 0; // value x occurrence

        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                result++;
            }
        }

        return result;
    }

    /**
     * Finds a value v in array a, given that it occurs.
     */
    static int find(int x, int[] a) {
        int index = 0; // index of element x

        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                index = i;
                break;
            }
        }

        return index;
    }

}