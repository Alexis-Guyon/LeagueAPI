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
        if ("ReadyCheck".equals(newPhase)) {
            try {
                gameFlowService.autoAcceptMatch().thenAccept(gameFlows -> System.out.println("Match accepté")).exceptionally(ex -> {
                    System.err.println("Erreur lors de la sélection du champion : " + ex.getMessage());
                    return null;
                });
            } catch (IOException e) {
                System.err.println("Erreur lors de l'appel à pickChampion : " + e.getMessage());
            }

        }
        if ("ChampSelect".equals(newPhase)) {
            try {
                int championId = 78;  // Par exemple, l'ID du champion à sélectionner

                // Appel de la méthode pickChampion avec les IDs
                gameFlowService.pickChampion(championId)
                        .thenAccept(gameFlows -> {
                            System.out.println("Champion sélectionné avec succès.");
                        })
                        .exceptionally(ex -> {
                            System.err.println("Erreur lors de la sélection du champion : " + ex.getMessage());
                            return null;
                        });
            } catch (IOException e) {
                System.err.println("Erreur lors de l'appel à pickChampion : " + e.getMessage());
            }
        }
    }
}
