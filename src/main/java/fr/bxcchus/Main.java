package fr.bxcchus;

import fr.bxcchus.utils.Constant;
import fr.bxcchus.utils.LockfileParser;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import static fr.bxcchus.utils.Client.getUnsafeOkHttpClient;
import static fr.bxcchus.utils.Constant.BASE_URL;

public class Main {
    public static void main(String[] args) throws IOException {
        LockfileParser parser = LockfileParser.getInstance();

        if (parser != null) {
            System.out.println(parser.getPassword());
            System.out.println(parser.getPort());
            // Générer l'authentification de base
            String user = "riot";
            String password = parser.getPassword();
            String basicAuth = Base64.getEncoder().encodeToString((user + ":" + password).getBytes());

            // Créer la requête
            OkHttpClient client = getUnsafeOkHttpClient();
            Request request = new Request.Builder()
                    .url(BASE_URL + parser.getPort() + "/lol-lobby/v2/lobby/matchmaking/search")
                    .method("POST", new RequestBody() {
                        @Nullable
                        @Override
                        public MediaType contentType() {
                            return null;
                        }

                        @Override
                        public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {

                        }
                    })
                    .addHeader("Authorization", "Basic " + basicAuth)
                    .build();

            // Exécuter la requête
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());

        }
    }


}