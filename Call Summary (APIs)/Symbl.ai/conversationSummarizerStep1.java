import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class conversationSummarizerStep1{

    public static void main(String[] args) throws IOException, InterruptedException{
        String token = tokenReturner();
        System.out.println(token);
    }

    static String tokenReturner() throws IOException, InterruptedException{
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.symbl.ai/oauth2/token:generate"))
                .header("content-type", "application/json")
                .header("accept", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\n" +
                        "      \"type\" : \"application\",\n" +
                        "      \"appId\": \"436d774d475a36364363434c34676547796f786a35654d78356558626770376e\",\n" +
                        "      \"appSecret\": \"642d6676475559637544753858436b7970415747375f72573241382d394a314f6c4a4c77696e35396a37646c44663546345843464a74666543575865624d3456\"\n" +
                        "    }"))
                .build();
        var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String collectToken = response.body();
        collectToken = collectToken.substring(1, collectToken.length()-1);
        String[] allKeyValuePairs = collectToken.split(",");
        String accessToken = "";

        for(String eachPair: allKeyValuePairs){
            String[] keyValueSplit = eachPair.split(":");
            accessToken = keyValueSplit[1];
            break;
        }
        accessToken = accessToken.substring(1, accessToken.length()-1);
        return accessToken;
    }
}
