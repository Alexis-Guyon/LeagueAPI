package fr.bxcchus.methods;

import fr.bxcchus.api.services.SkinService;
import fr.bxcchus.objects.Skin;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class SkinMethod implements SkinService {
    @Override
    public CompletableFuture<Skin[]> getSkins() throws IOException {
        return SkinService.super.getSkins();
    }
}
