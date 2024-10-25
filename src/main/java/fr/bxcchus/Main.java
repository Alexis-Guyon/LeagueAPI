package fr.bxcchus;

import fr.bxcchus.bot.ChampionStats;
import fr.bxcchus.bot.api.BotService;
import fr.bxcchus.bot.api.impl.BotServiceImpl;
import fr.bxcchus.bot.observer.HealthObserver;
import fr.bxcchus.bot.tree.BotClient;

import java.awt.*;

public class Main implements BotService  {
    public static void main(String[] args) throws AWTException {
        /*GameService gameService = new GameServiceImpl();
        GameFlowSubject subject = new GameFlowSubject();

        AcceptMatchTask acceptMatchTask = new AcceptMatchTask(gameService);

        subject.addObserver(acceptMatchTask);

        subject.startPhaseWatcher(gameService);*/
        try {
            // Créer une instance de ChampionStats
            ChampionStats bot = new ChampionStats();
            BotService botService = new BotServiceImpl(); // Implémentation concrète de BotService

            // Mettre à jour les statistiques du bot
            botService.updateBotStats(bot);

            // Vérification des stats du bot
            System.out.println("Bot's Current Health: " + bot.getCurrentHealth());
            System.out.println("Bot's Max Health: " + bot.getMaxHealth());
            System.out.println("Bot's Armor: " + bot.getArmor());

            // Initialiser Robot et Subject
            Robot robot = new Robot();

            // Créer une instance de BotClient
            BotClient client = new BotClient(robot);

            // Créer un observateur de santé pour vérifier les statistiques en continu
            new HealthObserver(botService, bot, client);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}