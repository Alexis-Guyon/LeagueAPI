package fr.bxcchus.api.services;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.Champion;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static fr.bxcchus.utils.Constant.OWNED_CHAMPIONS_URL;

public interface ChampionService extends IClient {
    default CompletableFuture<Champion[]> getChampions() throws IOException {
        return requestAsync(OWNED_CHAMPIONS_URL, HttpMethod.GET, null, Champion[].class);
    }
}
