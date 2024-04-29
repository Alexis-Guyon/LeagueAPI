package fr.bxcchus.objects;

import java.util.List;

public class Summoner {
    private String name;
    private int level;
    private String rank;
    private String tier;
    private int leaguePoints;
    private int wins;
    private int losses;
    private String profileIconId;
    private String revisionDate;
    private String summonerId;
    private String accountId;

    public Summoner(String name, int level, String rank, String tier, int leaguePoints, int wins, int losses, String profileIconId, String revisionDate, String summonerId, String accountId) {
        this.name = name;
        this.level = level;
        this.rank = rank;
        this.tier = tier;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerId = summonerId;
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(String profileIconId) {
        this.profileIconId = profileIconId;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
