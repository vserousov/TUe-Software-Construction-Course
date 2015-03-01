import java.util.EventListener;
import java.util.List;
import java.util.Set;
import javax.swing.SwingWorker;

/**
 * A proxy for a {@code KakuroCombinationGenerator}.
 * The proxy delegates generation of Kakuro combinations to
 * a "real" {@code KakuroCombinationGenerator}
 * running in a background thread through a {@link javax.swing.SwingWorker}.
 * <p>
 * Thus, {@code generate()} returns <em>asynchronously</em>
 * (before generation is completed).
 * <p>
 * When the "real" generator is done,
 * a {@code ResultListener} callback is invoked to do post-processing
 * (like showing results in the GUI).
 * <p>
 * The observer of the proxy is made to observe the "real" generator.
 *
 * @author Tom Verhoeff (TU/e, Eindhoven University of Technology)
<!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 11.12.2014
<!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class KCGeneratorProxy extends KakuroCombinationGenerator {

    /**
     * Interface for listeners that receive the generator result.
     * In this case, there is no result value, but completion is signaled.
     */
    public interface ResultListener extends EventListener {
        /**
         * Signals completion of the generator.
         *
         * @param exception  exception caught on get(), if not null
         */
        void generationDone(Exception exception);
    }

    /** Callback where completion is signaled. */
    private ResultListener resultListener;

    /**
     * Sets the result listener.
     *
     * @param resultListener  the given result listener
     */
    public void setResultListener(final ResultListener resultListener) {
        this.resultListener = resultListener;
    }

    /**
     * A {@code GeneratorWorker} is a special {@link javax.swing.SwingWorker}
     * that runs a {@code KakuroCombinationGenerator} in a background
     * thread, publishing all generated combinations.
     */
    private class GeneratorWorker extends SwingWorker<Void, Set<Integer>> {

        /** The sum. */
        private int sum;

        /** The length. */
        private int length;

        /** Constructs a generator for given sum and length. */
        public GeneratorWorker(final int sum, final int length) {
            super();
            this.sum = sum;
            this.length = length;
        }

        /**
         * Called from the background thread to do the actual generation.
         * It creates a "real" generator,
         * sets it up through {@code setMaxNumber()}
         * and {@code setObserver()}, and runs it.
         */
        @Override
        protected Void doInBackground() throws Exception {
            final KakuroCombinationGenerator generator;
//# BEGIN TODO Implementation of doInBackground()
            generator = new KakuroCombinationGenerator();
            generator.setMaxNumber(maxNumber);
            generator.setObserver(observer);
            generator.generate(sum, length);
//# END TODO
            return null;
        }

        /**
         * Called in the proxy thread, in response to calls
         * of publish() in the background thread.
         * Possibly, a sequence of calls to publish() is combined
         * into a single call of process();
         * hence, the List parameter of process().
         * <p>
         * Only, needed when the "real" generator uses {@code process}
         * to communicate combination to the proxy.
         */
        @Override
        protected void process(List<Set<Integer>> chunks) {
//# BEGIN TODO Implementation of process(), if needed
// Replace this line
//# END TODO
        }

        /**
         * Called in the proxy thread after the background thread ends.
         * If a resultListener is set,
         * then its {@code generationDone()} is called.
         */
        @Override
        protected void done() {
//# BEGIN TODO Implementation of done()
            if (resultListener != null) {
                // Abort
                if (this.isCancelled()) {
                    resultListener.generationDone(new InterruptedException("Canceled"));
                }

                // Generation done
                resultListener.generationDone(null);
            }
//# END TODO
        }

    } // End of class GeneratorWorker

    /**
     * The generator worker.
     * Note: It is not defined local to {@code generate()},
     * because {@code abort()} may need to access it as well.
     *
     */
    private GeneratorWorker generatorWorker;

    /**
     * Generates Kakuro combinations with given sum and length,
     * signaling a registered observer.
     * It creates a generator worker and starts it,
     * returning asynchronously.
     *
     * @param s  the given sum
     * @param n   the given length
     */
    @Override
    public void generate(int s, int n) {
//# BEGIN TODO Implementation of generate(int, int)
        generatorWorker = new GeneratorWorker(s, n);
        generatorWorker.execute();
//# END TODO
    }

    /**
     * Aborts the background thread.
     */
    public void abort() {
//# BEGIN TODO Implementation of abort()
        generatorWorker.cancel(true);
//# END TODO
    }

}
