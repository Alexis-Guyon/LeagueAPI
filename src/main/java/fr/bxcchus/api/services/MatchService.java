package fr.bxcchus.api.services;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MatchService extends IClient {
    default CompletableFuture<Match[]> findMatch() throws IOException {
        String url = "lol-lobby/v2/lobby/matchmaking/search";
        return requestAsync(url, HttpMethod.POST, null, Match[].class);
    }

    default GameSession getSessionMatch() {
        return request("lol-champ-select/v1/session", HttpMethod.GET, null, GameSession.class);
    }

    default CompletableFuture<GameFlow[]> pickChampion(int championId, String myPuuid) throws IOException {
        GameSession gameSession = getSessionMatch();
        List<Team> myTeam = gameSession.getMyTeam();
        List<List<Action>> actions = gameSession.getActions();

        Integer myActorCellId = null;
        Integer myActionId = null;

        for (Team teamMember : myTeam) {
            if (teamMember.getPuuid().equals(myPuuid)) {
                myActorCellId = teamMember.getCellId();
                break;
            }
        }

        if (myActorCellId == null) {
            throw new IOException("Le PUUID ne correspond pas à un membre de votre équipe.");
        }

        for (List<Action> actionList : actions) {
            for (Action action : actionList) {
                if (action.getActorCellId() == myActorCellId && "pick".equals(action.getType())) {
                    myActionId = action.getId();
                    break;
                }
            }
        }

        if (myActionId == null || myActionId < 0) {
            throw new IOException("Aucune action de sélection de champion valide trouvée pour cet acteur.");
        }

        Action action = new Action();
        action.setActorCellId(myActorCellId);
        action.setChampionId(championId);
        action.setCompleted(true);
        action.setAllyAction(true);
        action.setInProgress(true);
        action.setPickTurn(0);
        action.setType("pick");
        action.setId(myActionId);

        System.out.println("Action ID: " + action.getId() + ", Actor Cell ID: " + action.getActorCellId());

        String pickUrl = "lol-champ-select/v1/session/actions/" + myActionId;
        return requestAsync(pickUrl, HttpMethod.PATCH, action, GameFlow[].class);
    }
}
