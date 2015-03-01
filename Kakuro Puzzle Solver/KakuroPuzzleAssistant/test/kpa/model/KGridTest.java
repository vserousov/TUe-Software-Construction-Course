package kpa.model;

import java.util.List;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Smoke tests for class {@code KGrid}.
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class KGridTest {

    /**
     * Tests constructor.
     */
    @Test
    public void testKGrid() {
        System.out.println("KGrid constructor, plain");
        String expResult = "a 2 -  9 3\n" + "b 1 | 17 2\n";
        String expMatrix =
                " \\ \\ \\ \\ \\\n" +
                " \\ \\ . . .\n" +
                " \\ . \\ \\ \\\n" +
                " \\ . \\ \\ \\\n";
        final KGrid instance;
        instance = new KGrid(new Scanner(expResult));
        System.out.println(instance);
        System.out.println(instance.gridAsString());
        assertEquals("getRowCount", 4, instance.getRowCount());
        assertEquals("getColumnCount", 5, instance.getColumnCount());
        assertEquals("entriesAsString", expResult, instance.entriesAsString());
        assertEquals("matrixAsString", expMatrix, instance.gridAsString());
        assertEquals("# blocked cells", 15, instance.getStateCount(KCell.BLOCKED));
        assertEquals("# empty cells", 5, instance.getStateCount(KCell.EMPTY));
        assertTrue("isFull", ! instance.isFull());
        assertTrue("isValid", instance.isValid());
    }

    /**
     * Tests constructor with initialization of non-blocked cells.
     */
    @Test
    public void testKGrid2() {
        System.out.println("KGrid constructor, with extra initialized cell");
        String expResult = "a 2 -  9 3\n" + "b 1 | 17 2\n"
                + "=\n" + "a 3 = 1\n" + "c 1 = 9\n";
        String expMatrix =
                " \\ \\ \\ \\ \\\n" +
                " \\ \\ . 1 .\n" +
                " \\ . \\ \\ \\\n" +
                " \\ 9 \\ \\ \\\n";
        final KGrid instance;
        instance = new KGrid(new Scanner(expResult));
        System.out.println(instance);
        assertEquals("getRowCount", 4, instance.getRowCount());
        assertEquals("getColumnCount", 5, instance.getColumnCount());
        assertEquals("toString", expResult, instance.toString());
        assertEquals("matrixAsString", expMatrix, instance.gridAsString());
        assertEquals("# blocked cells", 15, instance.getStateCount(KCell.BLOCKED));
        assertEquals("# empty cells", 3, instance.getStateCount(KCell.EMPTY));
        assertEquals("# 9 cells", 1, instance.getStateCount(9));
        assertTrue("isFull", ! instance.isFull());
        assertTrue("isValid", instance.isValid());
    }

}
