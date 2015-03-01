package kpa.model;

import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for {@code Location}.
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class LocationTest {

    /**
     * Test of getRow method, of class Location.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Location instance = new Location(1, 2);
        int expResult = 1;
        int result = instance.getRow();
        assertEquals("Result", expResult, result);
    }

    /**
     * Test of getColumn method, of class Location.
     */
    @Test
    public void testGetColumn() {
        System.out.println("getColumn");
        Location instance = new Location(1, 2);
        int expResult = 2;
        int result = instance.getColumn();
        assertEquals("Result", expResult, result);
    }

    /**
     * Test of getCoordinate method, of class Location.
     */
    @Test
    public void testGetCoordinate() {
        System.out.println("getCoordinate");
        Location instance = new Location(1, 2);
        Direction direction = Direction.HORIZONTAL;
        int expResult = 1;
        int result = instance.getCoordinate(direction);
        assertEquals("Result for " + direction.name(), expResult, result);
        direction = Direction.VERTICAL;
        expResult = 2;
        result = instance.getCoordinate(direction);
        assertEquals("Result for " + direction.name(), expResult, result);
    }

    /**
     * Test of shortString method, of class Location.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Location instance = new Location(2, 3);
        String expResult = "b 3";
        String result = instance.toString();
        assertEquals("Result", expResult, result);
    }

    /**
     * Test of toString method, of class Location.
     */
    @Test
    public void testToStringLong() {
        System.out.println("toStringLong");
        Location instance = new Location(1, 2);
        String expResult = "{ row: 1, column: 2 }";
        String result = instance.toStringLong();
        assertEquals("Result", expResult, result);
    }

    /**
     * Test of Location(Scanner) constructor of class Location.
     */
    @Test
    public void testLocationScanner() {
        System.out.println("Location(Sanner)");
        String expResult = "b 3";
        Scanner scanner = new Scanner(expResult);
        Location instance = new Location(scanner);
        String result = instance.toString();
        assertEquals("toString", expResult, result);

        expResult = "z10";
        instance = new Location(new Scanner(expResult));
        result = instance.toString();
        assertEquals("toString", expResult, result);
    }

    /**
     * Test of main method, of class Location.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Location.main(args);
    }

}
