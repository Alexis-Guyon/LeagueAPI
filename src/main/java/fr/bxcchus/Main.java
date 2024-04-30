package fr.bxcchus;

import fr.bxcchus.methods.ChampionMethod;
import fr.bxcchus.methods.SkinMethod;
import fr.bxcchus.methods.SummonerMethod;
import fr.bxcchus.objects.Champion;
import fr.bxcchus.objects.Ownership;
import fr.bxcchus.objects.Skin;
import fr.bxcchus.objects.Summoner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        SkinMethod skinMethod = new SkinMethod();
        for (Skin s : skinMethod.getSkins()) {
            if (s.isOwned()) {
                System.out.println(s.getName());
            }
        }

    }


}