package kpa.solver;

import java.util.EventListener;
import java.util.Set;

/**
 * Interface with events that can be triggered by a Kakuro combination generator.
 *
 * @author Serousov Vitaly, last changes 11.12.2014
 */
public interface GeneratorListener extends EventListener {

    /**
     * Reports a generated combination.
     *
     * @param combination  the newly generated combination
     */
    void combinationGenerated(Set<Integer> combination);

}
