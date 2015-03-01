package kpa.model;

import java.util.Scanner;

/**
 * Direction in the Kakuro puzzle grid.
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public enum Direction {

    /** The horizontal direction. */
    HORIZONTAL("-"),
    /** The vertical direction. */
    VERTICAL("|");

    private final String symbol;

    Direction(final String symbol) {
        this.symbol = symbol;
    }

    /**
     * Determines opposite direction of given direction.
     *
     * @param direction  the given direction
     * @return  the opposite of {@code direction}
     */
    public static Direction opposite(final Direction direction) {
        switch (direction) {
            case HORIZONTAL: {
                return VERTICAL;
            }
            case VERTICAL: {
                return HORIZONTAL;
            }
            default: {
                throw new IllegalArgumentException(
                        "Direction.opposite().pre failed");
            }
        }
    }
    @Override
    public String toString() {
        return this.symbol;
    }

    /**
     * Factory method, returning a direction based on a given string.
     *
     * @param s  the given string
     * @return  direction represented by {@code s}
     */
    public static Direction fromString(final String s) {
        switch (s) {
            case "-": {
                return HORIZONTAL;
            }
            case "|": {
                return VERTICAL;
            }
            default: {
                throw new IllegalArgumentException(
                        "Direction.fromString().pre failed");
            }
        }
    }

    /**
     * Factory method, returning the direction represented by a given scanner.
     *
     * @param scanner  the given scanner
     * @return  direction delivered by {@code scanner}
     */
    public static Direction fromScanner(final Scanner scanner) {
        return fromString(scanner.next("."));
    }

    /**
     * Very simple test.
     *
     * @param args  the command-line arguments (unused)
     */
    public static void main (String[] args) {
        for (Direction d : Direction.values()) {
            System.out.println(d);
        }
    }

}
