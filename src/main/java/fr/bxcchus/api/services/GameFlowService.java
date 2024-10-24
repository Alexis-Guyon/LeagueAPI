package fr.bxcchus.api.services;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.*;

import java.io.IOException;
import java.util.List;
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
    default Session getSession() throws IOException {
        String sessionUrl = "lol-login/v1/session";
        return request(sessionUrl, HttpMethod.GET, null, Session.class);
    }

    // Méthode pour choisir un champion via l'URL spécifique
    default CompletableFuture<GameFlow[]> pickChampion(int championId) throws IOException {

        // Récupérer le PUUID de la session actuelle
        Session session = getSession();
        String myPuuid = session.getPuuid();

        // Récupérer la session de jeu en cours
        GameSession gameSession = getSessionMatch();
        List<Team> myTeam = gameSession.getMyTeam();
        List<List<Action>> actions = gameSession.getActions();  // Récupérer toutes les actions en cours

        // Variable pour stocker mon actorCellId et mon actionId
        Integer myActorCellId = null;
        Integer myActionId = null;

        // Vérifier si mon PUUID est dans la liste des joueurs de l'équipe et récupérer actorCellId
        for (Team teamMember : myTeam) {
            if (teamMember.getPuuid().equals(myPuuid)) {
                myActorCellId = teamMember.getCellId();  // Récupérer l'actorCellId correspondant
                break;
            }
        }

        // Vérifier que myActorCellId a bien été trouvé
        if (myActorCellId == null) {
            throw new IOException("Le PUUID ne correspond pas à un membre de votre équipe.");
        }

        // Chercher l'Action ID correspondant à mon Actor Cell ID
        for (List<Action> actionList : actions) {
            for (Action action : actionList) {
                if (action.getActorCellId() == myActorCellId && "pick".equals(action.getType())) {
                    myActionId = action.getId();  // Récupérer l'ID de l'action associée à mon cellId
                    break;
                }
            }
        }

        // Vérifier que myActionId a bien été trouvé et est valide (> 0)
        if (myActionId == null || myActionId <= 0) {
            throw new IOException("Aucune action de sélection de champion valide trouvée pour cet acteur.");
        }

        // Alimentation de l'objet Action avec l'actorCellId trouvé
        Action action = new Action();
        action.setActorCellId(myActorCellId);
        action.setChampionId(championId);
        action.setCompleted(false);
        action.setAllyAction(true);
        action.setInProgress(true);
        action.setPickTurn(0);
        action.setType("pick");
        action.setId(myActionId);  // Utiliser le bon Action ID récupéré

        System.out.println("Action ID: " + action.getId() + ", Actor Cell ID: " + action.getActorCellId());

        String pickUrl = "lol-champ-select/v1/session/actions/" + myActionId;  // URL spécifique à utiliser
        return requestAsync(pickUrl, HttpMethod.PATCH, action, GameFlow[].class);
    }




    default GameSession getSessionMatch() throws IOException {
        return request("lol-champ-select/v1/session", HttpMethod.GET,null, GameSession.class);

    }
}
