package fr.bxcchus.api.services;

import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.Match;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface MatchService extends IClient {
    default List<Match> getRecentMatches() throws IOException {
        String url = "https://127.0.0.1:29518/lol-champions/v1/inventories/ID/skins-minimal";
        return Arrays.asList(get(url, Match[].class));
    }
}
