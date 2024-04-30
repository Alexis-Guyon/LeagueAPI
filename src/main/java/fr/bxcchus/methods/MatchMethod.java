package fr.bxcchus.methods;

import fr.bxcchus.api.services.MatchService;

import java.io.IOException;

public class MatchMethod implements MatchService {
    @Override
    public Object findMatch() throws IOException {
        return MatchService.super.findMatch();
    }
}
