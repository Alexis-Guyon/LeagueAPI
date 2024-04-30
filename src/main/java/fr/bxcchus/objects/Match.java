package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Match {
    private long gameId;
    private String gameMode;
    private String gameType;
    private int gameDuration;
    private long gameCreation;

}
