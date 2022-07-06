//Text analysis' Text-sentiment-analysis API (RapidAPI)

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException{
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://text-analysis12.p.rapidapi.com/sentiment-analysis/api/v1.1"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Host", "text-analysis12.p.rapidapi.com")
                .header("X-RapidAPI-Key", "4e1ad2558cmshc0b0c237638fe5fp1ebe33jsn85534bb14973")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"language\": \"english\",\r\n    \"text\": \"Hi, I tried to set up wifi connection" +
                        " for Smart Brew 300 espresso machine, but it didn’t work." +
                        " Yes, I pushed the wifi connection button, and now the power light is slowly blinking." +
                        " No. Nothing happened.\"\r\n}"))
                .build();
        var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
