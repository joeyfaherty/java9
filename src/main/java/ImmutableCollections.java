import java.util.List;
import java.util.Set;

/**
 * Utility methods for easily creating immutable collections in Java9
 */
public class ImmutableCollections {

    public static void main(String[] args) {
        //Empty List Example
        List emptyImmutableList = List.of();
        // will throw a UnsupportedOperationException (Runtime) exception
        // as we cannot add to this immutable list
        emptyImmutableList.add("a");

        //Non-Empty List Example
        List immutableList = List.of("a", "b", "c");
        // will throw exception
        immutableList.add("d");

        Set<Integer> immutableSet = Set.of(1, 2, 3);
    }

}
