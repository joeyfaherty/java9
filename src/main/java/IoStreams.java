import java.io.ByteArrayInputStream;
import java.io.IOException;

public class IoStreams {

    public static void main(String[] args) throws IOException {
        // get resource as stream of bytes
        byte[] bytes = IoStreams.class.getResourceAsStream("test.txt").readAllBytes();

        new ByteArrayInputStream(bytes).transferTo(System.out);
    }

}
