/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kpa.model;

import java.util.List;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wstomv
 */
public class KEntryTest {

    /**
     * Test constructor, getDirection, and toString.
     */
    @Test
    public void testConstructor() {
        System.out.println("KEntry");
        String expResult = "a 2 -  9 3";
        Scanner scanner = new Scanner(expResult);
        KEntry instance = new KEntry(scanner);
        assertEquals("direction", Direction.HORIZONTAL, instance.getDirection());
        assertEquals("toString", expResult, instance.toString());
    }

    /**
     * Tests scanEntries.
     */
    @Test
    public void testScanEntries() {
        System.out.println("scanEntries, plain");
        String entry0 = "a 2 -  9 3";
        String entry1 = "b 1 | 17 2";
        String expResult = entry0 + "\n" + entry1 + "\n";
        List<KEntry> result = KEntry.scanEntries(new Scanner(expResult));
        assertEquals("size", 2, result.size());
        assertEquals("get(0)", entry0, result.get(0).toString());
        assertEquals("get(1)", entry1, result.get(1).toString());
    }

    /**
     * Tests scanEntries.
     */
    @Test
    public void testScanEntries2() {
        System.out.println("scanEntries, with extra line");
        String entry0 = "a 2 -  9 3";
        String entry1 = "b 1 | 17 2";
        String expResult = entry0 + "\n" + entry1 + "\n=\n";
        Scanner scanner = new Scanner(expResult);
        List<KEntry> result = KEntry.scanEntries(scanner);
        assertEquals("size", 2, result.size());
        assertEquals("get(0)", entry0, result.get(0).toString());
        assertEquals("get(1)", entry1, result.get(1).toString());
        assertTrue("next", scanner.hasNext("="));
    }

    /**
     * Test isValid().
     */
    @Test
    public void testIsValidAllEmpty() {
        System.out.println("isValid, empty cells");
        String entry = "a 2 -  9 3";
        Scanner scanner = new Scanner(entry);
        KEntry instance = new KEntry(scanner);
        KCell[] cells = new KCell[] {
            new KCell(KCell.EMPTY),
            new KCell(KCell.EMPTY),
            new KCell(KCell.EMPTY)
        };
        for (KCell cell : cells) {
            instance.add(cell);
            cell.add(instance);
        }
        assertTrue("isValid, all 3 empty", instance.isValid());
        cells[0].setState(8);
        assertFalse("isValid, 2 empty, too high", instance.isValid());
        cells[0].setState(1);
        assertTrue("isValid, 2 empty, not too high", instance.isValid());
        cells[1].setState(2);
        assertTrue("isValid, 1 empty, not too high", instance.isValid());
        cells[2].setState(6);
        assertTrue("isValid, no empty, sum OK", instance.isValid());
        cells[2].setState(7);
        assertFalse("isValid, no empty, sum too high", instance.isValid());
        cells[2].setState(5);
        assertFalse("isValid, no empty, sum too low", instance.isValid());
    }

}
