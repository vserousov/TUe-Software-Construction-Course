package kpa.solver;

import java.util.EventListener;
import java.util.Set;

/**
 * Generic interface with events that can be triggered by a {@link Generator}.
 *
 * @param A  Type of generator
 *
 * @author Serousov Vitaly, last changes 11.12.2014
 */
public interface GeneratorObserver<A> extends EventListener {

    /**
     * Event that notifies an observer that a generator generated a new object.
     * This object can then be pulled from the generator.
     *
     * @param generator  the sending generator
     */
    void objectGenerated(Generator<A> generator);

}
