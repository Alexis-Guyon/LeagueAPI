package fr.bxcchus.api.services;

import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.Skin;

import java.io.IOException;
import java.util.List;

public interface SkinService extends IClient {
    default List<Skin> getSkins() throws IOException {
        String url = "https://127.0.0.1:29518/lol-champions/v1/owned-skins";
        return List.of(get(url, Skin[].class));
    }
}
