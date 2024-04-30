package fr.bxcchus.methods;

import fr.bxcchus.api.services.SkinService;
import fr.bxcchus.objects.Skin;

import java.io.IOException;
import java.util.List;

public class SkinMethod implements SkinService {
    @Override
    public List<Skin> getSkins() throws IOException {
        return SkinService.super.getSkins();
    }
}
