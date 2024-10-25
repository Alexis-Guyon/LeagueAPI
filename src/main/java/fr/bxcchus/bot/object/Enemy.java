package fr.bxcchus.bot.object;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Enemy {
    private String championName;
    private boolean isBot;
    private boolean isDead;
    private List<String> items;
    private int level;
    private String position;
    private String rawChampionName;
    private String rawSkinName;
    private double respawnTimer;
    private String riotId;
    private String riotIdGameName;
    private String riotIdTagLine;
    private Map<String, Rune> runes;
}
