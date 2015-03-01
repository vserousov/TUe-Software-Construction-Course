package kpa.model;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Location in the Kakuro puzzle grid (immutable).
 *
 * @inv NonNegative: {@code 0 <= getRow() && 0 <= getColumn()}
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class Location {
    // TODO: consider implementation by extending EnumMap

    /** The row coordinate. */
    private final int row;

    /** The column coordinate. */
    private final int column;

    /**
     * Constructs a new location from given row and column.
     *
     * @param row  the given row coordinate
     * @param column  the given column coordinate
     */
    public Location(final int row, final int column) {
        if (row < 0) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + "().pre failed: row " + row + " < 0");
        }
        if (column < 0) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + "().pre failed: column " + column + " < 0");
        }
        this.row = row;
        this.column = column;
    }

    /** Constructs a new location from a given scanner.
     *
     * @param scanner  the given scanner
     */
    public Location(final Scanner scanner) {
        Pattern original = scanner.delimiter();
        scanner.skip("\\p{javaWhitespace}*");
        scanner.useDelimiter("");
        row = scanner.next("[a-zA-Z]").toLowerCase().charAt(0) - 'a' + 1;
        scanner.useDelimiter(original);
        column = scanner.nextInt();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getCoordinate(Direction direction) {
        switch (direction) {
            case HORIZONTAL: {
                return row;
            }
            case VERTICAL: {
                return column;
            }
            default: {
                assert false :
                        "Location.getCoordinate.pre failed: bad direction";
                return -1; // to satisfy the compiler
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s%2d", (char)('a' + row - 1), column);
    }

    public String toStringLong() {
        return "{ row: " + row + ", column: " + column + " }";
    }

    public static void main(String[] args) {
        Location loc1 = new Location(0, 1);
        Location loc2 = new Location(1, 10);
        System.out.println(loc1);
        System.out.println(loc2);
        System.out.println(loc1.toStringLong());
        System.out.println(loc2.toStringLong());
    }
}
