/**
 * Consider the following dice game.
 * {@code n} players ({@code n >= 1}) each roll once per round.
 * Player 1 rolls a (fair) dodecahedron,
 * having 12 faces with the numbers 1 through 12.
 * The other players (2 through N) roll two fair dice,
 * each having 6 faces with the numbers 1 through 6.
 * The player with the unique highest roll
 * wins the round. If the highest roll is
 * not unique, then there is no round winner.
 *
 * Serousov Vitaly
 * 201 SE
 * 25.09.2014
 */
// -----8<----- cut line -----8<-----
public class DiceGameDecomposed {

    /** Index for frequency of rounds without winner */
    final static int NO_WINNER = 0;

    /**
     * Simulates {@code r >= 0} rounds of the dice game and
     * returns how often each of {@code n >= 1} players has won.
     * The return value is an array, where
     * index 0 counts the number of rounds without winner, and
     * index {@code i > 0} counts the number of rounds won by player {@code i}.
     *
     * @param n  number of players
     * @param r  number of rounds
     * @return  array with numbers of rounds won by each player
     *   and number of rounds without winner
     * @pre {@code n >= 1 && r >= 0}
     * @post {@code \result.length == n + 1
     *   && (\forall i; result.has(i); result[i] >= 0)
     *   && (\sum i; result.has(i); result[i]) == r}
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
     * Simulates one round of the game for {@code n} players.
     * Returns the winner of the round, or {@code NO_WINNER}
     * if there is no winner.
     *
     * @param n  number of players
     * @return  winner of the round or {@code NO_WINNER}
     * @pre {@code n >= 1}
     * @post {@code 0 <= \result <= n}
     */
    static int simulate1round(int n) {

        int[] rolls = getRolls(n); //array with n rolls
        int winner = getWinner(rolls); // winner of the round

        return winner;
    }

    /**
     * Returns array with {@code n} rolls:
     * the first player rolls a dodecahedron;
     * the other players roll two dice.
     * N.B. The roll of player 1 is returned at index 0.
     *
     * @param n  number of rolls
     * @return  array with {@code n} rolls
     * @pre {@code n >= 1}
     * @post {@code \result.length == n}
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
     * The random generator, used only by {@code roll()}
     */
    final static java.util.Random RANDOM = new java.util.Random();

    /**
     * Rolls k-sided fair die with values 1 through {@code k}.
     *
     * @param k  number of fair sides
     * @return  random integer value from 1 to {@code k}
     * @pre {@code k >= 1}
     * @post {@code 1 <= \result <= k}
     */
    static int roll(int k) {
        return 1 + RANDOM.nextInt(k);
    }

    /**
     * Determine round winner, given the round rolls.
     *
     * @param rolls  array with rolls
     * @return  winner of the round (index of player
     *   with the highest value) or {@code NO_WINNER}
     *   if there are more than one winner with the same value
     * @pre {@code rolls != null && rolls.length >= 1}
     * @post {@code rolls.has(\result)}
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
     * Returns maximum of a given integer array of non-negative values.
     * N.B. The array is not empty.
     *
     * @param a  integer non-empty array with non-negative values
     * @return  the highest value in array
     * @pre {@code a != null && a.length >= 1 && (\forall i; a.has(i); a[i] >= 0)}
     * @post {@code \result == (\max i; a.has(i); a[i])}
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
     *
     * @param x  integer number
     * @param a  integer non-empty array
     * @return  value {@code x} occurrences in array {@code a}
     * @pre {@code a != null && a.length >= 1}
     * @post {@code \result == (\num_of i; a.has(i); a[i] == x)}
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
     * Finds a value {@code x} in array {@code a}, given that it occurs.
     *
     * @param x  integer number
     * @param a  integer non-empty array
     * @return  index of value {@code x} in array {@code a}
     * @pre {@code a != null && a.length >= 1 && (\exists x; a.has(i); a[i] == x)}
     * @post {@code a.has(\result) && a[\result] == x}
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