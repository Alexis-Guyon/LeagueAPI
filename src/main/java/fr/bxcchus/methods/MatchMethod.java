package fr.bxcchus.methods;

import fr.bxcchus.api.services.MatchService;
import fr.bxcchus.objects.Match;

import java.io.IOException;

public class MatchMethod implements MatchService {
    @Override
    public Match[] findMatch() throws IOException {
        return MatchService.super.findMatch();
    }
}
