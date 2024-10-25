package fr.bxcchus.bot.observer;

import fr.bxcchus.bot.ChampionStats;
import fr.bxcchus.bot.api.BotService;
import fr.bxcchus.bot.api.response.BotResponse;
import fr.bxcchus.bot.tree.BotClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HealthObserver implements Observer {
    private final BotService botService; // Injectez votre service pour obtenir les PV
    private final ChampionStats championStats; // Référence à ChampionStats
    private final ScheduledExecutorService scheduler;
    private final BotClient client;

    public HealthObserver(BotService botService, ChampionStats championStats, BotClient client) {
        this.botService = botService;
        this.championStats = championStats;
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.client = client;

        // Démarrer la vérification des PV
        startHealthCheck();
    }

    private void startHealthCheck() {
        scheduler.scheduleAtFixedRate(this::checkHealth, 0, 2, TimeUnit.SECONDS); // Vérifie toutes les 2 secondes
    }

    private void checkHealth() {
        // Récupérer les PV actuels
        try {
            CompletableFuture.supplyAsync(() -> {
                try {
                    BotResponse response = botService.getActivePlayerData(); // Récupérer les données
                    if (response == null) {
                        System.out.println("No response from bot service."); // Vérifier si la réponse est null
                        return null; // Retourner null si aucune réponse
                    }
                    return response.getChampionStats(); // Récupérer ChampionStats
                } catch (Exception e) {
                    System.out.println("Error retrieving health data: " + e.getMessage());
                    return null; // Retourner null en cas d'erreur
                }
            }).thenAccept(stats -> {
                if (stats != null) {
                    double currentHealth = stats.getCurrentHealth();
                    double maxHealth = stats.getMaxHealth();
                    championStats.setCurrentHealth(currentHealth);
                    System.out.println("Current Health: " + currentHealth);

                    // Calculer le seuil de 20% de HP
                    double twentyPercentHealth = 0.2 * maxHealth;

                    // Vérifier si les HP sont en dessous de 20%
                    if (currentHealth <= twentyPercentHealth) {
                        System.out.println("Health is below 20%. Initiating back.");
                        client.pressButtonB(); // Méthode pour faire reculer le bot
                    }
                } else {
                    System.out.println("Failed to retrieve health data or stats are null.");
                }
            }).exceptionally(e -> {
                System.out.println("Error during health check: " + e.getMessage());
                return null;
            });

        } catch (Exception e) {
            System.out.println("Error checking health: " + e.getMessage());
        }
    }


    // Optionnel : méthode pour arrêter la vérification
    public void stopHealthCheck() {
        scheduler.shutdown();
    }

    @Override
    public void update() {
        // Cette méthode peut être laissée vide si elle n'est pas utilisée
    }

    private void notifyObservers() {
        System.out.println("HP CRITIQUE");
    }
}
