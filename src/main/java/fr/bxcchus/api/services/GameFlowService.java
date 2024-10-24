package fr.bxcchus.api.services;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.GameFlow;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface GameFlowService extends IClient {

    default String getGameFlowPhase() throws IOException {
        String url = "lol-gameflow/v1/gameflow-phase";
        return request(url, HttpMethod.GET, null, String.class);
    }

    default CompletableFuture<GameFlow[]> autoAcceptMatch() throws IOException {
        String phase = getGameFlowPhase();
        if ("ReadyCheck".equals(phase)) {
            String acceptUrl = "lol-matchmaking/v1/ready-check/accept";
            return requestAsync(acceptUrl, HttpMethod.POST, null, GameFlow[].class);
        }
        return CompletableFuture.completedFuture(null);
    }
}
