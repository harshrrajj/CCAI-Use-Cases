import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://text-analysis12.p.rapidapi.com/summarize-text/api/v1.1"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Host", "text-analysis12.p.rapidapi.com")
                .header("X-RapidAPI-Key", "9a0f4db895msha36669ebb7b5f97p1bdb96jsne28ac6f17310")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"language\": \"english\",\r\n    \"summary_percent\": 20,\r\n    \"text\": \"Good morning. I'd like to book a flight to Toronto, please. Yes. On Thursday, please. I've heard that Air Canada is good, but expensive. Is there a big difference in prices between the airlines? I'll be flying business class. Wow, that's quite a difference in prices. I'm returning on from Wednesday 22nd. So could you check the return prices for me? Okay, before I make a decision, I would like to know the departure and arrival times, in both directions. Oh! That doesn't sound very convenient. Flying by Pacific, I mean. How about the returned flights? No, it isn't. No, it isn't. Are the stopovers the same as on the outward journey? Well, I have a business appointment on Wednesday the 22nd, in the morning. So, unfortunately I can't take the Qantas flight. The Pacific flight times are too inconvenient. So I'll take the Air Canada flight even though it's more expensive. Yes, I do. Here, you are. Yes. I have a company credit card. Here, you are. Oh, my name is Reece with a C not with an S. And my given names are hyphenated. Mary hyphen an with an e. Oh, you've got that. Sorry. Yes, could you give me an official invoice, please? I need it for my company. Yes. Thank you.\"\r\n}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}