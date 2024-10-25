package fr.bxcchus.methods;

import fr.bxcchus.api.services.GameService;
import fr.bxcchus.objects.GameFlow;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class MatchMethod implements GameService {

    @Override
    public CompletableFuture<GameFlow[]> acceptMatchIfReady() throws IOException {
        return GameService.super.acceptMatchIfReady();
    }

    @Override
    public CompletableFuture<GameFlow[]> pickChampion(int championId) throws IOException {
        return GameService.super.pickChampion(championId);
    }
}
