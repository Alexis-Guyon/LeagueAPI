package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private String assignedPosition;
    private int cellId;
    private int championId;
    private int championPickIntent;
    private String nameVisibilityType;
    private String obfuscatedPuuid;
    private long obfuscatedSummonerId;
    private String puuid;
    private int selectedSkinId;
    private long spell1Id;
    private long spell2Id;
    private long summonerId;
    private int team;
    private int wardSkinId;

}
