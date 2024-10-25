package fr.bxcchus.bot.api;

import com.google.gson.Gson;
import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.utils.LockfileParser;
import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

import static fr.bxcchus.utils.Constant.BASE_URL;
import static fr.bxcchus.utils.Constant.USER;

public interface IBot {
    LockfileParser parser = LockfileParser.getInstance();
    default OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that trusts all certificates
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an OkHttpClient with the all-trusting trust manager
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, port) -> true);

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    default <T> CompletableFuture<T[]> requestAsync(String url, HttpMethod method, Object requestBody, Class<T[]> responseType) {
        return CompletableFuture.supplyAsync(() -> executeRequest(url, method, requestBody, responseType));
    }

    // Méthode request pour les requêtes synchrones
    default <T> T request(String url, HttpMethod method, Object requestBody, Class<T> responseType)  {
        return executeRequest(url, method, requestBody, responseType);
    }

    // Méthode privée pour exécuter les requêtes
    private <T> T executeRequest(String url, HttpMethod method, Object requestBody, Class<T> responseType) {
        OkHttpClient client = getUnsafeOkHttpClient();
        Gson gson = new Gson();
        Request.Builder requestBuilder = new Request.Builder()
                .url(BASE_URL + 2999 + "/" + url)
                .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((USER + ":" + parser.getPassword()).getBytes()))
                .addHeader("Content-Type", "application/json"); // Ajout du header Content-Type

        // Crée un corps structuré pour la requête PATCH
        if (method == HttpMethod.PATCH) {
            // S'assurer que requestBody est un objet structuré (par exemple, une map ou un modèle spécifique)
            String jsonBody = gson.toJson(requestBody);  // Cette ligne crée une structure JSON correctement formée
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            requestBuilder.patch(body);
        } else if (method == HttpMethod.GET) {
            requestBuilder.get();
        } else if (method == HttpMethod.POST) {
            String jsonBody = gson.toJson(requestBody);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            requestBuilder.post(body);
        } else {
            throw new IllegalArgumentException("Méthode de requête non prise en charge: " + method);
        }

        Request request = requestBuilder.build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // Affichage détaillé de la réponse d'erreur pour le débogage
                String errorResponse = response.body() != null ? response.body().string() : "Aucune réponse d'erreur disponible.";
                System.out.println("Erreur lors de la récupération des données: " + url + " " + response.code() + " - " + errorResponse);
                throw new IOException("Erreur lors de la récupération des données: " + url + " " + response.code());
            }
            String jsonResponse = response.body() != null ? response.body().string() : "Le corps de la réponse est vide ou null.";
            return gson.fromJson(jsonResponse, responseType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erreur lors de la requête", e);
        }
    }

}
