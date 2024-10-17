package fr.bxcchus.methods;

import fr.bxcchus.api.services.GameFlowService;
import fr.bxcchus.api.services.MatchService;
import fr.bxcchus.objects.GameFlow;
import fr.bxcchus.objects.Match;

import java.io.IOException;
import java.util.concurrent.*;

public class MatchMethod implements MatchService, GameFlowService {
    @Override
    public CompletableFuture<Match[]> findMatch() throws IOException {
        return MatchService.super.findMatch();

    }


    @Override
    public String getGameFlowPhase() throws IOException {
        return GameFlowService.super.getGameFlowPhase();
    }

    @Override
    public CompletableFuture<GameFlow[]> autoAcceptMatch() throws IOException {
        return GameFlowService.super.autoAcceptMatch();
    }

    public void startReadyCheckWatcher()  {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        try {
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    String gameflow = getGameFlowPhase();
                    System.out.println(gameflow);

                    if ("ReadyCheck".equals(gameflow)) {
                        System.out.println("Success");
                        autoAcceptMatch().thenAccept(gameFlows -> System.out.println("Accepted")).exceptionally(ex -> {
                            System.out.println(ex.getMessage());
                            return null;
                        });
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, 0, 2, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
