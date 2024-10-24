package fr.bxcchus.objects;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameSession {
    private List<List<Action>> actions;
    private boolean allowBattleBoost;
    private boolean allowDuplicatePicks;
    private boolean allowLockedEvents;
    private boolean allowRerolling;
    private boolean allowSkinSelection;
    private Ban bans;
    private List<Integer> benchChampions;
    private boolean benchEnabled;
    private int boostableSkinCount;
    private ChatDetail chatDetails;
    private int counter;
    private long gameId;
    private boolean hasSimultaneousBans;
    private boolean hasSimultaneousPicks;
    private boolean isCustomGame;
    private boolean isSpectating;
    private int localPlayerCellId;
    private int lockedEventIndex;
    private List<Team> myTeam;
    private List<Integer> pickOrderSwaps;
    private int recoveryCounter;
    private int rerollsRemaining;
    private boolean skipChampionSelect;
    private List<Team> theirTeam;
    private Timer timer;
    private List<Object> trades;


}
