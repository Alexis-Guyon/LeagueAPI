package fr.bxcchus.api.services.impl;

import fr.bxcchus.api.services.GameService;
import fr.bxcchus.objects.GameFlow;
import java.util.concurrent.CompletableFuture;
import java.io.IOException;

public class GameServiceImpl implements GameService {

    @Override
    public CompletableFuture<GameFlow[]> acceptMatchIfReady() throws IOException {
        return GameService.super.acceptMatchIfReady();
    }

    @Override
    public CompletableFuture<GameFlow[]> pickChampion(int championId) throws IOException {
        return GameService.super.pickChampion(championId);
    }
}
