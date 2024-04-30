package fr.bxcchus.api.services;

import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.Champion;

import java.io.IOException;
import java.util.List;

import static fr.bxcchus.utils.Constant.OWNED_CHAMPIONS_URL;

public interface ChampionService extends IClient {
    default List<Champion> getChampions() throws IOException {
        return List.of(get(OWNED_CHAMPIONS_URL, Champion[].class));
    }
}
