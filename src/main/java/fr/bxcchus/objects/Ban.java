package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Ban {
    private List<Integer> myTeamBans;
    private int numBans;
    private List<Integer> theirTeamBans;
}
