package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session {
    private long accountId;
    private boolean connected;
    private String error;
    private String idToken;
    private boolean isInLoginQueue;
    private boolean isNewPlayer;
    private String puuid;
    private String state;
    private long summonerId;
    private String userAuthToken;
    private String username;
}
