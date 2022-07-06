import okhttp3.*;

import java.io.File;
import java.io.IOException;

public class jobCreation {
    public static void main(String[] args) throws IOException, InterruptedException{
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse("text/plain");

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("transcript","Sample conversation - 2.txt",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("R:\\Sample conversation - 2.txt")))
                .build();

        Request request = new Request.Builder()
                .url("https://wordcab.com/api/v1/summarize?source=generic&summary_type=narrative&summary_lens=3&split_long_utterances=true&display_name=Harsh Cisco Example")
                .method("POST", body)
                .addHeader("Authorization", "Bearer 444ac6a31e079bc359b70ab361d7cb66cad0bf2f")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}