package fr.bxcchus.api.services;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.api.IClient;

import java.io.IOException;

public interface MatchService extends IClient {
    default Object findMatch() throws IOException {
        String url = "lol-lobby/v2/lobby/matchmaking/search";
        return request(url, HttpMethod.POST, null, null);
    }

}
