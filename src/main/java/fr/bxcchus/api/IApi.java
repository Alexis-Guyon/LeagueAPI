package fr.bxcchus.api;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import fr.bxcchus.objects.Champion;
import fr.bxcchus.objects.Skin;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IApi {
    public interface LolApi {

        OkHttpClient getUnsafeOkHttpClient();

        String getBasicAuth();

        default JSONObject get(String url) throws IOException {
            OkHttpClient client = getUnsafeOkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Basic " + getBasicAuth())
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Erreur lors de la récupération des données: " + response.code());
                }
                return new JSONObject(response.body().string());
            }
        }

        default List<Champion> getChampions() throws IOException {
            JSONObject response = get("https://127.0.0.1:2999/lol-champions/v1/owned-champions-minimal");
            JSONArray championsJson = response.getJSONArray("champions");
            List<Champion> champions = new ArrayList<>();
            for (int i = 0; i < championsJson.length(); i++) {
                JSONObject championJson = championsJson.getJSONObject(i);
                Champion champion = new Champion();
                champion.setName(championJson.getString("id"));
                champion.setImageUrl("http://ddragon.leagueoflegends.com/cdn/12.1.1/img/champion/" + champion.getName() + ".png");
                champions.add(champion);
            }
            return champions;
        }

        default List<Skin> getSkins() throws IOException {
            JSONObject response = get("https://127.0.0.1:2999/lol-champions/v1/owned-skins");
            JSONArray skinsJson = response.getJSONArray("skins");
            List<Skin> skins = new ArrayList<>();
            for (int i = 0; i < skinsJson.length(); i++) {
                JSONObject skinJson = skinsJson.getJSONObject(i);
                Skin skin = new Skin();
                skin.setName(skinJson.getString("id"));
                skin.setChampionName(skinJson.getString("championId"));
                skin.setImageUrl("http://ddragon.leagueoflegends.com/cdn/12.1.1/img/champion/" + skin.getChampionName() + "/" + skin.getName() + ".png");
                skins.add(skin);
            }
            return skins;
        }

        /* default List<Match> getRecentMatches() throws IOException {
            JSONObject response = get("https://127.0.0.1:2999/lol-match-history/v1/products/lol/matches/recent");
            JSONArray matchesJson = response.getJSONArray("matches");
            List<Match> matches = new ArrayList<>();
            for (int i = 0; i < matchesJson.length(); i++) {
                JSONObject matchJson = matchesJson.getJSONObject(i);
                Match match = new Match();
                match.setGameId(matchJson.getLong("gameId"));
                match.setGameMode(matchJson.getString("gameMode"));
                match.setGameType(matchJson.getString("gameType"));
                match.setGameDuration(matchJson.getInt("gameDuration"));
                match.setGameCreation(matchJson.getLong("gameCreation"));
                matches.add(match);
            }
            return matches;
        }*/

        // Ajoutez d'autres méthodes ici pour récupérer d'autres informations de l'API LCU
    }}
