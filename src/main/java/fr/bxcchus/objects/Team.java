package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {
    private String assignedPosition;
    private int cellId;
    private int championId;
    private int championPickIntent;
    private String nameVisibilityType;
    private String obfuscatedPuuid;
    private int obfuscatedSummonerId;
    private String puuid;
    private int selectedSkinId;
    private String spell1Id;
    private String spell2Id;
    private long summonerId;
    private int team;
    private int wardSkinId;
}
