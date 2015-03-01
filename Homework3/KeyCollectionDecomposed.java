/**
 * Concrete class for homework assignment 3 in Series 3,
 * where you provide a functional decomposition of {@code isSecure()}.
 * Serousov Vitaly
 * 201 SE
 * 25.09.2014
 */
// -----8<----- cut line -----8<-----
public class KeyCollectionDecomposed extends AbstractKeyCollection {

    @Override
    public boolean isSecure(int[][][] keys) {
        for (int k1 = 0; k1 < keys.length; k1++) {
            for (int k2 = 0; k2 < keys.length; k2++) {
                if (k1 != k2) {
                    boolean result = !CK(keys[k1], keys[k2]);

                    if (!result) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Checks if keys {@code key1} and {@code key2} are convertible
     *
     * @param key1  first key
     * @param key2  second key
     * @return  true, if keys are convertible, otherwise return true
     */
    public boolean CK(int[][] key1, int[][] key2) {

        if (key1 == null || key2 == null) {
            return false;
        }

        if (key1.length == 0 || key2.length == 0) {
            return false;
        }

        for (int r = 0; r < key1.length; r++) {
            if (!CR(key1[r], key2[r])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if corresponding rows have the same length
     * and if element from the first row is not bigger than
     * corresponding element from the second row
     *
     * @param row1 first row
     * @param row2 second row
     * @return  true, if {@code row1} and {@code row2} have the same length
     *   and if {@code row1[i] <= row2[i]}, otherwise return false
     */
    public boolean CR(int[] row1, int[] row2) {
        if (row1 == null || row2 == null || row1.length == 0) {
            return false;
        }

        if (row1.length != row2.length) {
            return false;
        }

        for (int i = 0; i < row1.length; i++) {
            if (row1[i] > row2[i]) {
                return false;
            }
        }

        return true;
    }
        
}
