package kpa.solver;

/**
 * Generic interface to be implemented by generators, that can be queried
 * for {@link A} objects.
 *
 * @param A  Type of data items generated.
 *
 * @author Serousov Vitaly, last changes 11.12.2014
 */
public interface Generator<A> {

    /**
     * Sets the observer.
     * Set to {@code Null} to remove observer.
     *
     * @param observer  the new observer
     */
    void setObserver(GeneratorObserver<A> observer);

    /**
     * Gets the current data.
     *
     * @return Current data, as {@link String}
     */
    A getObject();

}
