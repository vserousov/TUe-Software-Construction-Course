/**
 * Consider the following dice game.
 * Five players each roll once per round.
 * Player 1 rolls a (fair) dodecahedron,
 * having 12 faces with the numbers 1 through 12.
 * The other players (2 through 5) roll two fair dice,
 * each having 6 faces with the numbers 1 through 6.
 * The player with the unique highest roll
 * wins the round.  If the highest roll is
 * not unique, then there is no round winner.
 *
 * Serousov Vitaly
 * 201 SE
 * 10.09.2014
 */
// -----8<----- cut line -----8<-----
public class DiceGame {

    /** Number of players, >= 1 */
    final static int NUM_PLAYERS = 5;

    /** Index for frequency of rounds without winner */
    final static int NO_WINNER = 0;

    /**
     * Simulates r >= 0 rounds of the dice game and
     * returns how often each player won.
     * The return value is an array, where
     * index 0 counts the number rounds without winner, and
     * index i > 0 counts the number of rounds won by player i.
     */
    static public int[] simulate(int r) {

        int[] result; // winning frequencies
        result = new int[1 + NUM_PLAYERS]; // initialize to 0

        for (int i = 0; i < r; i++) { // loop for all rounds

            int max = (int)(Math.random() * 12) + 1; // set current highest score to first player
            int id = NO_WINNER + 1; // set current player id to first player
            boolean unique = true; // it's unique, because it's first player

            for (int j = NO_WINNER + 2; j <= NUM_PLAYERS; j++) { // start loop for other players (2 through 5)

                int temp = ((int)(Math.random() * 6) + 1) + ((int)(Math.random() * 6) + 1);
                // get random number from two fair dice roll

                if (temp == max) { // if this number is equal to highest score
                    unique = false; // then it is not unique
                }

                if (temp > max) { // if this number is more than highest score
                    max = temp; // set new highest score
                    id = j; // update player id
                    unique = true; // set it unique
                }
            }

            if (!unique) { // if there is no winner
                result[NO_WINNER]++; // then increase no winner counter
            } else {
                result[id]++; // otherwise increase winner counter
            }
        }

        return result;
    }
}