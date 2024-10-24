package fr.bxcchus.methods.tasks;

import fr.bxcchus.api.services.GameService;
import fr.bxcchus.observers.GameFlowObserver;

import java.io.IOException;

public class AcceptMatchTask implements GameFlowObserver {
    private final GameService service;

    public AcceptMatchTask(GameService service) {
        this.service = service;
    }

    @Override
    public void onGameFlowPhaseChange(String newPhase) {
        System.out.println("Phase de jeu changée : " + newPhase);

        try {
            if("Lobby".equals(newPhase)) {
                service.findMatch();
            }
            // Si on est dans la phase de ReadyCheck, on accepte le match
            if ("ReadyCheck".equals(newPhase)) {
                service.acceptMatchIfReady()
                        .thenAccept(gameFlows -> System.out.println("Match accepté"))
                        .exceptionally(throwable -> {
                            System.out.println("Erreur lors de l'acceptation du match : " + throwable.getMessage());
                            return null;
                        });
            }

            // Si on est dans la phase de ChampSelect, on sélectionne un champion
            if ("ChampSelect".equals(newPhase)) {
                service.pickChampion(78)  // Exemple avec Poppy (ID 78)
                        .thenAccept(gameFlows -> System.out.println("Champion sélectionné"))
                        .exceptionally(throwable -> {
                            System.out.println("Erreur lors de la sélection du champion : " + throwable.getMessage());
                            return null;
                        });
            }
        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
