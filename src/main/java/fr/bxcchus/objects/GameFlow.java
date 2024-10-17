package fr.bxcchus.objects;

public enum GameFlow {
    CHAMP_SELECT,
    READY_CHECK,
    IN_GAME,
    GAME_END,
    OTHER_PHASE;

    public static GameFlow fromString(String phase) {
        switch (phase) {
            case "ChampSelect":
                return CHAMP_SELECT;
            case "ReadyCheck":
                return READY_CHECK;
            case "InGame":
                return IN_GAME;
            case "GameEnd":
                return GAME_END;
            default:
                return OTHER_PHASE;
        }
    }
}
