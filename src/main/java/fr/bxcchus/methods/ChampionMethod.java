package fr.bxcchus.methods;

import fr.bxcchus.api.services.ChampionService;
import fr.bxcchus.objects.Champion;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ChampionMethod implements ChampionService {
    @Override
    public CompletableFuture<Champion[]> getChampions() throws IOException {
        return ChampionService.super.getChampions();
    }
}
