package kpa.command;

import kpa.model.KCell;

/**
 * The set command.
 * Illustrates a concrete command with a parameter.
 * It also needs to store KCell values in order to revert it.
 *
 * @author Serousov Vitaly, last changes 11.12.2014
 */
public class SetCommand extends Command {

    /** The command's parameter. */
    private int newState;

    /** Previous state of the receiver, for revert(). */
    private int oldState;

    /**
     * Constructs a set command for a given receiver and new state.
     *
     * @param receiver  the given receiver
     * @param newState  the new state
     */
    public SetCommand(final KCell receiver, final int newState) {
        super(receiver);
        this.newState = newState;
    }

    @Override
    public void execute() {
        super.execute();
        oldState = receiver.getState();
        receiver.setState(newState);
    }

    @Override
    public void revert() {
        super.revert();
        receiver.setState(oldState);
    }

}
