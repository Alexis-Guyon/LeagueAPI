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

        if ("ChampSelect".equals(newPhase)) {
            try {
                // 1. Récupérer la session de sélection
                gameFlowService.getSession().thenAccept(session -> {
                    int actionId = session.getId();
                    System.out.println("Session de sélection récupérée avec actionId : " + actionId);

                    // 2. Choisir un champion avec un ID spécifique (par exemple 78)
                    try {
                        gameFlowService.pickChampion(actionId, 78)
                                .thenAccept(gameFlows -> {
                                    System.out.println("Champion sélectionné : " + 78);
                                })
                                .exceptionally(ex -> {
                                    System.err.println("Erreur lors de la sélection du champion : " + ex.getMessage());
                                    return null;
                                });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).exceptionally(ex -> {
                    System.err.println("Erreur lors de la récupération de la session de sélection : " + ex.getMessage());
                    return null;
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
