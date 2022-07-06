import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wordcab.com/api/v1/summaries/narrative_summary_cb262qshdj7QkMixo83fJfimNAWGwKgK"))  // Note that 'narrative_summary_cb262qshdj7QkMixo83fJfimNAWGwKgK' used in the API link in this line is the summary ID obtained on running summaryIdCreation.java. It must be changed everytime for a different summary ID created using summaryIdCreation.java
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 444ac6a31e079bc359b70ab361d7cb66cad0bf2f")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}