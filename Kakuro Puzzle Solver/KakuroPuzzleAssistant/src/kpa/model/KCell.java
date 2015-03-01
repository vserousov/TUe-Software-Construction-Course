package kpa.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * A grid cell for a Kakuro puzzle.
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class KCell implements Iterable<AbstractGroup> {

    /** The blocked state. */
    public static final int BLOCKED = -1;

    /** The empty state. */
    public static final int EMPTY = 0;

    /** String for blocked state. */
    public static final String BLOCKED_STR = "\\";

    /** String for empty state. */
    public static final String EMPTY_STR = ".";

    /** The cell's state. */
    private int state;

    /**
     * The groups to which this cell belongs.
     * The first group will be the whole grid.
     */
    private final List<AbstractGroup> groups;

    /** Location in the grid, if any. */
    private Location location;

    /** The grid to which this cell belongs, if any. */
    private KGrid grid;

    /**
     * Constructs a cell with a given state.
     *
     * @param state  state
     * @pre {@code state} is a valid state
     */
    public KCell(final int state) {
        if (state < BLOCKED) {
            throw new IllegalArgumentException("KCell("
                    + state + ").pre failed");
        }
        this.state = state;
        groups = new ArrayList<AbstractGroup>();
        grid = null;
    }

    /**
     * Constructs a cell with a state given by a string.
     *
     * @param state  state
     */
    public KCell(final String state) {
        this(fromString(state));
    }

    /**
     * Constructs a cell from a given scanner.
     *
     * @param scanner  the given scanner
     */
    public KCell(final Scanner scanner) {
        this(scanner.next());
    }

    public int getState() {
        return state;
    }

    /**
     * Sets a new cell state.
     *
     * @param state  the new state
     * @pre {@code state} is valid
     */
    public void setState(int state) {
        if (state < BLOCKED) {
            throw new IllegalArgumentException("KCell.setState("
                    + state + ").pre failed");
        }
        for (AbstractGroup group : groups) {
            group.update(this, state);
        }
        this.state = state;
    }

    /**
     * Returns whether cell is blocked.
     *
     * @return whether {@code this} is blocked
       public KGrid getGrid() {
        return grid;
    }

    public void setGrid(KGrid grid) {
        this.grid = grid;
    }

  */
    public boolean isBlocked() {
        return state == BLOCKED;
    }

    /**
     * Returns whether cell is empty.
     *
     * @return whether {@code this} is empty
     */
    public boolean isEmpty() {
        return state == EMPTY;
    }

    /**
     * Returns whether cell is filled.
     *
     * @return whether {@code this} is filled
     */
    public boolean isFilled() {
        return state > EMPTY;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public KGrid getGrid() {
        return grid;
    }

    public void setGrid(KGrid grid) {
        this.grid = grid;
    }

    /**
     * Returns whether this cell is involved in a rule violation.
     *
     * @return whether this cell conforms to the rules
     */
    public boolean isOK() {
        if (! this.isFilled()) {
            return true;
        }
        for (AbstractGroup group : groups) {
            if (group == grid) {
                continue;
            }
            if (! group.isValid()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns whether this cell is associated with a given group.
     *
     * @param group  the group to check
     * @return whether {@code this} is element of {@code group}
     */
    public boolean isContainedIn(final AbstractGroup group) {
        return groups.contains(group);
    }

    /**
     * Adds a group containing this cell.
     *
     * @pre {@code group != null && ! isElementOf(group)}
     * @modifies {@code this}
     * @post {@code isElementOf(group)}
     */
    void add(final AbstractGroup group) {
        groups.add(group);
    }

    /**
     * Converts string to cell state.
     *
     * @param s  string to convert
     * @return cell state corresponding to s
     * @throw IllegalArgumentException  if invalid string
     */
    public static int fromString(final String s) {
        switch (s) {
            case BLOCKED_STR: {
                return BLOCKED;
            }
            case EMPTY_STR: {
                return EMPTY;
            }
            default: {
                try {
                    int result = Integer.parseInt(s);
                    if (result <= EMPTY) {
                        throw new IllegalArgumentException("KCell.fromString("
                                + s + ").pre failed");
                    }
                    return result;
                }
                catch (Exception e) {
                    throw new IllegalArgumentException("KCell.fromString(" + s
                            + ").pre failed");
                }
            }
        }
    }

    @Override
    public String toString() {
        switch (state) {
            case BLOCKED: {
                return BLOCKED_STR;
            }
            case EMPTY: {
                return EMPTY_STR;
            }
            default: {
                return String.valueOf(state);
            }
        }
    }

    @Override
    public Iterator<AbstractGroup> iterator() {
        return groups.iterator();
    }

}
