package fr.bxcchus.api.services.impl;

import fr.bxcchus.observers.GameFlowObserver;
import java.util.ArrayList;
import java.util.List;

public class GameFlowServiceImpl {

    private final List<GameFlowObserver> observers = new ArrayList<>();

    // Ajouter un observateur
    public void addObserver(GameFlowObserver observer) {
        observers.add(observer);
    }

    // Supprimer un observateur
    public void removeObserver(GameFlowObserver observer) {
        observers.remove(observer);
    }

    // Notifier tous les observateurs lorsque la phase de jeu change
    public void notifyGameFlowPhaseChange(String newPhase) {
        for (GameFlowObserver observer : observers) {
            observer.onGameFlowPhaseChange(newPhase);
        }
    }

    // Exemple de méthode qui récupère la phase actuelle et notifie
    public void checkGameFlowPhase() {
        String currentPhase = "None";  // Supposons que c'est la phase actuelle
        notifyGameFlowPhaseChange(currentPhase);  // Notifier les observateurs
    }
}
