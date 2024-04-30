package fr.bxcchus.api.services;

import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.Summoner;

import java.io.IOException;

import static fr.bxcchus.utils.Constant.CURRENT_SUMMONER_URL;

public interface SummonerService extends IClient {
    default Summoner getSummoner() throws IOException {
        return get(CURRENT_SUMMONER_URL, Summoner.class);
    }
}
