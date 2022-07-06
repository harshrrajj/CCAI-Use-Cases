import com.azure.core.credential.AzureKeyCredential;
import com.azure.ai.textanalytics.models.*;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.TextAnalyticsClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class sentimentAnalyzeAgent {

    private static String key = "ffde05cd14ec42059130340c7c99ba45";
    private static String endpoint = "https://harshraj.cognitiveservices.azure.com/";

    public static void main(String[] args) throws IOException, InterruptedException {
        TextAnalyticsClient client = authenticateClient(key, endpoint);
        sentimentAnalysis(client);
    }
    // Method to authenticate the client object with your key and endpoint
    static TextAnalyticsClient authenticateClient(String key, String endpoint) {
        return new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildClient();
    }
    // Example method for sentiment in text
    static void sentimentAnalysis(TextAnalyticsClient client) throws IOException, InterruptedException
    {
        // The text that needs to be analyzed (from the agent side)
        String agentText = new String(Files.readAllBytes(Paths.get("Agent Conversation.txt")));

        DocumentSentiment documentSentiment = client.analyzeSentiment(agentText);
        System.out.printf(
                "Analyzed total sentiment of the Agent: %s, positive score: %s, neutral score: %s, negative score: %s.%n",
                documentSentiment.getSentiment(),
                documentSentiment.getConfidenceScores().getPositive(),
                documentSentiment.getConfidenceScores().getNeutral(),
                documentSentiment.getConfidenceScores().getNegative());

        for (SentenceSentiment sentenceSentiment : documentSentiment.getSentences()) {
            System.out.printf(
                    "Analyzed sentence sentiment: %s, positive score: %s, neutral score: %s, negative score: %s.%n",
                    sentenceSentiment.getSentiment(),
                    sentenceSentiment.getConfidenceScores().getPositive(),
                    sentenceSentiment.getConfidenceScores().getNeutral(),
                    sentenceSentiment.getConfidenceScores().getNegative());
        }
    }

}
