package fr.bxcchus.methods;

import fr.bxcchus.api.services.GameFlowService;
import fr.bxcchus.api.services.MatchService;
import fr.bxcchus.objects.GameFlow;
import fr.bxcchus.objects.GameSession;
import fr.bxcchus.objects.Match;
import fr.bxcchus.objects.Session;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class MatchMethod implements MatchService, GameFlowService {

    @Override
    public String getGameFlowPhase() throws IOException {
        return GameFlowService.super.getGameFlowPhase();
    }

    @Override
    public CompletableFuture<GameFlow[]> autoAcceptMatch() throws IOException {
        return GameFlowService.super.autoAcceptMatch();
    }

    @Override
    public CompletableFuture<Match[]> findMatch() throws IOException {
        return MatchService.super.findMatch();
    }

    @Override
    public CompletableFuture<GameFlow[]> pickChampion(int championId) throws IOException {
        return GameFlowService.super.pickChampion(championId);
    }

    @Override
    public Session getSession() throws IOException {
        return GameFlowService.super.getSession();
    }

    @Override
    public GameSession getSessionMatch() throws IOException {
        return GameFlowService.super.getSessionMatch();
    }
}
