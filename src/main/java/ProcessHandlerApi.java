import java.io.IOException;
import java.util.stream.Stream;

/**
 * ProcessHandle identifies and provides control of native processes. Each individual process
 * can be monitored for liveness, list its children, get information about the process or destroy it.
 *
 * http://download.java.net/java/jdk9/docs/api/java/lang/ProcessHandle.html
 */
public class ProcessHandlerApi {

    public static void main(String[] args) throws IOException {
        getProcessInformation();

        getPidOfCurrentProcess();

        executeCommand();

        getAllProcesses();

        printProcessesThatHaveChildren();
    }

    private static void getPidOfCurrentProcess() throws IOException {
        ProcessHandle current = ProcessHandle.current();
        // Returns the native process ID of the process
        long pid = current.getPid();
        System.out.println("Current pid is " + pid);
    }

    private static void getProcessInformation() {
        ProcessHandle currentProcess = ProcessHandle.current();
        // Information snapshot about the process.
        // The attributes of a process vary by operating system and are not available in all implementations
        // Return types are all Optional<T>
        ProcessHandle.Info processInfo = currentProcess.info();
        System.out.println(String.format("Process [%s] started at [%s] by user [%s] " +
                        "from path [%s] took [%s] milliseconds to execute",
                currentProcess.getPid(),
                processInfo.startInstant().get(),
                processInfo.user().get(),
                processInfo.command().get(),
                processInfo.totalCpuDuration().get().toMillis())
        );
    }

    private static void executeCommand() throws IOException {
        Process exec = Runtime.getRuntime().exec("mkdir yyy");
        System.out.println("Created pid " + exec.getPid());
    }

    private static void getAllProcesses() {
        // Returns a snapshot of all processes visible to the current process.
        Stream<ProcessHandle> processHandleStream = ProcessHandle.allProcesses();
        System.out.println("List of all current processes are : ");
        processHandleStream.forEach(System.out::println);
    }

    private static void printProcessesThatHaveChildren() {
        // e.children() returns a snapshot of the current direct children of the process.
        ProcessHandle.allProcesses()
                // keep only elements that have at least one child
                .filter(e -> e.children().count() >= 1)
                .forEach(System.out::println);
    }
}
