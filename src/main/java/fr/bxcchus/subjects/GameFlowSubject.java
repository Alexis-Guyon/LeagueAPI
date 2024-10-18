package fr.bxcchus.subjects;

import fr.bxcchus.api.services.GameFlowService;
import fr.bxcchus.observers.GameFlowObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameFlowSubject {
    private final List<GameFlowObserver> observers = new ArrayList<>();
    private String currentPhase;
    public void addObserver(GameFlowObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameFlowObserver observer) {
        observers.remove(observer);
    }

    public void startPhaseWatcher(GameFlowService service) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                String newPhase = service.getGameFlowPhase();

                if (!newPhase.equals(currentPhase)) {
                    currentPhase = newPhase;
                    notifyObservers();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    private void notifyObservers() {
        for (GameFlowObserver observer : observers) {
            observer.onGameFlowPhaseChange(currentPhase);
        }
    }
}
