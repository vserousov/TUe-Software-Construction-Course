package kpa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * State of a Kakuro puzzle, without auxiliary solver-related information:
 * the collection of entries, and the states of the cells in the grid
 * (mutable).
 * Acts as a facade for the underlying classes.
 * A Kakuro puzzle has
 * <ul>
 * <li>a name;
 * <li>minimum and maximum value for cell states,
 *   the minimum is at least one, and the maximum is at least the minimum;
 * <li>a mode of operation;
 * <li>a grid of cells.
 * </ul>
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class KPuzzle {

    /** The puzzle's (file) name. */
    private String name;

    /** The possible modes of a puzzle. */
    public enum Mode {
        /** When puzzle can be edited. */
        EDIT,
        /** When puzzle can be solved, but not edited. */
        SOLVE,
        /** When puzzle can only be viewed, but not changed. */
        VIEW
    }

    /** The puzzle's mode. */
    private Mode mode;

    /** Smallest number allowed.{@code KCell.EMPTY < minNumber} */
    private int minNumber = 1;

    /** Largest number allowed.{@code minNumber <= maxNumber} */
    private int maxNumber = 9;

    /** The grid of cells. */
    private KGrid grid;

    /**
     * Constructs a new puzzle with initial state read from given scanner,
     * and with a given name.
     * The actual dimensions are determined from the input.
     *
     * @param scanner  the given scanner
     * @param name  the given name
     */
    public KPuzzle(final Scanner scanner, final String name) {
        this.name = name;
        this.mode = Mode.VIEW;
        this.grid = new KGrid(scanner);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (mode != Mode.EDIT) {
            throw new IllegalStateException(this.getClass().getSimpleName()
                    + ".setName(): not in EDIT mode");
        }
        this.name = name;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(int minNumber) {
        if (mode != Mode.EDIT) {
            throw new IllegalStateException(this.getClass().getSimpleName()
                    + ".setMinNumber(): not in EDIT mode");
        }
        if (minNumber < 1) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ".setMinNumber.pre failed: minNumber == "
                    + minNumber + " <= " + KCell.EMPTY);
        }
        this.minNumber = minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        if (mode != Mode.EDIT) {
            throw new IllegalStateException(this.getClass().getSimpleName()
                    + ".setMaxNumber(): not in EDIT mode");
        }
        if (maxNumber < minNumber) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ".setMaxNumber.pre failed: maxNumber == "
                    + maxNumber + " < " + minNumber + " == minNumber");
        }
        this.maxNumber = maxNumber;
    }

    /**
     * Gets the number of columns in this puzzle.
     *
     * @return number of columns
     */
    public int getColumnCount() {
        return grid.getColumnCount();
    }

    /**
     * Gets the number of rows in this puzzle.
     *
     * @return number of rows
     */
    public int getRowCount() {
        return grid.getRowCount();
    }

    /**
     * Returns whether a coordinate pair is in the puzzle's grid.
     *
     * @param rowIndex  the row index
     * @param columnIndex  the column index
     * @return  whether {@code (row, column)} belongs to the grid
     */
    public boolean has(final int rowIndex, final int columnIndex) {
        return 0 <= rowIndex && rowIndex < grid.getRowCount() &&
                0 <= columnIndex && columnIndex < grid.getColumnCount();
    }

    /**
     * Gets the cell at given coordinates.
     *
     * @return cell at {@code rowIndex, columnIndex}
     * @param rowIndex  the row coordinate to get from
     * @param columnIndex  the column coordinate to get from
     * @pre {@code 0 <= rowIndex < getRows() &&
     *   0 <= columnIndex < getColumns()}
     * @post {@code \result = cells[rowIndex, columnIndex]}
     */
    public KCell getCell(final int rowIndex, final int columnIndex) {
        if (! has(rowIndex, columnIndex)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + ".getCell().pre failed: invalid location");
        }
        return grid.getCell(rowIndex, columnIndex);
    }

    public List<KEntry> getEntries() {
        return grid.getEntries();
    }

    /**
     * Returns whether puzzle is solved.
     *
     * @return whether puzzle is solved
     */
    public boolean isSolved() {
        return grid.isFull() && grid.isValid();
    }

    /**
     * Clears the non-blocked cells.
     */
    public void clear() {
        grid.clear();
    }

    /**
     * Makes a descriptor for an empty puzzle of given size.
     *
     * @param size  the given size
     * @return  descriptor string for empty puzzle of {@code size}
     */
    public static String makeEmptyDescriptor(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("KPuzzle"
                    + ".makeEmptyDescriptor().pre failed: "
                    + "size == " + size + " < 0");
        }
        return "Undefined Kakuro puzzle state with size " + size;
    }

    public String gridAsString() {
        return grid.gridAsString();
    }

    @Override
    public String toString() {
        return grid.toString();
    }

}
