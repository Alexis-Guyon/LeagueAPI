package fr.bxcchus.methods;

import fr.bxcchus.api.services.GameFlowService;
import fr.bxcchus.api.services.MatchService;
import fr.bxcchus.objects.GameFlow;
import fr.bxcchus.objects.Match;

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
    public CompletableFuture<GameFlow[]> pickChampion(int actionId, int championId) throws IOException {
        return GameFlowService.super.pickChampion(actionId,championId);
    }

}
