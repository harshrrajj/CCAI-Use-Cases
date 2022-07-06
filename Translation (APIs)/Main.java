//Google cloud's Translate API (using RapidAPI)

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException{
        String text = "Yo vivo en Granada, una ciudad pequeña que tiene monumentos muy importantes como " +
                "la Alhambra. Aquí la comida es deliciosa y son famosos el gazpacho, el rebujito y el salmorejo.";
        String langTo = "en";
        String langFrom = "es";
        String bodyVal = "q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "application/gzip")
                .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "4e1ad2558cmshc0b0c237638fe5fp1ebe33jsn85534bb14973")
                .method("POST", HttpRequest.BodyPublishers.ofString(bodyVal))
                .build();
        var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
