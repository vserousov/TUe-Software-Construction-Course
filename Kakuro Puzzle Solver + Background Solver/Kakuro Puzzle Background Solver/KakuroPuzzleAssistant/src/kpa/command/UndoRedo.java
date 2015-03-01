package kpa.command;

import java.util.Stack;

/**
 * Facilities for an undo-redo mechanism, on the basis of commands.
 *
 * @author Serousov Vitaly, last changes 11.12.2014
 */
public class UndoRedo {

    /** Stack for undo operation. */
    private Stack<Command> undoStack = new Stack<Command>();
    
    /** Stack for redo operation. */
    private Stack<Command> redoStack = new Stack<Command>();

    /**
     * Returns whether an {@code undo} is possible.
     *
     * @return whether {@code undo} is possible
     */
    public boolean canUndo() {
        if (undoStack.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns whether a {@code redo} is possible.
     *
     * @return {@code redo().pre}
     */
    public boolean canRedo() {
        if (redoStack.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns command most recently done.
     *
     * @return command at top of undo stack
     * @throw IllegalStateException  if precondition failed
     * @pre {@code canUndo()}
     */
    public Command lastDone() throws IllegalStateException {
        if (! canUndo()) {
            throw new IllegalStateException("Precondition violated: "
            + "Undo stack is empty");
        }
        
        return undoStack.lastElement();
    }

    /**
     * Returns command most recently undone.
     *
     * @return command at top of undo stack
     * @throw IllegalStateException  if precondition failed
     * @pre {@code canRedo()}
     */
    public Command lastUndone() throws IllegalStateException {
        if (! canRedo()) {
            throw new IllegalStateException("Precondition violated: "
            + "Redo stack is empty");
        }
        
        return redoStack.lastElement();
    }

    /**
     * Clears all undo-redo history.
     */
    public void clear() {
        undoStack.clear();
        redoStack.clear();
    }

    /**
     * Adds given command to the do-history.
     * If the command was not yet executed,
     * it is first executed.
     *
     * @param command  the command to incorporate
     */
    public void did(final Command command) {
        if (! command.isExecuted()) {
            command.execute();
        }
        
        undoStack.push(command);
        redoStack.clear();
    }

    /**
     * Undo the most recently done command, optionally allowing
     * it to be redone.
     *
     * @param redoable  whether to allow redo
     * @throws IllegalStateException  if precondition violated
     * @pre {@code canUndo()}
     */
    public void undo(final boolean redoable) throws IllegalStateException {
        if (! canUndo()) {
            throw new IllegalStateException("Precondition violated: "
            + "Undo stack is empty");
        }
        
        Command c = undoStack.pop();
        
        if (redoable) {
            redoStack.push(c);
        }
        
        c.revert();
    }

    /**
     * Redo the most recently undone command.
     *
     * @throws IllegalStateException  if precondition violated
     * @pre {@code canRedo()}
     */
    public void redo() throws IllegalStateException {
        if (! canRedo()) {
            throw new IllegalStateException("Precondition violated: "
            + "Redo stack is empty");
        }
        
        Command c = redoStack.pop();
        undoStack.push(c);
        c.execute();
    }

    /**
     * Undo all done commands.
     *
     * @param redoable  whether to allow redo
     */
    public void undoAll(final boolean redoable) {
        while (canUndo()) {
            undo(redoable);
        }
    }

    /**
     * Redo all undone commands.
     */
    public void redoAll() {
        while (canRedo()) {
            redo();
        }
    }

}
