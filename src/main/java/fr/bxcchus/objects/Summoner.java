package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Summoner {
    private long accountId;
    private String displayName;
    private String gameName;
    private String internalName;
    private boolean nameChangeFlag;
    private int percentCompleteForNextLevel;
    private String privacy;
    private int profileIconId;
    private String puuid;
    private long summonerId;
    private int summonerLevel;
    private String tagLine;
    private boolean unnamed;
    private int xpSinceLastLevel;
    private int xpUntilNextLevel;

}
