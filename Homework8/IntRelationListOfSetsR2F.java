import java.util.Iterator;

/**
 * Version of {@link IntRelationListOfSets}, that allows iteration
 * over all related integers to a given first integer.
 *
<!--//# BEGIN TODO Name, group id, and date-->
Serousov Vitaly, 201 SE, 11.11.2014
<!--//# END TODO-->
*/
// -----8<----- cut line -----8<-----
public class IntRelationListOfSetsR2F extends IntRelationListOfSets
        implements IntRelationExtra {

    public IntRelationListOfSetsR2F(final int n) {
        super(n);
    }

    @Override
    public Iterable<Integer> relatedToFirst(final int a) {
//# BEGIN TODO Robust implementation of relatedToFirst
        // Precondition
        if (!isValid(a)) {
            throw new IllegalArgumentException("Precondition violated");
        }

        // Return object of anonymous class
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return relation.get(a).iterator();
            }
        };
//# END TODO
    }

}
