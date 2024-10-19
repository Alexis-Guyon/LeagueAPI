package fr.bxcchus.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Action {
    private int actorCellId;
    private int championId;
    private boolean completed;
    private int id;
    private boolean isAllyAction;
    private boolean isInProgress;
    private int pickTurn;
    private String type;

}
