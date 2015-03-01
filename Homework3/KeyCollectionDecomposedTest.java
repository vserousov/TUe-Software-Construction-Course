import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for functional decomposition in {@code KeyCollectionDecomposed}.
 *
 * Serousov Vitaly
 * 201 SE
 * 25.09.2014
 */
// -----8<----- cut line -----8<-----
public class KeyCollectionDecomposedTest extends AbstractKeyCollectionTestCases {

    /** key 1 to check **/
    private int[][] key1;

    /** key 2 to check **/
    private int[][] key2;

    /** row 1 to check **/
    private int[] row1;

    /** row 2 to check **/
    private int[] row2;

    public KeyCollectionDecomposedTest() {
    }

    @Before
    public void setUp() {
        instance = new KeyCollectionDecomposed();
    }

    /**
     * Invokes CK(key1, key2) and checks result against expResult.
     *
     * @param msg message
     * @param expResult expected result
     */
    private void checkKeys(String msg, boolean expResult) {
        boolean result = ((KeyCollectionDecomposed)instance).CK(key1, key2);
        assertEquals(msg, expResult, result);
    }

    /**
     * Invokes CR(row1, row2) and checks result against expResult.
     *
     * @param msg message
     * @param expResult expected result
     */
    private void checkRows(String msg, boolean expResult) {
        boolean result = ((KeyCollectionDecomposed)instance).CR(row1, row2);
        assertEquals(msg, expResult, result);
    }

    @Test
    public void noKeysCK() {
        // Secure boundary case: no keys
        key1 = new int[][]{ };
        key2 = new int[][]{ };
        checkKeys("No keys", false);
    }

    @Test
    public void singletonKeysCK() {
        // Secure boundary case: one minimal key
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = null;
        checkKeys("One minimal key", false);
    }

    @Test
    public void twoInconvertibleKeysRow0CK() {
        // Secure boundary case: two inconvertible keys, due to 1st row lengths
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 1, 1 }, new int[]{ 1 }};
        checkKeys("Two inconvertible keys, first row lengths differ", false);
    }

    @Test
    public void twoInconvertibleKeysRow1CK() {
        // Secure boundary case: two inconvertible keys, due to 2nd row lengths
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 1 }, new int[]{ 1, 1 }};
        checkKeys("Two inconvertible keys, second row lengths differ", false);
    }

    @Test
    public void twoInconvertibleKeysSameRowLengthsCK() {
        // Secure boundary case: two inconvertible keys, same row lengths
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 2 }};
        key2 = new int[][]{new int[]{ 2 }, new int[]{ 1 }};
        checkKeys("Two inconvertible keys, same row lengths", false);
    }

    @Test
    public void twoConvertibleKeysRow0CK() {
        // Insecure boundary case: two convertible keys, diff in first row
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 2 }, new int[]{ 1 }};
        checkKeys("Two convertible keys, first row", true);
    }

    @Test
    public void twoConvertibleKeysRow1CK() {
        // Insecure boundary case: two convertible keys, diff in second row
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 1 }, new int[]{ 2 }};
        checkKeys("Two convertible keys, second row", true);
    }

    @Test
    public void twoNonConvertibleKeysRow0RevCK() {
        // Secure boundary case: two non-convertible keys, diff in first row
        // reversed order
        key1 = new int[][]{new int[]{ 2 }, new int[]{ 1 }};
        key2 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        checkKeys("Two non-convertible keys, reversed order, first row", false);
    }

    @Test
    public void twoNonConvertibleKeysRow1RevCK() {
        // Secure boundary case: two non-convertible keys, diff in second row
        // reversed order
        key1 = new int[][]{new int[]{ 1 }, new int[]{ 2 }};
        key2 = new int[][]{new int[]{ 1 }, new int[]{ 1 }};
        checkKeys("Two non-convertible keys, reversed order, second row", false);
    }

    @Test
    public void noKeysCR() {
        // Secure boundary case: no keys
        row1 = new int[]{ };
        row2 = new int[]{ };
        checkRows("No rows", false);
    }

    @Test
    public void singletonKeysCR() {
        // Secure boundary case: one minimal key
        row1 = new int[]{ 1 };
        row2 = null;
        checkRows("One minimal row", false);
    }

    @Test
    public void twoConvertibleKeysRow0CR() {
        // Insecure boundary case: two inconvertible rows, due to 1st row lengths
        row1 = new int[]{ 1 };
        row2 = new int[]{ 1 };
        checkRows("Two convertible rows, first row lengths differ", true);
    }

    @Test
    public void twoInconvertibleKeysRow1CR() {
        // Secure boundary case: two inconvertible rows, due to 2nd row lengths
        row1 = new int[]{ 2 };
        row2 = new int[]{ 1 };
        checkRows("Two inconvertible rows, second row lengths differ", false);
    }

    @Test
    public void twoConvertibleKeysRow0RevCR() {
        // Insecure boundary case: two convertible rows, diff in second row
        // reversed order
        row1 = new int[]{ 1 };
        row2 = new int[]{ 2 };
        checkRows("Two convertible rows, reversed order, second row", true);
    }
}
