import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException{

        String authenticationToken = "Bearer " + conversationSummarizerStep1.tokenReturner();
        // Here, Conversation ID obtained from conversationSummarizerStep2.java = 4886478072053760.
        // This has been used below in line-14 to form the API link. Please note that this value must be changed according
        // to what obtained on running conversationSummarizerStep2.java. Hence, the conversation ID in the line-14's API link must be changed everytime.
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.symbl.ai/v1/conversations/4886478072053760/summary"))
                .header("content-type", "application/json")
                .header("Authorization", authenticationToken)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        String jsonResponse = response.body();
        System.out.println(summaryReturn(jsonResponse));

    }
    static String summaryReturn(String response) throws IOException, InterruptedException{
        int i=0;
        String summaryResponse = "";
        while(true) {
            while (i < (response.length() - 6)) {
                if ((response.charAt(i) == '"') && (response.charAt(i + 1) == 't') && (response.charAt(i + 2) == 'e') && (response.charAt(i + 3) == 'x') && (response.charAt(i + 4) == 't') && (response.charAt(i + 5) == '"') && (response.charAt(i + 6) == ':')) {
                    i = i + 8;
                    while (response.charAt(i) != '"') {
                        summaryResponse += response.charAt(i);
                        i++;
                    }
                    summaryResponse += ' ';
                    break;
                }
                i += 1;
            }
            if(i >= (response.length() - 6)){
                break;
            }
        }
        return summaryResponse;
    }
}
