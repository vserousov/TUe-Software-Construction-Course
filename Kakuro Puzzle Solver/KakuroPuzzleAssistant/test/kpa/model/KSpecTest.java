package kpa.model;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for {@code KSpec}.
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class KSpecTest {

    /**
     * Test of getSum method, of class KSpec.
     */
    @Test
    public void testGetSum() {
        System.out.println("getSum");
        KSpec instance = new KSpec(9, 3);
        int expResult = 9;
        int result = instance.getSum();
        assertEquals("Result", expResult, result);
    }

    /**
     * Test of getLength method, of class KSpec.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        KSpec instance = new KSpec(9, 3);
        int expResult = 3;
        int result = instance.getLength();
        assertEquals("Result", expResult, result);
    }

    /**
     * Test of toString method, of class KSpec.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        KSpec instance = new KSpec(9, 3);
        String expResult = " 9 3";
        String result = instance.toString();
        assertEquals("Result", expResult, result);
    }

    /**
     * Test of toStringLong method, of class KSpec.
     */
    @Test
    public void testToStringLong() {
        System.out.println("toStringLong");
        KSpec instance = new KSpec(9, 3);
        String expResult = "{ sum: 9, length: 3 }";
        String result = instance.toStringLong();
        assertEquals("Result", expResult, result);
    }

    /**
     * Test of KSpec(Set<Integer>) constructor of class KSpec.
     */
    @Test
    public void testKSpecSetInteger() {
        System.out.println("KSpec(Set<Integer>)");
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        set.add(3);
        set.add(5);
        String expResult = " 9 3";
        KSpec instance = new KSpec(set);
        String result = instance.toString();
        assertEquals("toString", expResult, result);
    }

    /**
     * Test of KSpec(Scanner) constructor of class KSpec.
     */
    @Test
    public void testKSpecScanner() {
        System.out.println("KSpec(Sanner)");
        String expResult = " 9 3";
        KSpec instance = new KSpec(new Scanner(expResult));
        String result = instance.toString();
        assertEquals("toString", expResult, result);
    }

}
