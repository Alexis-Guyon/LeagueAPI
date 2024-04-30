package fr.bxcchus.api.services;

import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.Skin;

import java.io.IOException;
import java.util.List;

import static fr.bxcchus.utils.Constant.OWNED_SKINS_URL;

public interface SkinService extends IClient {
    default List<Skin> getSkins() throws IOException {
        return List.of(get(OWNED_SKINS_URL, Skin[].class));
    }
}
