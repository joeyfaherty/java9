import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by joey on 11/5/16.
 */
public class Http2 {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ExecutionException {

        simpleBlockingSynchronousHttpClient();

        aSynchronousHttpClient();

    }

    private static void simpleBlockingSynchronousHttpClient() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse response = HttpRequest
                .create(new URI("https://github.com/joeyfaherty"))
                // specify the method type
                .GET()
                // send the request. This blocks, until the response
                // had been received and stored into response variable
                .response();
        int statusCode = response.statusCode();
        String body = response.body(HttpResponse.asString());
        System.out.println(String.format("Status code is: %s \n \n %s", statusCode, body));
    }

    private static void aSynchronousHttpClient() throws URISyntaxException, InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse> future = HttpRequest
                .create(new URI("https://github.com/joeyfaherty"))
                .GET()
                // this returns a CompletableFutureApi
                .responseAsync();

        // block until complete
        future.get();

        if (future.isDone()) {
            HttpResponse response = future.get();
            int statusCode = response.statusCode();
            String body = response.body(HttpResponse.asString());
            System.out.println(String.format("Status code is: %s \n \n %s", statusCode, body));
        }
    }
}
