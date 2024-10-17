package fr.bxcchus.api.services;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.Match;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface MatchService extends IClient {
    default CompletableFuture<Match[]> findMatch() throws IOException {
        String url = "lol-lobby/v2/lobby/matchmaking/search";
        return requestAsync(url, HttpMethod.POST, null, Match[].class);
    }

}
