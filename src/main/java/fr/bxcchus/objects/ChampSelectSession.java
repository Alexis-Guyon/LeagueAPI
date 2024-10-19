package fr.bxcchus.objects;

import java.util.List;

public class ChampSelectSession {
    private List<Action> actions;  // Liste des actions possibles dans la session

    // Getters and setters
    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "ChampSelectSession{actions=" + actions + '}';
    }
}
