package fr.bxcchus.api.services;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.Skin;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static fr.bxcchus.utils.Constant.OWNED_SKINS_URL;

public interface SkinService extends IClient {
    default CompletableFuture<Skin[]> getSkins() throws IOException {
        return requestAsync(OWNED_SKINS_URL, HttpMethod.GET, null, Skin[].class);
    }
}
