package fr.bxcchus.api.services;

import fr.bxcchus.objects.GameFlow;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface GameService extends GameFlowService, SessionService, MatchService {

    // Méthode pour accepter automatiquement le match
    default CompletableFuture<GameFlow[]> acceptMatchIfReady() throws IOException {
        String phase = getGameFlowPhase();
        System.out.println("Phase : " + phase);

        // Accepter automatiquement le match si en ReadyCheck
        if ("ReadyCheck".equals(phase)) {
            System.out.println("Accepting match in phase: " + phase);
            return autoAcceptMatch();
        }
        return CompletableFuture.completedFuture(null);
    }

    // Méthode pour choisir un champion pendant la phase ChampSelect
    default CompletableFuture<GameFlow[]> pickChampion(int championId) throws IOException {
        String phase = getGameFlowPhase();
        System.out.println("Phase : " + phase);

        // Récupérer le PUUID du joueur actuel
        String myPuuid = getCurrentPlayerPuuid();

        // Si la phase de jeu est en ChampSelect, on procède à la sélection du champion
        if ("ChampSelect".equals(phase)) {
            return pickChampion(championId, myPuuid);
        }
        return CompletableFuture.completedFuture(null);
    }
}
