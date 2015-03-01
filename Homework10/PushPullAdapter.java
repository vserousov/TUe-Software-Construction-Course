import java.util.Set;

/**
 * Adapter that adapts an object of a class implementing
 * {@link GeneratorListener} (which works by pushing)
 * to act like a class implementing
 * {@link GeneratorObserver<Set<Integer>>} (which works by pulling).
 *
 * @author Tom Verhoeff (TU/e)
<!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 27.11.2014
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class PushPullAdapter implements GeneratorObserver<Set<Integer>> {

    /** The adapted listener. */
    private GeneratorListener listener;

    /** Whether to pull objects (or ignore them). */
    private final boolean pull;

    /**
     * Constructs a new {@link PushPullAdapter} that optionally
     * suppresses data pulling to improve performance.
     *
     * @param pull  whether to pull the object
     */
    public PushPullAdapter(GeneratorListener listener, boolean pull) {
        this.listener = listener;
        this.pull = pull;
    }

    /**
     * If pulling, pull the object and pass it on the listener interface;
     * if not pulling, pass {@code null} on the listener interface.
     *
     * @param generator  the originating generator
     */
    @Override
    public void objectGenerated(Generator<Set<Integer>> generator) {
//# BEGIN TODO Implementation of objectGenerated
        if (pull) {
            listener.combinationGenerated(generator.getObject());
        } else {
            listener.combinationGenerated(null);
        }
//# END TODO
    }

}
