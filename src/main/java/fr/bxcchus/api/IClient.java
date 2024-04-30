package fr.bxcchus.api;

import com.google.gson.Gson;
import fr.bxcchus.utils.LockfileParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import static fr.bxcchus.utils.Constant.BASE_URL;
import static fr.bxcchus.utils.Constant.USER;

public interface IClient {
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

    default <T> T get(String url, Class<T> responseType) throws IOException {
        OkHttpClient client = getUnsafeOkHttpClient();
        LockfileParser parser = LockfileParser.getInstance();
        if (parser != null) {
            System.out.println(parser.getPort());
            Request request = new Request.Builder()
                    .url(BASE_URL + parser.getPort() + "/" + url)
                    .addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((USER + ":" + parser.getPassword()).getBytes()))
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Erreur lors de la récupération des données: " + response.code());
                }
                String jsonResponse = response.body().string();
                Gson gson = new Gson();
                return gson.fromJson(jsonResponse, responseType);
            }
        }
        throw new NullPointerException("Erreur lors de la récupération du Lockfile.");
    }
}
