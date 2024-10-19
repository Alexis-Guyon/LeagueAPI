package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Team {
    private List<Player> myTeam;

    // Trouver un joueur par son PUUID
    public Player findPlayerByPuuid(String puuid) {
        for (Player player : myTeam) {
            if (player.getPuuid().equals(puuid)) {
                return player;
            }
        }
        return null;
    }
}
