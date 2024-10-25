package fr.bxcchus.bot.tree;

import fr.bxcchus.bot.ChampionStats;
import fr.bxcchus.bot.api.BotService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.CompletableFuture;

public class BotClient implements BotService {

    private final Robot robot;

    public BotClient(Robot robot) {
        this.robot = robot;
    }

    public void pressButtonB() {
        System.out.println("Pressing B");
        boolean back = true;
        // Récupérer les statistiques actuelles du bot via le service
        ChampionStats botStats = getActivePlayerData().getChampionStats();
        if (botStats == null) {
            System.out.println("Failed to retrieve bot stats.");
            return;
        }

        // Enregistrer la santé avant d'appuyer sur B
        double healthBefore = botStats.getCurrentHealth();
        System.out.println("Bot's Current Health before pressing B: " + healthBefore);

        // Simuler l'appui sur le bouton B
        robot.keyPress(KeyEvent.VK_B);
        robot.keyRelease(KeyEvent.VK_B);

        // Délai pour permettre à l'état du jeu de se mettre à jour (ex : 1 seconde)
        try {
            Thread.sleep(1000); // Attendre une seconde pour les mises à jour de l'état du jeu
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // Nouvelle tentative pour récupérer les statistiques et vérifier la santé
        botStats = getActivePlayerData().getChampionStats();
        if (botStats == null) {
            System.out.println("Failed to retrieve bot stats after pressing B.");
        }

        double healthAfter = botStats.getCurrentHealth();
        System.out.println("Bot's Current Health after pressing B: " + healthAfter);

        // Si la santé a diminué, annuler le retour
        if (healthAfter < healthBefore) {
            back = false;
            System.out.println("Health decreased! Canceling back.");
            return;
        }

        if (healthAfter == healthBefore || healthAfter > healthBefore) {
            back = true;
        }

        // Attendre 10 secondes avant d'appuyer sur P
        boolean finalBack = back;
        CompletableFuture.runAsync(() -> {
            try {
                if (finalBack) {
                    Thread.sleep(10000);
                    pressButtonP(); // Appuyer sur P après 10 secondes
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
    }



    private void pressButtonP() {
        System.out.println("Pressing P");
        robot.keyPress(KeyEvent.VK_P);
        robot.keyRelease(KeyEvent.VK_P);
    }
}
