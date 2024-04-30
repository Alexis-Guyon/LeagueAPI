package fr.bxcchus.methods;

import fr.bxcchus.api.services.SummonerService;
import fr.bxcchus.objects.Summoner;

import java.io.IOException;

public class SummonerMethod implements SummonerService {
    @Override
    public Summoner getSummoner() throws IOException {
        return SummonerService.super.getSummoner();
    }
}
