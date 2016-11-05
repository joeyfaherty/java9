import java.io.IOException;
import java.util.stream.Stream;

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
        long pid = current.getPid();
        System.out.println("Current pid is " + pid);
    }

    private static void getProcessInformation() {
        ProcessHandle currentProcess = ProcessHandle.current();

        // get info from current process
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
        Stream<ProcessHandle> processHandleStream = ProcessHandle.allProcesses();
        System.out.println("List of all current processes are : ");
        processHandleStream.forEach(System.out::println);
    }

    private static void printProcessesThatHaveChildren() {
        ProcessHandle.allProcesses()
                // keep only elements that have at least one child
                .filter(e -> e.children().count() >= 1)
                .forEach(System.out::println);
    }
}
