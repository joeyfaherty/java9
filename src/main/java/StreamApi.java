import java.util.stream.Stream;

public class StreamApi {

    /**
     * takeWhile() & dropWhile() take a predicate as an argument and returns a
     * Stream of subset of the given values which satisfy that predicate
     */
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
                .takeWhile(e -> e < 4)
                .forEach(System.out::println); // prints 1, 2, 3

        Stream.of(1, 2, 3, 4, 5)
                .dropWhile(e -> e < 4)
                .forEach(System.out::println); // prints 4, 5
    }
}
