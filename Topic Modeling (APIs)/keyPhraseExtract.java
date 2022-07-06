import com.azure.core.credential.AzureKeyCredential;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.TextAnalyticsClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class keyPhraseExtract {

    private static String key = "ffde05cd14ec42059130340c7c99ba45";
    private static String endpoint = "https://harshraj.cognitiveservices.azure.com/";

    public static void main(String[] args) throws IOException, InterruptedException {
        TextAnalyticsClient client = authenticateClient(key, endpoint);
        extractKeyTopics(client);
    }
    // Method to authenticate the client object with your key and endpoint
    static TextAnalyticsClient authenticateClient(String key, String endpoint) {
        return new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }
    // Method for extracting key phrases from text
    static void extractKeyTopics (TextAnalyticsClient client) throws IOException, InterruptedException
    {
        // The text to be analyzed
        String text = new String(Files.readAllBytes(Paths.get("R:\\Sample Conversation.txt")));

        System.out.printf("Key topics: %n");
        var count = 0;
        for (String keyPhrase : client.extractKeyPhrases(text)) {
            System.out.printf("%s%n", keyPhrase);
            //Since the key phrases extracted are printed in the order of importance/relevance,
            //we can customize the number of phrases we want with the below piece of code.

            count = count+1;
            if(count==3){
                break;
            }

        }
    }
}