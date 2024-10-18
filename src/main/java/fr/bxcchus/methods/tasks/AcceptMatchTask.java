package fr.bxcchus.methods.tasks;

import fr.bxcchus.api.services.GameFlowService;
import fr.bxcchus.observers.GameFlowObserver;

import java.io.IOException;

public class AcceptMatchTask implements GameFlowObserver {
    private final GameFlowService gameFlowService;

    public AcceptMatchTask(GameFlowService gameFlowService) {
        this.gameFlowService = gameFlowService;
    }

    @Override
    public void onGameFlowPhaseChange(String newPhase) {
        System.out.println("Phase de jeu actuelle : " + newPhase);

        // Si la phase est "ReadyCheck", on accepte automatiquement le match
        if ("ReadyCheck".equals(newPhase)) {
            try {
                System.out.println("Phase 'ReadyCheck' détectée. Acceptation automatique...");
                gameFlowService.autoAcceptMatch().thenAccept(gameFlows -> {
                    System.out.println("Match accepté automatiquement !");
                }).exceptionally(ex -> {
                    System.out.println("Erreur lors de l'acceptation automatique : " + ex.getMessage());
                    return null;
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
