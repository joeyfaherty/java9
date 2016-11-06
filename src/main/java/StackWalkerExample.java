import java.util.List;
import java.util.stream.Collectors;

/**
 * A stack walker. StackWalker is thread-safe.
 * Multiple threads can share a single StackWalker object to traverse its own stack
 */
public class StackWalkerExample {


    public static void main(String[] args) {
        methodA();
    }

    private static void methodA() {
        methodB();
    }

    private static void methodB() {
        methodC();
    }

    private static void methodC() {
        methodD();
    }

    private static void methodD() {
        // Snapshot the top 10 stack frames of the current thread
        List<StackWalker.StackFrame> stack = StackWalker.getInstance().walk(s ->
                s.limit(10).collect(Collectors.toList()));
        // print each li
        stack.forEach(e -> System.out.println("Class: " + e.getFileName() + " with line number: "
                + e.getLineNumber() + " in method: " + e.getMethodName()));

    }
}
