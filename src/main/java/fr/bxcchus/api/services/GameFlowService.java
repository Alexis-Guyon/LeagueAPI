package fr.bxcchus.api.services;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.GameFlow;
import fr.bxcchus.objects.Action;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface GameFlowService extends IClient {

    // Méthode pour obtenir la phase de jeu
    default String getGameFlowPhase() throws IOException {
        String url = "lol-gameflow/v1/gameflow-phase";
        return request(url, HttpMethod.GET, null, String.class);
    }

    // Méthode pour accepter automatiquement le match
    default CompletableFuture<GameFlow[]> autoAcceptMatch() throws IOException {
        String phase = getGameFlowPhase();
        if ("ReadyCheck".equals(phase)) {
            String acceptUrl = "lol-matchmaking/v1/ready-check/accept";
            return requestAsync(acceptUrl, HttpMethod.POST, null, GameFlow[].class);
        }
        return CompletableFuture.completedFuture(null);
    }

    // Méthode pour récupérer la session de sélection
    default CompletableFuture<Action> getSession() throws IOException {
        String sessionUrl = "lol-champ-select/v1/session";
        return CompletableFuture.supplyAsync(() -> request(sessionUrl, HttpMethod.GET, null, Action.class));
    }

    // Méthode pour choisir un champion via l'URL spécifique
    default CompletableFuture<GameFlow[]> pickChampion(int actionId, int championId) throws IOException {
        String pickUrl = "lol-champ-select-legacy/v1/session/actions/1";  // URL spécifique à utiliser

        // Créer les données à envoyer pour la sélection du champion

        Action action = new Action();

        // Retourner la requête PATCH à cette URL
            action.setActorCellId(0);
            action.setChampionId(championId); // Choisir le champion avec ID 78
            action.setCompleted(true);
            action.setAllyAction(true);
            action.setInProgress(true);
            action.setPickTurn(1);
            action.setType("pick");
            action.setId(1);
        return requestAsync(pickUrl, HttpMethod.PATCH, action, GameFlow[].class);
    }
}
