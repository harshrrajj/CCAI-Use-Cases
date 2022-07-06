import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class conversationSummarizerStep2{

    public static void main(String[] args) throws IOException, InterruptedException{
        String convID = conversationIDGenerator();
        System.out.println(convID);
    }

    static String conversationIDGenerator() throws IOException, InterruptedException{

        String authenticationToken = "Bearer " + conversationSummarizerStep1.tokenReturner();

        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.symbl.ai/v1/process/text"))
                .header("content-type", "application/json")
                .header("Authorization", authenticationToken)
                .method("POST", HttpRequest.BodyPublishers.ofString("{\n" +
                        "  \"name\": \"Business Meeting\",\n" +
                        "  \"detectPhrases\": \"true\",\n" +
                        "  \"enableSummary\": \"true\",\n" +
                        "  \"detectEntities\": \"true\",\n" +
                        "  \"confidenceThreshold\": 0.6,\n" +
                        "  \"messages\": [{\n" +
                        "    \"payload\": {\n" +
                        "      \"content\": \"Hello, you’re chatting with Rene. How may I help you?\"\n" +
                        "    },\n" +
                        "    \"from\": {\n" +
                        "\t\"userId\": \"agent@abccorp.com\",\n" +
                        "\t\"name\": \"Agent\"\n" +
                        "    }\n" +
                        "  }, {\n" +
                        "    \"payload\": {\n" +
                        "      \"content\": \"Hi, I tried to set up wifi connection for Smart Brew 300 espresso machine, but it didn’t work.\"\n" +
                        "    },\n" +
                        "    \"from\": {\n" +
                        "\t\"userId\": \"customer@example.com\",\n" +
                        "\t\"name\": \"Customer\"\n" +
                        "    }\n" +
                        "\t}, {\n" +
                        "    \"payload\": {\n" +
                        "      \"content\": \"I’m sorry to hear that. Let’s see what we can do to fix this issue. Could you please try the following steps for me? First, could you push the wifi connection button, hold for 3 seconds, then let me know if the power light is slowly blinking on and off every second?\"\n" +
                        "    },\n" +
                        "    \"from\": {\n" +
                        "\t\"userId\": \"agent@abccorp.com\",\n" +
                        "\t\"name\": \"Agent\"\n" +
                        "    }\n" +
                        "   }, {\n" +
                        "    \"payload\": {\n" +
                        "      \"content\": \"Yes, I pushed the wifi connection button, and now the power light is slowly blinking.\"\n" +
                        "    },\n" +
                        "    \"from\": {\n" +
                        "\t\"userId\": \"customer@example.com\",\n" +
                        "\t\"name\": \"Customer\"\n" +
                        "    }\n" +
                        "\t}, {\n" +
                        "    \"payload\": {\n" +
                        "      \"content\": \"Great. Thank you! Now, please check in your Contoso Coffee app. Does it prompt to ask you to connect with the machine?\"\n" +
                        "    },\n" +
                        "    \"from\": {\n" +
                        "\t\"userId\": \"agent@abccorp.com\",\n" +
                        "\t\"name\": \"Agent\"\n" +
                        "    }\n" +
                        "  }, {\n" +
                        "    \"payload\": {\n" +
                        "      \"content\": \"No. Nothing happened.\"\n" +
                        "    },\n" +
                        "    \"from\": {\n" +
                        "\t\"userId\": \"customer@example.com\",\n" +
                        "\t\"name\": \"Customer\"\n" +
                        "    }\n" +
                        "\t}, {\n" +
                        "    \"payload\": {\n" +
                        "      \"content\": \"I’m very sorry to hear that. Let me see if there’s another way to fix the issue. Please hold on for a minute.\"\n" +
                        "    },\n" +
                        "    \"from\": {\n" +
                        "\t\"userId\": \"agent@abccorp.com\",\n" +
                        "\t\"name\": \"Agent\"\n" +
                        "    }\n" +
                        "  }]\n" +
                        "}"))
                .build();
        //The 'Conversation for Summarization.txt' file has been used to put the text body above. Similarly, it can be tested on 'Conversation for Summarization - 2.txt'
        var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String collectID = response.body();
        collectID = collectID.substring(1, collectID.length()-1);
        String[] allKeyValuePairs = collectID.split(",");
        String conversationID = "";

        for(String eachPair: allKeyValuePairs){
            String[] keyValueSplit = eachPair.split(":");
            conversationID = keyValueSplit[1];
            break;
        }
        conversationID = conversationID.substring(1, conversationID.length()-1);
        return conversationID;
    }
}
